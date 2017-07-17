package cn.baisee.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * ����daoʵ�ֲ�
 * @author Administrator
 *
 */
public abstract class BaseDaoImpl<T> implements IBaseDao<T> {

	protected  HibernateTemplate template;

	/**
	 * �������
	 * @param entity
	 */
	public void save(Object entity){
		template.save(entity);
	}
	
	/**
	 * ���ø���
	 * @param entity
	 */
	public void update(Object entity){
		template.update(entity);
	}
	
	/**
	 * ���ñ�������
	 * @param entity
	 */
	public void saveOrUpdate(Object entity){
		template.saveOrUpdate(entity);
	}
	
	
	public T get(Serializable qid){
		return template.get(getClazz(), qid);
	}
	/**
	 * ����ɾ��
	 * @param entity
	 */
	public void delete(Serializable did){
		//ɾ��֮ǰ�Ȳ�ѯget(did)
		template.delete(get(did));
	}
	
	/**
	 * ���ö��ɾ��
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
	 * ��ҳ��ѯ
	 * @param Hql HQL��ѯ���
	 * @param startIndex ��ʼλ��
	 * @param pageSize ÿҳ��ʾ����
	 * @param params ����
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Integer startIndex,Integer pageSize,List<Object> params){
		//��ȡ�߳��е�session
		Session session = template.getSessionFactory().getCurrentSession();
		//������ѯ���
		Query query = session.createQuery(Hql);
		//���ò���
		if(params!=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		//���÷�ҳ	����
		query.setFirstResult(startIndex);
		query.setMaxResults(pageSize);
		//�õ���ѯ���
		return query.list();
	}
	
	
	/**
	 * ��ѯ
	 * @param Hql HQL��ѯ���
	 * @param params ����
	 * @return
	 */
	public List<?> queryHQLList(String Hql,Serializable... args){
		// ��ȡ��ǰSpring���Ƶ�Session
		Session session = template.getSessionFactory().getCurrentSession();
		// ������ѯ
		Query query = session.createQuery(Hql);
		// ���ò�ѯ����
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		//�õ���ѯ���
		return query.list();
	}
	
	/**
	 * ��ҳ��ʾ������
	 * @param Hql
	 * @param params
	 * @return
	 */
	public Integer queryHQLCount(String Hql,List<Object> params){
		//��ȡ�߳��е�session
		Session session = template.getSessionFactory().getCurrentSession();
		//������ѯ���
		Query query = session.createQuery(Hql);
		//���ò���
		if(params!=null){
			for(int i=0;i<params.size();i++){
				query.setParameter(i, params.get(i));
			}
		}
		//�õ���ѯ���
		Object count = query.uniqueResult();
		return Integer.valueOf(String.valueOf(count));
	}
	
	/**
	 * ��ѯ��һ�ĵ�ǰ����
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
		// ��ȡ��ǰSpring���Ƶ�Session
		Session session = template.getSessionFactory().getCurrentSession();
		// ������ѯ
		Query query = session.createQuery(hql);
		// ���ò�ѯ����
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				query.setParameter(i, args[i]);
			}
		}
		int row = query.executeUpdate();
		return row;
	}
	public Integer updateSQL(String sql, Serializable... args) {
		// ��ȡ��ǰSpring���Ƶ�Session
		Session session = template.getSessionFactory().getCurrentSession();
		// ������ѯ
		Query query = session.createSQLQuery(sql);
		// ���ò�ѯ����
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
