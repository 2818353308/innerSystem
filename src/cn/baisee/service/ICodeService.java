package cn.baisee.service;

import java.util.List;

import cn.baisee.entity.SimpleCode;

public interface ICodeService {
	/**
	 * 根据code查询出SimpleCode对象
	 * @param code
	 * @return
	 */
	public SimpleCode query(String codetype);
	
	public List<SimpleCode> queryAll();

}
