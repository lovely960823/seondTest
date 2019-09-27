package cn.com.edu.nyist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.edu.nyist.biz.ChooseBiz;
import cn.com.edu.nyist.biz.TClassBiz;
import cn.com.edu.nyist.model.Choose;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.model.TClass;

@Controller
public class TClassController {
	
	@Autowired
	private TClassBiz tClassBiz;

	@Autowired
	private ChooseBiz chooseBiz;
	
	
	@RequestMapping("selectClass")
	@ResponseBody
	public int Select(@RequestParam int id,@RequestParam int tid,HttpServletRequest request,Model model,
			@RequestParam int timeId,@RequestParam int locationId) {
		int studentId=((Student)request.getSession().getAttribute("user")).getId();//学生的ID
		//根据教师和课程和自己的ID进行判断自己是否选过
		Choose choose = chooseBiz.findClass(id,tid,studentId);
		
		//判断是否上课地点时间冲突
		Choose ch1 = chooseBiz.findByTimeAdnLocation(studentId,timeId,locationId);
		
		TClass tClass = tClassBiz.finById(id);
		
		if(choose!=null) {
			return 1;  //已经选过返回1
		}
		
		if(ch1!=null) {
			return 3;//冲突返回3(如果选过，就肯定冲突)
		}
		
		if(tClass.getRest_count()<tClass.getCount()) {
			tClassBiz.doSelect(id,tid,studentId,timeId,locationId);
			return 0;
		}else {
			return 4;  //可选人数不足
		}
		
	}
	
}
