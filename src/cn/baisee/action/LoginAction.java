package cn.baisee.action;

import java.io.IOException;
import java.io.Writer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;
import cn.baisee.entity.User;
import cn.baisee.service.IUserService;
import cn.baisee.utils.MessageUtils;
import com.opensymphony.xwork2.ModelDriven;
@Results({
		@Result(name="success",location="/WEB-INF/jsp/admin/main.jsp"),
		@Result(name="error",location="/login.jsp")
})
@ParentPackage("struts-default")
@Namespace("/")
@Controller
public class LoginAction implements ModelDriven<User>{
	
	@Resource(name = "userServiceImpl")
	private IUserService userService;
	private User user;
	
	@Action("login")
	public String login(){
		System.out.println("�ҵ�LoginAction()����");
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("logincode")==null&&!session.getAttribute("logincode").equals(user.getLoginCode())){
			MessageUtils.addMessage("��֤�����");
			return "error";
		}
		User login =userService.login(user.getUname(), user.getUpass());
		if(login==null){
			MessageUtils.addMessage("�û��������������");
			return "error";
		}
		//����Ա��½�ɹ�������Ա��Ϣ����session��
		session.setAttribute("loginUser", login);
		return "success";
	}
	
	
	/**
	 * ��ѯ�û���Ȩ����
	 */
	@Action("queryUserAuthorTree")
	public void queryUserAuthorTree(){
		//��ȡsession
		HttpSession session = ServletActionContext.getRequest().getSession();
		User loginuser = (User)session.getAttribute("loginUser");
		System.out.println("��ȡ����ԱID"+loginuser.getUserId());
		//��session�л�ȡID��ѯȨ����
		String res =  userService.queryUserAuthorTree(loginuser.getUserId());
		
		session.setAttribute("authorTree", res);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			Writer writer = response.getWriter();
			writer.write(res);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(res);
	}

	@Override
	public User getModel() {
		if(user==null){
			user = new User();
		}
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       