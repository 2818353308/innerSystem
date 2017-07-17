package cn.baisee.utils;


import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.baisee.utils.vo.Message;

/**
 * 消息处理工具
 * 
 * @author Administrator
 *
 */
public class MessageUtils {

	
	public static void addMessage(String message){
		@SuppressWarnings("unchecked")
		List<Message> errors = (List<Message>) ServletActionContext.getRequest().getAttribute("errors");
		if(errors == null){
			errors = new ArrayList<Message>();
		}
		errors.add(new Message(message));
		//设置Message到Request
		 ServletActionContext.getRequest().setAttribute("errors", errors);
	}
	
}
