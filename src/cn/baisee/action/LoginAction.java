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
		System.out.println("找到LoginAction()方法");
		HttpSession session = ServletActionContext.getRequest().getSession();
		if(session.getAttribute("logincode")==null&&!session.getAttribute("logincode").equals(user.getLoginCode())){
			MessageUtils.addMessage("认证码错误");
			return "error";
		}
		User login =userService.login(user.getUname(), user.getUpass());
		if(login==null){
			MessageUtils.addMessage("用户名或者密码错误！");
			return "error";
		}
		//管理员登陆成功将管理员信息放入session中
		session.setAttribute("loginUser", login);
		return "success";
	}
	
	
	/**
	 * 查询用户的权限树
	 */
	@Action("queryUserAuthorTree")
	public void queryUserAuthorTree(){
		//获取session
		HttpSession session = ServletActionContext.getRequest().getSession();
		User loginuser = (User)session.getAttribute("loginUser");
		System.out.println("获取管理员ID"+loginuser.getUserId());
		//从session中获取ID查询权限树
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
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       