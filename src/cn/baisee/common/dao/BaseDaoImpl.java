package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * 公用dao实现层
 * @author Administrator
 *
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {

	protected  HibernateTemplate template;

	/**
	 * 公用添加
	 * @param entity
	 */
	public void save(Object entity){
		template.save(entity);
	}
	
	/**
	 * 公用更新
	 * @param entity
	 */
	public void update(Object entity){
		template.update(entity);
	}
	
	/**
	 * 公用保存或更新
	 * @param entity
	 */
	public void saveOrUpdate(Object entity){
		template.saveOrUpdate(entity);
	}
	
	
	public T get(Serializable qid){
		return template.get(getClazz(), qid);
	}
	/**
	 * 公用删除
	 * @param entity
	 */
	public void delete(Serializable did){
		//删除之前先查询get(did)
		template.delete(get(did));
	}
	
	/**
	 * 公用多个删除
	 * @param entity
	 */
	public void delete(Serializable... dids){
		if(dids!=null){
			for(int i=0;i<dids.length;i++){
				delete(dids[i]);
			}
		}
	}
	
	/**
	 * 分页查询
	 * @param Hql HQL查询语句
	 * @param startIndex 开始位置
	 * @param pageSize 每页显示几条
	 * @param params 参数
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Integer startIndex,Integer pageSize,List<Object> params){
		//获取线程中的session
		Session session = template.getSessionFactory().getCurrentSession();
		//创建查询语句
		Query query = session.createQuery(Hql);
		//设置参数
		if(params!=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		//设置分页	参数
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		//得到查询结果
		return query.list();
	}
	
	
	/**
	 * 查询
	 * @param Hql HQL查询语句
	 * @param params 参数
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Serializable... args){
		// 获取当前Spring控制的Session
		Session session = template.getSessionFactory().getCurrentSession();
		// 创建查询
		Query query = session.createQuery(Hql);
		// 设置查询参数
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//得到查询结果
		return query.list();
	}
	
	/**
	 * 分页显示总条数
	 * @param Hql
	 * @param params
	 * @return
	 */
	public Integer queryHQLCount(String Hql,List<Object> params){
		//获取线程中的session
		Session session = template.getSessionFactory().getCurrentSession();
		//创建查询语句
		Query query = session.createQuery(Hql);
		//设置参数
		if(params!=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		//得到查询结果
		Object count = query.uniqueResult();
		return Integer.valueOf(String.valueOf(count));
	}
	
	/**
	 * 查询单一的当前对象
	 * @param Hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String Hql,Serializable...args){
		Session session = template.getSessionFactory().getCurrentSession();
		
		Query query = session.createQuery(Hql);
		if(args!=null){
			for(int i=0;i<args.length;i++){
				query.setParameter(i, args[i]);
			}
		}
		return query.uniqueResult();
	}
	
	
	public Integer updateHQL(String hql, Serializable... args) {
		// 获取当前Spring控制的Session
		Session session = template.getSessionFactory().getCurrentSession();
		// 创建查询
		Query query = session.createQuery(hql);
		// 设置查询参数
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		int row = query.executeUpdate();
		return row;
	}
	public Integer updateSQL(String sql, Serializable... args) {
		// 获取当前Spring控制的Session
		Session session = template.getSessionFactory().getCurrentSession();
		// 创建查询
		Query query = session.createSQLQuery(sql);
		// 设置查询参数
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		int row = query.executeUpdate();
		return row;
	}
	
	public abstract Class<T> getClazz();
	
	public abstract void setTemplate(HibernateTemplate template);

}
