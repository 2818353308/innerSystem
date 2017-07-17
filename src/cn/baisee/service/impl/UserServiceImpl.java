package cn.baisee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IUserDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.PageVo;
import cn.baisee.entity.User;
import cn.baisee.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;
	@Override
	public User login(String uname, String upass) {
		Object o = userDao.queryHqlUnique(" from User where uname=? and upass=?", uname,upass);
		return (User) o;
	}

	/**
	 * ��ѯ��ҳ����
	 */
	@Override
	public PageVo toList(PageVo pageVo) {
		
		if(pageVo!=null&&pageVo.getCurrentPage()==null){
			pageVo.setCurrentPage(1);
		}
		String Hql = " from User where 1=1";
		String HqlCount = "select count(*) from User where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		if(pageVo.getParams()!=null){
			if (pageVo.getParams().get("uname")!= null && !pageVo.getParams().get("uname").equals("")) {
				Hql+=" and uname like ?";
				HqlCount+="and uname like ?";
				params.add("%"+pageVo.getParams().get("uname").toString()+"%");
			}
			if (pageVo.getParams().get("email")!= null && !pageVo.getParams().get("email").equals("")) {
				Hql+=" and email like ?";
				HqlCount+="and email like ?";
				params.add("%"+pageVo.getParams().get("email").toString()+"%");
			}
			if (pageVo.getParams().get("phone")!= null && !pageVo.getParams().get("phone").equals("")) {
				Hql+=" and phone like ?";
				HqlCount+="and phone like ?";
				params.add("%"+pageVo.getParams().get("phone").toString()+"%");
			}
		}
		//��ѯ��ҳ����
		List<? extends Object> list = userDao.queryHQLList(Hql, pageVo.getStartIndex(), pageVo.getPageSize(), params);
		pageVo.setResult(list);
		//������
		Integer count = userDao.queryHQLCount(HqlCount, params);
		pageVo.setTotalCount(count);
		return pageVo;
	}

	@Override
	public void saveUserRole(String userId, String roleIds) {
		if(userId!=null&&roleIds!=null){
			userDao.saveUserRole(userId, roleIds);
		}
	}
	
	@Override
	public String queryUserAuthorTree(String userId) {
		//��ѯ�õ������
		List<AuthorResources> results = userDao.queryUserAuthorTree(userId);
		//������Դ���ĸ��ӹ�ϵ
		Map<Integer, List<AuthorResources >> resultsMap = new HashMap<Integer, List<AuthorResources >>();
		//ѭ������
		for(AuthorResources item:results){
			//��ȡ��ǰ�ڵ�ĸ��ڵ��ͷ�Ϊ��
			List<AuthorResources> parents = resultsMap.get(item.getParentId());
			if(parents==null){
				parents = new ArrayList<AuthorResources>();
				parents.add(item);
			}else{
				parents.add(item);
			}
			//����ǰ��������
			resultsMap.put(item.getParentId(), parents);
			
		}
		//JsonArray
		JSONArray array=new JSONArray();
		//������
		buildTree(null, array, resultsMap);
		return array.toString();
	}
	
	/**
	 * ������
	 * @param parentId ��ǰ�ڵ�ĸ��ڵ�ID
	 * @param array ����JSONArray�Ķ���
	 */
	public void buildTree(Integer parentId,JSONArray array,Map<Integer, List<AuthorResources >> resultsMap){
		List<? extends Object> childs;
		//��ѯ�Ƿ�Ϊ���ڵ�
		if(parentId == null){
			childs = resultsMap.get(null);
		}else{
			childs = resultsMap.get(parentId);
		}
		//������ǰ�ڵ��Ƿ����ӽڵ�
		for (Object item : childs) {
			// ��ѯ��ǰ�ڵ�
			AuthorResources resources = (AuthorResources) item;
			// ��ѯ��ǰ�ڵ��Ƿ����ӽڵ�
			List<? extends Object> citems = resultsMap.get(resources.getId());
			// �жϵ�ǰ�ڵ��Ƿ����ӽڵ�
			if (citems!= null &&citems.size() > 0) {
				// ���ӽڵ�
				JSONArray childArray = new JSONArray();
				buildTree(resources.getId(), childArray,resultsMap);
				System.out.println("��ǰ"+resources.getId()+"���ӽڵ�"+childArray);
				//����ǰ�ڵ���뵽JSONObject��
				JSONObject itemObj = JSONObject.fromObject(resources);
				itemObj.element("children", childArray);
				//��ŵ�����JSONArray�Ķ�����
				array.add(itemObj);
			} else {
				// û���ӽڵ�
				array.add(JSONObject.fromObject(resources));
			}

		}
	}
}
