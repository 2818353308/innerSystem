package cn.baisee.dao.impl;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.baisee.common.dao.BaseDaoImpl;
import cn.baisee.dao.ICodeDao;
import cn.baisee.entity.SimpleCode;

@Repository
public class CodeDaoImpl extends BaseDaoImpl<SimpleCode> implements ICodeDao{

	@Override
	public Class<SimpleCode> getClazz() {
		return SimpleCode.class;
	}

	@Resource(name = "hibernateTemplate")
	public void setTemplate(HibernateTemplate template) {
		this.template=template;
	}

}
