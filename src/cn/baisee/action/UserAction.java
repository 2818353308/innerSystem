package cn.baisee.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;

import cn.baisee.entity.PageVo;
import cn.baisee.entity.User;
import cn.baisee.service.IUserService;

@Results({
	@Result(name="toList",location="/WEB-INF/jsp/admin/user/toList.jsp"),
	@Result(name="toAuthor",location="/WEB-INF/jsp/admin/user/toAuthor.jsp"),
	@Result(name="ftoList",type="redirectAction",params={"actionName","toList"}),
})
@ParentPackage("struts-default")
@Namespace("/user")
@Controller
public class UserAction implements ModelDriven<PageVo>{
	
	private PageVo pageVo;
	private User user;
	
	@Resource(name = "userServiceImpl")
	private IUserService userService;
	
	@Action("toList")
	public String toList(){
		
		
		pageVo = userService.toList(pageVo);
		return "toList";
	}
	/**
	 * 跳转带用户授权角色页面
	 * @return
	 */
	@Action("toAuthor")
	public String toAuthor(){
		return "toAuthor";
	}
	@Action("saveAuthor")
	public String saveAuthor(){
		//选中的角色
		String roleIds=ServletActionContext.getRequest().getParameter("roleIds");
		//用户ID
		String userId=ServletActionContext.getRequest().getParameter("userId");
		userService.saveUserRole(userId, roleIds);
		return "ftoList";
	}

	@Override
	public PageVo getModel() {
		if(pageVo == null){
			pageVo = new PageVo();
		}
		return pageVo;
	}
	public PageVo getPageVo() {
		return pageVo;
	}

	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
