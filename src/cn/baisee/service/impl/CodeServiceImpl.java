package cn.baisee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.baisee.dao.ICodeDao;
import cn.baisee.entity.SimpleCode;
import cn.baisee.service.ICodeService;


@Service
public class CodeServiceImpl implements ICodeService{
	
	@Resource(name = "codeDaoImpl")
	private ICodeDao codeDao;

	@Override
	public SimpleCode query(String codetype) {
		String Hql = "from SimpleCode where codetype=?";
		SimpleCode s =  (SimpleCode) codeDao.queryHqlUnique(Hql, codetype);
		return s;
	}

	@Override
	public List<SimpleCode> queryAll() {
		String hql = "from SimpleCode";
		List<SimpleCode> list = (List<SimpleCode>) codeDao.queryHQLList(hql);
		return list;
	}


}
