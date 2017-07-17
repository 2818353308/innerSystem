package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * ����dao��
 * @author Administrator
 *
 */
public interface IBaseDao<T> {

	/**
	 * �������
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * ���ø���
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * ���ñ�������
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);
	
	
	public T get(Serializable qid);
	/**
	 * ����ɾ��
	 * @param entity
	 */
	public void delete(Serializable did);
	
	/**
	 * ���ö��ɾ��
	 * @param entity
	 */
	public void delete(Serializable... dids);
	
	/**
	 * ��ҳ��ѯ
	 * @param Hql HQL��ѯ���
	 * @param startIndex ��ʼλ��
	 * @param pageSize ÿҳ��ʾ����
	 * @param params ����
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Integer startIndex,Integer pageSize,List<Object> params);
	
	/**
	 * ��ѯ
	 * @param Hql HQL��ѯ���
	 * @param startIndex ��ʼλ��
	 * @param pageSize ÿҳ��ʾ����
	 * @param params ����
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Serializable... args);
	
	
	/**
	 * ��ҳ��ʾ������
	 * @param Hql
	 * @param params
	 * @return
	 */
	public Integer queryHQLCount(String Hql,List<Object> params);
	
	/**
	 * ��ѯ��һ�ĵ�ǰ����
	 * @param Hql
	 * @param args
	 * @return
	 */
	public Object queryHqlUnique(String Hql,Serializable...args);
	
	
	public Integer updateHQL(String hql, Serializable... args);
	
	public Integer updateSQL(String sql, Serializable... args);
	
	
}
