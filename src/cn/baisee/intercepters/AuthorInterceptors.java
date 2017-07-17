package cn.baisee.intercepters;



import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
/**
 * Ȩ�����ػ���
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
		System.out.println("��������");
		//��ȡ�������
		HttpServletRequest request = (HttpServletRequest) invo.getInvocationContext().get(ServletActionContext.HTTP_REQUEST); 
		//�õ�Ȩ����
		String authorTree = (String)request.getSession().getAttribute("authorTree");
		//request�ṩ�Ŀ��Ի�ȡ����·��
		String requestURI = request.getRequestURI();
		String requestPath = requestURI.replace("innerSystem/", "");
		//System.out.println(requestPath);
		//�Ա��Ƿ������Ȩ��
		if(authorTree!=null&&authorTree.indexOf(requestPath)!=-1){
			System.out.println("-----------");
			return  invo.invoke();
		}
		return "login";
	}

}
