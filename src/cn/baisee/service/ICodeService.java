package cn.baisee.service;

import java.util.List;

import cn.baisee.entity.SimpleCode;

public interface ICodeService {
	/**
	 * ����code��ѯ��SimpleCode����
	 * @param code
	 * @return
	 */
	public SimpleCode query(String codetype);
	
	public List<SimpleCode> queryAll();

}
