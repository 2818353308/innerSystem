package cn.baisee.dao.impl;


import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.IAuthorRoleDao;
import cn.baisee.entity.AuthorRole;
/**
 * 角色dao
 * @author Administrator
 *
 */
@Repository
public class AuthorRoleDaoImpl extends BaseDaoImpl<AuthorRole> implements
		IAuthorRoleDao {

	@Override
	public Class<AuthorRole> getClazz() {
		return AuthorRole.class;
	}

	@Resource(name = "hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}

	public void saveAuthorRole(String roleId,String resourcesIds){
		
		//先删除已经保存的。
		updateSQL("delete from sys_author_role_resources where roleId=?", roleId);
		//拆分多个资源的ID
		String [] resourceIds=resourcesIds.split(",");
		
		//循环资源ID 保存与角色的关联
		for(String resourceId : resourceIds){
			//insert into sys_author_role_resources (roleId,resourcesId) values()
			updateSQL("insert into sys_author_role_resources (roleId,resourcesId) values(?,?)", roleId,resourceId);
			System.out.println("");
		}
	}



}
