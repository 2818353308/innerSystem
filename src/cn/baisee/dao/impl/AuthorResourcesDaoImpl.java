package cn.baisee.dao.impl;


import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.IAuthorResourcesDao;
import cn.baisee.entity.AuthorResources;

@Repository
public class AuthorResourcesDaoImpl extends BaseDaoImpl<AuthorResources> implements IAuthorResourcesDao {

	@Override
	public Class<AuthorResources> getClazz() {
		return AuthorResources.class;
	}

	@Resource(name = "hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}
}
