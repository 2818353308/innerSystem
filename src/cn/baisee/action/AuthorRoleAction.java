package cn.baisee.action;


import java.io.IOException;
import java.io.Writer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import cn.baisee.entity.AuthorRole;
import cn.baisee.entity.PageVo;
import cn.baisee.service.IAuthorRoleService;

import com.opensymphony.xwork2.ModelDriven;

@Results({
	@Result(name="toList",location="/WEB-INF/jsp/admin/role/toList.jsp"),
	@Result(name="toAuthor",location="/WEB-INF/jsp/admin/role/toAuthor.jsp"),
	@Result(name="ftoList",type="redirectAction",params={"namespace","/role","actionName","toList"})
})
@ParentPackage("struts-default")
@Namespace("/role")
@Controller
public class AuthorRoleAction implements ModelDriven<PageVo>{

	private PageVo pageVo;
	private AuthorRole authorRole;
	
	@Resource(name = "authorRoleServiceImpl")
	private IAuthorRoleService authorRoleServiceImpl;
	
	@Action("toList")
	public String toList(){
		pageVo = authorRoleServiceImpl.toList(pageVo);
		return "toList";
	}
	
	/**
	 * 去授权页面
	 * @return
	 */
	@Action("toAuthor")
	public String toAuthor(){
		return "toAuthor";
	}
	
	/**
	 * 进行授权
	 * @return
	 */
	@Action("saveAuthorRole")
	public String saveAuthorRole(){
		System.out.println("进行授权");
		//获取提交的参数
		String resourcesIds=ServletActionContext.getRequest().getParameter("resourcesIds");
		String roleId=ServletActionContext.getRequest().getParameter("roleId");
		//保存关联关系
		authorRoleServiceImpl.saveAuthorRole(roleId, resourcesIds);
		return "ftoList";
	}
	
	
	/**
	 * 查询角色树  以json数据形式响应
	 */
	@Action("all")
	public void all(){
		String results = authorRoleServiceImpl.queryAll();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			Writer writer = response.getWriter();
			writer.write(results);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(results);
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
	public AuthorRole getAuthorRole() {
		return authorRole;
	}
	public void setAuthorRole(AuthorRole authorRole) {
		this.authorRole = authorRole;
	}
	
}
