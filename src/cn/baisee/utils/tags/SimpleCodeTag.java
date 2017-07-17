package cn.baisee.utils.tags;

import java.util.List;

import cn.baisee.entity.SimpleCode;
import cn.baisee.utils.EhcacheUtil;

public class SimpleCodeTag {
	
	public static List<SimpleCode> getCodesByType(String codetype){
		
		//ICodeService codeService = (ICodeService) ContextUtils.getBean("codeServiceImpl");
		//SimpleCode s = codeService.query(codetype);
		List<SimpleCode> list=(List<SimpleCode>) EhcacheUtil.getInstance().get("simpleCode", codetype);
		return list;
	}
	
	public static String formatSimpleCode(String code){
		SimpleCode scode= (SimpleCode) EhcacheUtil.getInstance().get("simpleCode", code);
		return scode.getValue();
	}

}
