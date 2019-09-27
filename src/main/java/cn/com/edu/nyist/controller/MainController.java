package cn.com.edu.nyist.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.edu.nyist.biz.AdminBiz;
import cn.com.edu.nyist.model.Admin;



@Controller
public class MainController {
	
@Autowired
private AdminBiz adminBiz;

	//到主页
	@RequestMapping("main")
	public String toMain() {
		
		return "main";
	}
	@RequestMapping("main1")
	public String toMain1() {
		
		return "test";
	}

	// 管理员修改密码
		@RequestMapping("toPwdEdit")
		public String toEdit() {
			return "pwdEditAdmin";
		}

		@RequestMapping("pwdEditAdmin")
		@ResponseBody
		public int doEdit(HttpServletRequest request, Model model, HttpSession session) {
			int id=((Admin)request.getSession().getAttribute("user")).getId();//学生的ID
			//找到对应的id用户
			Admin admin= adminBiz.findById(id);
			//得到界面输入框中的值
			String pwds = request.getParameter("pwds");
			//判断数据库里面的密码和用户输入的密码是否一致
			
				//将数据库密码设置为用户输入的值，
				admin.setPwd(pwds);
				adminBiz.save(admin);
			    return 0;

		}
		
		

			

				
	
}
