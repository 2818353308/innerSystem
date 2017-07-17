package cn.baisee.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.IUserDao;
import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {


	@Override
	public Class<User> getClazz() {
		return User.class;
	}

	@Resource(name = "hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
	
	public List<AuthorResources> queryUserAuthorTree(String userId){
		Session session = template.getSessionFactory().getCurrentSession();
		String sql = "select DISTINCT sys_author_resources.* from sys_author_user_role LEFT JOIN sys_author_role_resources on sys_author_user_role.roleId=sys_author_role_resources.roleId LEFT JOIN sys_author_resources on sys_author_resources.id=sys_author_role_resources.resourcesId  where sys_author_user_role.userId=?";
		Query query = session.createSQLQuery(sql).setResultTransformer(new AliasToBeanResultTransformer(AuthorResources.class));
		query.setParameter(0, userId);
		//得到结果集、
		List<AuthorResources> results = query.list();
		return results;
	}

	@Override
	public void saveUserRole(String userId, String roleIds) {
		//先删除以保存的
		updateSQL("delete from sys_author_user_role where userId=?", userId);
		//拆分多个资源的id
		String[] arrayRoleIds = roleIds.split(",");
		for(String roleId : arrayRoleIds){
			updateSQL("insert into sys_author_user_role (userId,roleId)values(?,?)", userId,roleId);
		}
	}

}
