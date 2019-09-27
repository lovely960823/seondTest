package cn.com.edu.nyist.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.edu.nyist.biz.ChooseBiz;
import cn.com.edu.nyist.biz.StudentBiz;
import cn.com.edu.nyist.biz.TClassBiz;
import cn.com.edu.nyist.model.Choose;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.model.TClass;
import cn.com.edu.nyist.util.MD5Utils;

@Controller
public class StudentController {

	
	@Autowired
	ChooseBiz chooseBiz;
	@Autowired
	private TClassBiz tClassBiz;
	@Autowired
	private StudentBiz studentBiz;
//学生查看自己选的课程，根据自己的ID查询
	/**
	 * 学生查看自己选的课程
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("mySelect")
	public String mySelect(HttpServletRequest request,Model model) {
		
		int stuId=((Student)request.getSession().getAttribute("user")).getId();//学生的ID
		System.out.println(stuId+"******");
		List<Choose> ls=chooseBiz.findByStuId(stuId);//查询某学生的选课信息By学生ID
		System.out.println(ls);
		model.addAttribute("ls", ls);
		return"mySelect";
	}

   /**
              * 学生退课操作
    * @param id
    * @param request
    * @param model
    * @return
    */
	@RequestMapping("exitClass")
	@ResponseBody
	public int exitClass(@RequestParam int id,HttpServletRequest request,Model model) {
		//删除前根据id找到choose表里面对应的claId
		Choose choose=chooseBiz.findByClaId(id);
		TClass tClass=tClassBiz.finById(choose.getClaId());
		//退课成功课程可选人数+1
		tClass.setRest_count(tClass.getRest_count()-1);
		tClassBiz.save(tClass);
		//删除该数据
		chooseBiz.deleteById(id);
		
		return 0;
	}
	// 学生修改密码
	@RequestMapping("toPwdEditStudent")
	public String toEditStudent() {
		return "pwdEditStudent";
	}

	@RequestMapping("pwdEditStudent")
	@ResponseBody
	public int doEditStudent(HttpServletRequest request, Model model, HttpSession session) {
		int id = ((Student) request.getSession().getAttribute("user")).getId();// 学生的ID
		// 找到对应的id用户
		Student student = studentBiz.findById(id);
		// 得到界面输入框中的值
		String pwds = MD5Utils.getMD5(request.getParameter("pwds"));
		student.setPwd(pwds);
		studentBiz.save(student);
		return 0;
	}
	
	@RequestMapping("studentDetail")
	@ResponseBody
	public Student detail(Integer id){
		Student student = studentBiz.findById(id);	
		return student;
	}
}
