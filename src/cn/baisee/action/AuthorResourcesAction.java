package cn.baisee.action;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import cn.baisee.entity.AuthorResources;
import cn.baisee.entity.PageVo;
import cn.baisee.service.IAuthorResourcesService;

import com.opensymphony.xwork2.ModelDriven;

@Results({
	@Result(name="toList",location="/WEB-INF/jsp/admin/res/toList.jsp"),
	@Result(name="toAdd",location="/WEB-INF/jsp/admin/res/add.jsp"),
	@Result(name="add",type="redirectAction",params={"namespace","/res","actionName","toAdd","res.id","%{res.id}"}),
	@Result(name="toListhtmlx",type="redirectAction",params={"namespace","/res","actionName","toList"}),
	@Result(name="login",location="/login.jsp")
})
@ParentPackage("authorPackAge")
@Namespace("/res")
@Controller
public class AuthorResourcesAction implements ModelDriven<PageVo>{

	private PageVo pageVo;
	
	private AuthorResources res;
	
	@Resource(name = "authorResourcesServiceImpl")
	private IAuthorResourcesService authorResourcesService;
	
	@Action("toList")
	public String toList(){
		System.out.println("进图0000");
		pageVo = authorResourcesService.toList(pageVo);
		System.out.println(pageVo);
		return "toList";
	}
	
	@Action("toAdd")
	public String toAdd(){
		return "toAdd";
	}
	
	//添加或更新节点
	@Action("add")
	public String add(){
		res.setCreateTs(new Date());
		authorResourcesService.saveOrUpdate(res);
		return "toAdd";
	}
	
	
	@Action("exc")
	public String Exc() throws Exception{
		if(true){
			throw new RuntimeException("123");
		}
		return "toListhtmlx";
	}
	
	/**
	 * 删除节点要把自己的所有子节点删除干净
	 * @return
	 */
	@Action("todelete")
	public String todelete(){
		authorResourcesService.delete(res.getId());
		return "toListhtmlx";
	}
	
	

	/**
	 * 查询权限树  以json数据形式响应
	 */
	@Action("all")
	public void all(){
		String results = authorResourcesService.queryAll();
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

	public AuthorResources getRes() {
		return res;
	}

	public void setRes(AuthorResources res) {
		this.res = res;
	}
	
	

}
