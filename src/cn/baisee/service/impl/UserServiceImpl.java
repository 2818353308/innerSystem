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
	 * 查询分页数据
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
		//查询分页数据
		List<? extends Object> list = userDao.queryHQLList(Hql, pageVo.getStartIndex(), pageVo.getPageSize(), params);
		pageVo.setResult(list);
		//总条数
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
		//查询得到结果集
		List<AuthorResources> results = userDao.queryUserAuthorTree(userId);
		//分析资源树的父子关系
		Map<Integer, List<AuthorResources >> resultsMap = new HashMap<Integer, List<AuthorResources >>();
		//循环分析
		for(AuthorResources item:results){
			//获取当前节点的父节点释放为空
			List<AuthorResources> parents = resultsMap.get(item.getParentId());
			if(parents==null){
				parents = new ArrayList<AuthorResources>();
				parents.add(item);
			}else{
				parents.add(item);
			}
			//将当前结果集填回
			resultsMap.put(item.getParentId(), parents);
			
		}
		//JsonArray
		JSONArray array=new JSONArray();
		//构建树
		buildTree(null, array, resultsMap);
		return array.toString();
	}
	
	/**
	 * 构建书
	 * @param parentId 当前节点的父节点ID
	 * @param array 构建JSONArray的对象
	 */
	public void buildTree(Integer parentId,JSONArray array,Map<Integer, List<AuthorResources >> resultsMap){
		List<? extends Object> childs;
		//查询是否为根节点
		if(parentId == null){
			childs = resultsMap.get(null);
		}else{
			childs = resultsMap.get(parentId);
		}
		//分析当前节点是否还有子节点
		for (Object item : childs) {
			// 查询当前节点
			AuthorResources resources = (AuthorResources) item;
			// 查询当前节点是否有子节点
			List<? extends Object> citems = resultsMap.get(resources.getId());
			// 判断当前节点是否有子节点
			if (citems!= null &&citems.size() > 0) {
				// 有子节点
				JSONArray childArray = new JSONArray();
				buildTree(resources.getId(), childArray,resultsMap);
				System.out.println("当前"+resources.getId()+"有子节点"+childArray);
				//将当前节点放入到JSONObject中
				JSONObject itemObj = JSONObject.fromObject(resources);
				itemObj.element("children", childArray);
				//存放到构建JSONArray的对象中
				array.add(itemObj);
			} else {
				// 没有子节点
				array.add(JSONObject.fromObject(resources));
			}

		}
	}
}
