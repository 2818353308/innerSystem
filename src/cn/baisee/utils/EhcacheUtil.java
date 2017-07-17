package cn.baisee.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类
 * @author Administrator
 *
 */
public class EhcacheUtil {
	
	//缓存管理器
	private CacheManager manager;  
	
	//缓存工具类
    private static EhcacheUtil ehCache;  
    
    //构造函数
    private EhcacheUtil() {  
        manager = CacheManager.create(EhcacheUtil.class.getClassLoader().getResourceAsStream("ehcache.xml"));  
    }  
    
    //创建当前单例的对象
    public static EhcacheUtil getInstance() {  
        if (ehCache== null) {  
            ehCache= new EhcacheUtil();  
        }  
        return ehCache;  
    }  
    
    /**
     * 存放一个缓存
     * @param cacheName 缓存名称
     * @param key 缓存Key
     * @param value 缓存值
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
    
    //清空当前缓存
    public void clear(String cacheName) {  
        Cache cache = manager.getCache(cacheName);  
        cache.removeAll();
    }  
    
    public static void main(String[] args) {
		EhcacheUtil.getInstance().put("simpleCode", "cn.baisee.demo", "百思缓存测试");
		System.out.println(EhcacheUtil.getInstance().get("simpleCode", "cn.baisee.demo"));
	}	
}
