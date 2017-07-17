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
	 * 查询分页数据
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
		
		
		//查询分页数据
		List<? extends Object> list = authorResourcesDao.queryHQLList(Hql, pageVo.getStartIndex(), pageVo.getPageSize(), params);
		pageVo.setResult(list);
		//总条数
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
		//判断释放子节点
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
	 * 构建书
	 * @param parentId 当前节点的父节点ID
	 * @param array 构建JSONArray的对象
	 */
	@SuppressWarnings("unchecked")
	public void buildTree(Integer parentId,JSONArray array){
		List<Object> childs;
		//查询是否为根节点
		if(parentId == null){
			childs = (List<Object>) authorResourcesDao.queryHQLList(" from AuthorResources where parentId is Null");
		}else{
			childs = (List<Object>) authorResourcesDao.queryHQLList(" from AuthorResources where parentId =?",parentId);
		}
		//分析当前节点是否还有子节点
		for (Object item : childs) {
			// 查询当前节点
			AuthorResources resources = (AuthorResources) item;
			// 查询当前节点是否有子节点
			List<? extends Object> citems = authorResourcesDao.queryHQLList("from AuthorResources where parentId=? ", resources.getId());
			// 判断当前节点是否有子节点
			if (citems.size() > 0) {
				// 有子节点
				JSONArray childArray = new JSONArray();
				buildTree(resources.getId(), childArray);
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
	
	//使用json添加支持包json-lib-2.4-jdk15.jar 、ezmorph-1.0.6.jar、commons-collections-3.2.1.jar、commons-beanutils-1.8.0.jar
	/*public static void main(String[] args) {
		JSONObject jsonObject = JSONObject.fromObject(new AuthorResources());
		System.out.println(jsonObject.toString());
		JSONArray array = new JSONArray();
		array.add(jsonObject);
		System.out.println(array.toString());
	}*/
}
