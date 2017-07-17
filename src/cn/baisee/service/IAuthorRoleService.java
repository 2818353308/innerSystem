package cn.baisee.service;

import cn.baisee.entity.PageVo;

public interface IAuthorRoleService {

	public PageVo toList(PageVo pageVo);
	
	public void saveAuthorRole(String roleId,String resourcesIds);
	
	public String queryAll();
	
}
