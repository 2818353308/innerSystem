package cn.baisee.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

import cn.baisee.utils.ImageUtils;

@ParentPackage("struts-default")
@Namespace("/code")
@Controller
public class VCodeAction {
	
	@Action("vlogin")
	public void vcode() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		//设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//设置响应格式为图片
		response.setContentType("image/jpeg");
		//获取认证码图片
		BufferedImage vcodeImage = ImageUtils.createCode(ServletActionContext.getRequest().getSession(), "logincode");
		
		//输出图片到前台页面
		ImageIO.write(vcodeImage, "jpeg", response.getOutputStream());
		
	}

}
