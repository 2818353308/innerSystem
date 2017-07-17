package cn.baisee.intercepters;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * 异常处理机制
 * @author Administrator
 *
 */
public class ExceptionInterceptor implements Interceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		
		try {
			return invo.invoke();
		} catch (Exception e) {
			e.printStackTrace();
			String cl = e.getClass().getSimpleName();
			System.out.println("报错了=====异常类型="+cl);
			return "errorPage";
		}
		
	}

}
