package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 公用dao层
 * @author Administrator
 *
 */
public interface IBaseDao<T> {

	/**
	 * 公用添加
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * 公用更新
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * 公用保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	
	
	public T get(Serializable qid);
	/**
	 * 公用删除
	 * @param entity
	 */
	public void delete(Serializable did);
	
	/**
	 * 公用多个删除
	 * @param entity
	 */
	public void delete(Serializable... dids);
	
	/**
	 * 分页查询
	 * @param Hql HQL查询语句
	 * @param startIndex 开始位置
	 * @param pageSize 每页显示几条
	 * @param params 参数
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Integer startIndex,Integer pageSize,List<Object> params);
	
	/**
	 * 查询
	 * @param Hql HQL查询语句
	 * @param startIndex 开始位置
	 * @param pageSize 每页显示几条
	 * @param params 参数
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Serializable... args);
	
	
	/**
	 * 分页显示总条数
	 * @param Hql
	 * @param params
	 * @return
	 */
	public Integer queryHQLCount(String Hql,List<Object> params);
	
	/**
	 * 查询单一的当前对象
	 * @param Hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String Hql,Serializable...args);
	
	
	public Integer updateHQL(String hql, Serializable... args);
	
	public Integer updateSQL(String sql, Serializable... args);
	
	
}
