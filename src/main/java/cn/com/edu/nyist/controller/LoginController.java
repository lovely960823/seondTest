package cn.com.edu.nyist.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.com.edu.nyist.biz.AdminBiz;
import cn.com.edu.nyist.biz.StudentBiz;
import cn.com.edu.nyist.biz.TeacherBiz;
import cn.com.edu.nyist.model.Admin;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.model.Teacher;

@Controller
public class LoginController {

	@Autowired
	private TeacherBiz teacherBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AdminBiz adminBiz;
	@RequestMapping("/toLogin")
	public String toLogin() {
		
		return "login";
	}
	
	
	@RequestMapping("login")
	public String login(Model model,@RequestParam int num,@RequestParam String pwd,
			@RequestParam String vcode,HttpSession session,HttpServletRequest request) {
		//验证码
		String serverVcode = (String) session.getAttribute(ValidateController.serverVcodeName);
		if(!serverVcode.equalsIgnoreCase(vcode)) {
			model.addAttribute("msg", "验证码错误");
			return "login";
		}
		
		   String type=request.getParameter("type");
		   //1用来判断管理员 2用来判断教师  3用来判断学生
		   if(type.equalsIgnoreCase("1")) {    //判断管理员
				 //查询数据库
					//Admin admin=adminBiz.findByNameAndPwd(name,pwd);
			          Admin admin=adminBiz.findByNameAndNum(num,pwd);
					if(admin!=null) {
						session.setAttribute("user", admin);
						return "main";
					}else {
						model.addAttribute("msg", "用户名或者密码错误");
						return "login";
					}
			  }
		   if(type.equalsIgnoreCase("2")) {    //判断教师
			 //查询数据库
				Teacher teacher=teacherBiz.findByNumAndPwd(num,pwd);
				if(teacher!=null) {
					session.setAttribute("user", teacher);
					return "redirect:classListTeacher";
				}else {
				model.addAttribute("msg", "用户名或者密码错误");
				return "login";
				}
		   }
		    if(type.equalsIgnoreCase("3")){  //判断学生
			   
			   Student student=studentBiz.findByNumAndPwd(num,pwd);
			   if(student!=null) {
					session.setAttribute("user", student);
					
					return "redirect:classList";
				}else {
				model.addAttribute("msg", "用户名或者密码错误");
				return "login";
				}
		   }
			return "login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:toLogin";
	}
}
