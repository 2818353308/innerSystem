package cn.baisee.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * ���湤����
 * @author Administrator
 *
 */
public class EhcacheUtil {
	
	//���������
	private CacheManager manager;  
	
	//���湤����
    private static EhcacheUtil ehCache;  
    
    //���캯��
    private EhcacheUtil() {  
        manager = CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream("ehcache.xml"));  
    }  
    
    //������ǰ�����Ķ���
    public static EhcacheUtil getInstance() {  
        if (ehCache== null) {  
            ehCache= new EhcacheUtil();  
        }  
        return ehCache;  
    }  
    
    /**
     * ���һ������
     * @param cacheName ��������
     * @param key ����Key
     * @param value ����ֵ
     */
    public void put(String cacheName, String key, Object value) {  
        Cache cache = manager.getCache(cacheName); 
        Element element = new Element(key, value);  
        cache.put(element);  
    }  
  
    public Object get(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        Element element = cache.get(key);  
        return element == null ? null : element.getObjectValue();  
    }  
  
    public Cache get(String cacheName) {  
        return manager.getCache(cacheName);  
    }  
  
    public void remove(String cacheName, String key) {  
        Cache cache = manager.getCache(cacheName);  
        cache.remove(key);  
    }  
    
    //��յ�ǰ����
    public void clear(String cacheName) {  
        Cache cache = manager.getCache(cacheName);  
        cache.removeAll();
    }  
    
    public static void main(String[] args) {
		EhcacheUtil.getInstance().put("simpleCode", "cn.baisee.demo", "��˼�������");
		System.out.println(EhcacheUtil.getInstance().get("simpleCode", "cn.baisee.demo"));
	}	
}
