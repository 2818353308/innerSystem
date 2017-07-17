package cn.baisee.service;

import java.io.Serializable;

import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.PageVo;

public interface IAuthorResourcesService {

	public PageVo toList(PageVo pageVo);
	
	public void saveOrUpdate(AuthorResources res);
	
	/**
	 * ɾ���ڵ�ID
	 */
	public void delete(Serializable did);
	
	/**
	 * 
	 */
	public String queryAll();
}
