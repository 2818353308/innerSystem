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
 * �򵥴��뻺��
 * @author Administrator
 */
@Component
public class SimpleCodeCache {

	@Resource(name = "codeServiceImpl")
	ICodeService codeService;
	
	//spring��ʼ��ʱ���ô˷���
	@PostConstruct
	public void init(){
		System.out.println("�����ʼ������");
		//��ѯ���еĽ����
		List<SimpleCode> results=codeService.queryAll();
		Map<String, List<SimpleCode>> maps = new HashMap<String, List<SimpleCode>>();
		//��Ż���
		for(SimpleCode item : results){
			System.out.println(item.getValue());
			EhcacheUtil.getInstance().put("simpleCode", item.getCode(),item);
			//������ǰ�򵥴���������ʲô��
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
		//���������з��໺��
		Iterator<String> keys = maps.keySet().iterator(); 
		while(keys.hasNext()){
			String key = keys.next();
			System.out.println(key+"========"+maps.get(key));
			EhcacheUtil.getInstance().put("simpleCode", key, maps.get(key));
		}
		System.out.println("��ʼ������˷���----------");
		
	}
	
	
}
