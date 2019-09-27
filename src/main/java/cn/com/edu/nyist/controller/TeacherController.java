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
import cn.com.edu.nyist.biz.TClassBiz;
import cn.com.edu.nyist.biz.TeacherBiz;
import cn.com.edu.nyist.model.Choose;
import cn.com.edu.nyist.model.TClass;
import cn.com.edu.nyist.model.Teacher;
import cn.com.edu.nyist.util.MD5Utils;

@Controller
public class TeacherController {

	@Autowired
	private ChooseBiz chooseBiz;
	@Autowired
	private TClassBiz tClassBiz;
	@Autowired
	private TeacherBiz teacherBiz;
	
//查看选我的学生名单
	@RequestMapping("selectMe")
	public String selectMe(HttpServletRequest request,Model model) {
		
		int teaId=((Teacher)request.getSession().getAttribute("user")).getId();//教师的ID
		System.out.println(teaId+"******");
		List<Choose> ls=chooseBiz.findByTeaId(teaId);//查询该教师教授的byId
		model.addAttribute("ls", ls);
		return "selectMe";
	}

	// 教师踢人操作
	@RequestMapping("deleteClass")
	@ResponseBody
	public int deleteClass(@RequestParam int id, Model model) {
		// 删除前根据id找到choose表里面对应的claId
		Choose choose = chooseBiz.findByClaId(id);
		TClass tClass = tClassBiz.finById(choose.getClaId());
		// 退课成功课程可选人数+1
		tClass.setRest_count(tClass.getRest_count() - 1);
		tClassBiz.save(tClass);
		// 删除该数据
		chooseBiz.deleteById(id);

		return 0;
	}
	//教师打分
	@RequestMapping("toScore")
	public String  toScore(Model model,@RequestParam int id) {
		
		Choose choose= chooseBiz.findById(id);
		model.addAttribute("id",choose.getId() );
		model.addAttribute("cname",choose.getCname() );
		model.addAttribute("cnum",choose.getCnum() );
		model.addAttribute("sname",choose.getSname() );
		model.addAttribute("snum",choose.getSnum() );
		model.addAttribute("score",choose.getScore() );
		return "score";
	}
	@RequestMapping("doScoreEdit")
	public String  doScore(HttpServletRequest request,@RequestParam int id) {
		
		Choose choose = chooseBiz.findById(id);
		String score = request.getParameter("score");
		choose.setScore(Integer.parseInt(score));
		chooseBiz.update(choose);
		return "redirect:selectMe";
	}
	// 教师修改密码
	@RequestMapping("toPwdEditTeacher")
	public String toEditAdmin() {
		return "pwdEditTeacher";
	}
	
	@RequestMapping("pwdEditTeacher")
	@ResponseBody
	public int doEditTeacher(HttpServletRequest request, Model model, HttpSession session) {
		int id = ((Teacher) request.getSession().getAttribute("user")).getId();// 学生的ID
		// 找到对应的id用户
		Teacher teacher = teacherBiz.findById(id);
		// 得到界面输入框中的值
		String pwds = MD5Utils.getMD5(request.getParameter("pwds"));
		teacher.setPwd(pwds);
		teacherBiz.save(teacher);
		return 0;
	}
	
	@RequestMapping("teacherDetail")
	@ResponseBody
	public Teacher detail(Integer id){
		Teacher teacher = teacherBiz.findById(id);	
		return teacher;
	}
	
	/*@RequestMapping("tDetail")
	@ResponseBody
	public Teacher tdetail(Integer tid){
		Teacher teacher = teacherBiz.findById(tid);	
		return teacher;
	}*/
}
