package cn.baisee.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import cn.baisee.dao.IAuthorRoleDao;
import cn.baisee.entity.PageVo;
import cn.baisee.service.IAuthorRoleService;
/**
 * 角色selvice
 * @author Administrator
 *
 */
@Service
public class AuthorRoleServiceImpl implements IAuthorRoleService {
	
	@Resource(name = "authorRoleDaoImpl")
	private IAuthorRoleDao authorRoleDao;

	/**
	 * 查询分页数据
	 */
	@Override
	public PageVo toList(PageVo pageVo) {
		if(pageVo!=null&&pageVo.getCurrentPage()==null){
			pageVo.setCurrentPage(1);
		}
		String Hql = " from AuthorRole where 1=1";
		String HqlCount = "select count(*) from AuthorRole where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		//查询分页数据
		List<? extends Object> list = authorRoleDao.queryHQLList(Hql, pageVo.getStartIndex(), pageVo.getPageSize(), params);
		pageVo.setResult(list);
		//总条数
		Integer count = authorRoleDao.queryHQLCount(HqlCount, params);
		pageVo.setTotalCount(count);
		return pageVo;
	}

	@Override
	public void saveAuthorRole(String roleId, String resourcesIds) {
		authorRoleDao.saveAuthorRole(roleId, resourcesIds);
	}
	
	@Override
	public String queryAll() {
		//构建Role树
		JSONArray array = new JSONArray();
		
		//查询所有角色
		List<?> results = authorRoleDao.queryHQLList("from AuthorRole");
		
		for(Object item:results){
			array.add(JSONObject.fromObject(item));
		}
		String result = array.toString();
		System.out.println(result);
		return result;
	}

}
