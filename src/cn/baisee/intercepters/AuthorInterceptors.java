package cn.baisee.intercepters;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * 权限拦截机制
 * @author Administrator
 *
 */
public class AuthorInterceptors implements Interceptor{
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation invo) throws Exception {
		System.out.println("进入拦截");
		//获取请求对象
		HttpServletRequest request = (HttpServletRequest) invo.getInvocationContext().get(ServletActionContext.HTTP_REQUEST); 
		//得到权限树
		String authorTree = (String)request.getSession().getAttribute("authorTree");
		//request提供的可以获取请求路径
		String requestURI = request.getRequestURI();
		String requestPath = requestURI.replace("innerSystem/", "");
		//System.out.println(requestPath);
		//对比是否有这个权限
		if(authorTree!=null&&authorTree.indexOf(requestPath)!=-1){
			System.out.println("-----------");
			return  invo.invoke();
		}
		return "login";
	}

}
