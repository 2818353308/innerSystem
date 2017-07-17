package cn.baisee.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IAuthorResourcesDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.PageVo;
import cn.baisee.service.IAuthorResourcesService;

@Service
public class AuthorResourcesServiceImpl implements IAuthorResourcesService{

	@Resource(name = "authorResourcesDaoImpl")
	private IAuthorResourcesDao authorResourcesDao;
	
	/**
	 * ��ѯ��ҳ����
	 */
	@Override
	public PageVo toList(PageVo pageVo) {
		if(pageVo==null){
			pageVo = new PageVo();
		}
		
		if(pageVo!=null&&pageVo.getCurrentPage()==null){
			pageVo.setCurrentPage(1);
		}
		String Hql = " from AuthorResources where 1=1";
		String HqlCount = "select count(*) from AuthorResources where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		
		if(pageVo!=null&&pageVo.getParams()!=null){
			if (pageVo.getParams().get("parentId")!= null && !pageVo.getParams().get("parentId").equals("")) {
				Hql+=" and parentId = ?";
				HqlCount+="and parentId = ?";
				params.add(Integer.valueOf(pageVo.getParams().get("parentId")));
			}
		}
		
		
		//��ѯ��ҳ����
		List<? extends Object> list = authorResourcesDao.queryHQLList(Hql, pageVo.getStartIndex(), pageVo.getPageSize(), params);
		pageVo.setResult(list);
		//������
		Integer count = authorResourcesDao.queryHQLCount(HqlCount, params);
		pageVo.setTotalCount(count);
		return pageVo;
	}

	@Override
	public void saveOrUpdate(AuthorResources res) {
		authorResourcesDao.saveOrUpdate(res);
	}

	@Override
	public void delete(Serializable did) {
		//�ж��ͷ��ӽڵ�
		@SuppressWarnings("unchecked")
		List<Object> res = (List<Object>) authorResourcesDao.queryHQLList(" from AuthorResources where parentId=?", did);
		if(res.size()>1){
			for(Object item: res){
				AuthorResources ress = (AuthorResources) item;
				delete(ress.getId());
			}
		}else{
			authorResourcesDao.delete(did);
		}
		
	}

	@Override
	public String queryAll() {
		JSONArray array=new JSONArray();
		
		buildTree(null, array);
		
		return array.toString();
	}
	
	/**
	 * ������
	 * @param parentId ��ǰ�ڵ�ĸ��ڵ�ID
	 * @param array ����JSONArray�Ķ���
	 */
	@SuppressWarnings("unchecked")
	public void buildTree(Integer parentId,JSONArray array){
		List<Object> childs;
		//��ѯ�Ƿ�Ϊ���ڵ�
		if(parentId == null){
			childs = (List<Object>) authorResourcesDao.queryHQLList(" from AuthorResources where parentId is Null");
		}else{
			childs = (List<Object>) authorResourcesDao.queryHQLList(" from AuthorResources where parentId =?",parentId);
		}
		//������ǰ�ڵ��Ƿ����ӽڵ�
		for (Object item : childs) {
			// ��ѯ��ǰ�ڵ�
			AuthorResources resources = (AuthorResources) item;
			// ��ѯ��ǰ�ڵ��Ƿ����ӽڵ�
			List<? extends Object> citems = authorResourcesDao.queryHQLList("from AuthorResources where parentId=? ", resources.getId());
			// �жϵ�ǰ�ڵ��Ƿ����ӽڵ�
			if (citems.size() > 0) {
				// ���ӽڵ�
				JSONArray childArray = new JSONArray();
				buildTree(resources.getId(), childArray);
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
	
	//ʹ��json���֧�ְ�json-lib-2.4-jdk15.jar ��ezmorph-1.0.6.jar��commons-collections-3.2.1.jar��commons-beanutils-1.8.0.jar
	/*public static void main(String[] args) {
		JSONObject jsonObject = JSONObject.fromObject(new AuthorResources());
		System.out.println(jsonObject.toString());
		JSONArray array = new JSONArray();
		array.add(jsonObject);
		System.out.println(array.toString());
	}*/
}
