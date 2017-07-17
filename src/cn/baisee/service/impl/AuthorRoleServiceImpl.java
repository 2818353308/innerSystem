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
 * ��ɫselvice
 * @author Administrator
 *
 */
@Service
public class AuthorRoleServiceImpl implements IAuthorRoleService {
	
	@Resource(name = "authorRoleDaoImpl")
	private IAuthorRoleDao authorRoleDao;

	/**
	 * ��ѯ��ҳ����
	 */
	@Override
	public PageVo toList(PageVo pageVo) {
		if(pageVo!=null&&pageVo.getCurrentPage()==null){
			pageVo.setCurrentPage(1);
		}
		String Hql = " from AuthorRole where 1=1";
		String HqlCount = "select count(*) from AuthorRole where 1=1 ";
		List<Object> params=new ArrayList<Object>();
		//��ѯ��ҳ����
		List<? extends Object> list = authorRoleDao.queryHQLList(Hql, pageVo.getStartIndex(), pageVo.getPageSize(), params);
		pageVo.setResult(list);
		//������
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
		//����Role��
		JSONArray array = new JSONArray();
		
		//��ѯ���н�ɫ
		List<?> results = authorRoleDao.queryHQLList("from AuthorRole");
		
		for(Object item:results){
			array.add(JSONObject.fromObject(item));
		}
		String result = array.toString();
		System.out.println(result);
		return result;
	}

}
