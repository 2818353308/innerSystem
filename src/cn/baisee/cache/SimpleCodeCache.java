package cn.baisee.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.baisee.entity.SimpleCode;
import cn.baisee.service.ICodeService;
import cn.baisee.utils.EhcacheUtil;

/**
 * 简单代码缓存
 * @author Administrator
 */
@Component
public class SimpleCodeCache {

	@Resource(name = "codeServiceImpl")
	ICodeService codeService;
	
	//spring初始化时调用此方法
	@PostConstruct
	public void init(){
		System.out.println("进入初始化方法");
		//查询所有的结果集
		List<SimpleCode> results=codeService.queryAll();
		Map<String, List<SimpleCode>> maps = new HashMap<String, List<SimpleCode>>();
		//存放缓存
		for(SimpleCode item : results){
			System.out.println(item.getValue());
			EhcacheUtil.getInstance().put("simpleCode", item.getCode(),item);
			//分析当前简单代码的类别是什么？
			List<SimpleCode> list=maps.get(item.getCodetype());
			if (list == null) {
				list=new ArrayList<>();
				list.add(item);
				System.out.println(list);
				maps.put(item.getCodetype(), list);
			}
			else{
				list.add(item);
				maps.put(item.getCodetype(), list);
			}
		}
		//根据类别进行分类缓存
		Iterator<String> keys = maps.keySet().iterator(); 
		while(keys.hasNext()){
			String key = keys.next();
			System.out.println(key+"========"+maps.get(key));
			EhcacheUtil.getInstance().put("simpleCode", key, maps.get(key));
		}
		System.out.println("初始化进入此方法----------");
		
	}
	
	
}
