package cn.baisee.service;

import cn.baisee.entity.PageVo;
import cn.baisee.entity.User;

/**
 * 用户业务逻辑层
 * @author Administrator
 *
 */
public interface IUserService {
	
	public User login(String uname,String upass);
	
	public void saveUserRole(String userId, String roleIds);
	
	public PageVo toList(PageVo pageVo);
	
	public String queryUserAuthorTree(String userId);

}
