package cn.baisee.dao;

import cn.baisee.common.dao.IBaseDao;
import cn.baisee.entity.AuthorRole;

/**
 * ½ÇÉ«dao
 * @author 
 *
 */
public interface IAuthorRoleDao extends IBaseDao<AuthorRole> {

	public void saveAuthorRole(String roleId,String resourcesIds);
	
}
