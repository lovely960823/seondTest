package cn.com.edu.nyist.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.edu.nyist.biz.ChooseBiz;
import cn.com.edu.nyist.biz.LocationBiz;
import cn.com.edu.nyist.biz.StudentBiz;
import cn.com.edu.nyist.biz.TClassBiz;
import cn.com.edu.nyist.biz.TeacherBiz;
import cn.com.edu.nyist.biz.TimeBiz;
import cn.com.edu.nyist.model.Location;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.model.TClass;
import cn.com.edu.nyist.model.Teacher;
import cn.com.edu.nyist.model.Time;
import cn.com.edu.nyist.util.MD5Utils;

@Controller
public class SuperController {
	@Autowired
	private TClassBiz tClassBiz;
	@Autowired
	private TeacherBiz teacherBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private LocationBiz locationBiz;
	@Autowired
	private TimeBiz timeBiz;
	@Autowired
	private ChooseBiz chooseBiz;
	@RequestMapping("toTeacherSelf")
	public String toTeacherSelf(Model model) {

		return "teacherSelf";
	}

	// 对于教师一系列操作
	@RequestMapping("toTeacherAdd")
	public String toTeacherAdd(Model model) {

		model.addAttribute("teacher", new Teacher());
		return "teacherAdd";
	}

	@RequestMapping("doTeacherAdd")
	@ResponseBody
	public int toTeacherAdd(@Valid @ModelAttribute Teacher teacher, BindingResult result, HttpServletRequest request,
		Model model,@RequestParam MultipartFile photoFile
		/*@RequestParam(value="photoFile",required=false) MultipartFile file*/) throws Exception{
		/*String rootPath = request.getSession().getServletContext().getRealPath("/");
		String path="upload/teacher/";
		String fileName = "";*/
		// 查询数据库里是否有该对象
		int num = teacher.getNum();
		Teacher t1 = teacherBiz.findByNum(num);
		if (t1 == null) {
			/*if(file!=null){
				fileName = file.getOriginalFilename();//判断文件是否存在
			    if(!"".equals(fileName)&&fileName!=null) {//判断文件名是否为空
				// 解决IE下路径问题
				fileName = fileName.lastIndexOf("\\") == -1 ? fileName :
				fileName.substring(fileName.lastIndexOf("\\") + 1 );
				// 解决名字重复问题
				String ext = fileName.substring(fileName.lastIndexOf('.') + 1 );
				String newFileName = UUID.randomUUID().toString() + "." + ext;
				file.transferTo(new File(rootPath+path + newFileName)); 
			 	teacher.setPhoto(path+newFileName);
			    }else {
			    teacher.setPhoto(null);
			    }
			}*/
			String fileName = photoFile.getOriginalFilename();
			if (!fileName.equals("")) {
				// 解决IE下路径问题
				fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
				String ext=fileName.substring(fileName.lastIndexOf(".")+1);
				String newFileName=UUID.randomUUID()+"."+ext;
				photoFile.transferTo(new File(request.getServletContext().getRealPath("WEB-INF/upload/teacher/" + newFileName)));
				teacher.setPhoto("upload/teacher/"+newFileName);
			} else {
				teacher.setPhoto(null);
			}
			
			teacher.setPwd(MD5Utils.getMD5("123456"));
			// 调用jpa自身的方法插入一条数据
			teacherBiz.save(teacher);
			return 0;// 代表成功
		} else {
			return 1;// 代表失败
		}

	}

	

	// 到教师修改界面
	@RequestMapping("toTeacherEdit")
	public String toTeacherEdit(Model model, @RequestParam int id) {
		Teacher teacher = teacherBiz.findById(id);
		model.addAttribute("teacher", teacher);
		return "teacherEdit";
	}

	// 教师修改操作
	@RequestMapping("doTeacherEdit")
	@ResponseBody
	public int teacherEdit(Teacher teacher, HttpServletRequest request, Model model,@RequestParam MultipartFile photoFile
			/*@RequestParam(value="photoFile",required=false) MultipartFile file*/) throws Exception{
		/*String rootPath = request.getSession().getServletContext().getRealPath("/");
		String path="upload/teacher/";
		String fileName = "";*/
		String str = request.getParameter("id");
		Teacher x= teacherBiz.findById(Integer.parseInt(str));
		/*if(file!=null){
			fileName = file.getOriginalFilename();//判断文件是否存在
		    if(!"".equals(fileName)&&fileName!=null) {//判断文件名是否为空
		    	//到这一步说明选择了文件，删除旧文件
		    	File f = new File(rootPath+path+x.getPhoto());
				f.delete();
			// 解决IE下路径问题
			fileName = fileName.lastIndexOf("\\") == -1 ? fileName :
			fileName.substring(fileName.lastIndexOf("\\") + 1 );
			// 解决名字重复问题
			String ext = fileName.substring(fileName.lastIndexOf('.') + 1 );
			String newFileName = UUID.randomUUID().toString() + "." + ext;
			file.transferTo(new File(rootPath+path + newFileName)); 
			
		 	teacher.setPhoto(path+newFileName);
		    }else {
		    teacher.setPhoto(x.getPhoto());
		    }
		}*/
		String fileName = photoFile.getOriginalFilename();
		if (!fileName.equals("")) {
			// 解决IE下路径问题
			fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
			String ext=fileName.substring(fileName.lastIndexOf(".")+1);
			String newFileName=UUID.randomUUID()+"."+ext;
			photoFile.transferTo(new File(request.getServletContext().getRealPath("WEB-INF/upload/teacher/" + newFileName)));
			teacher.setPhoto("upload/teacher/"+newFileName);
		} else {
			teacher.setPhoto(x.getPhoto());
		}
		if (teacherBiz.update(teacher) != null) {
			return 0;
		} else {

			return 1;
		}

	}
	
	// 教师列表
		@RequestMapping("toTeacherList")
		public String toTeacherList(Model model,@RequestParam(defaultValue = "1", required = false) int pageNo) {
			//List<Teacher> ls = teacherBiz.findAll();
			//model.addAttribute("ls", ls);
			Page<Teacher> pageInfo = teacherBiz.findAllTeacher(pageNo);
			model.addAttribute("pageInfo", pageInfo);
			return "teacherList";
		}

		// 教师删除
		@RequestMapping("teacherDel")
		@ResponseBody
		public int delTeacher(@RequestParam int id,HttpServletRequest request) {
			//String rootPath = request.getSession().getServletContext().getRealPath("/");
			//String path="upload/teacher/";
			Teacher x = teacherBiz.findById(id);
 			File f = new File("WEB-INF/upload/teacher/"+x.getPhoto());
			f.delete();
			teacherBiz.delete(id);
			chooseBiz.deleteByTid(id);
			return 0;
		}

		// 教师密码重置
		@RequestMapping("toResetTeaPwd")
		@ResponseBody
		public int ResetPwd(@RequestParam int id) {
			Teacher teacher = teacherBiz.findById(id);
			teacher.setPwd(MD5Utils.getMD5("123456"));
			// teacherBiz.update(teacher);
			if (teacherBiz.update(teacher) != null) {
				return 0;
			}
			return 1;

		}

	// 对于学生的操作
	// 学生添加页面
	@RequestMapping("toStudentAdd")
	public String toStudentAdd(Model model) {

		model.addAttribute("student", new Student());
		return "studentAdd";
	}

	// 学生添加动作
	@RequestMapping("doStudentAdd")
	@ResponseBody
	public int toStudentAdd(Student student, Model model, HttpServletRequest request,
			@RequestParam MultipartFile photoFile) throws Exception {
		int num = student.getNum();
		Student s = studentBiz.findByNum(num);
		if (s == null) {
			String fileName = photoFile.getOriginalFilename();
			if (!fileName.equals("")) {
				// 解决IE下路径问题
				fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
				String ext=fileName.substring(fileName.lastIndexOf(".")+1);
				String newFileName=UUID.randomUUID()+"."+ext;
				photoFile.transferTo(new File(request.getServletContext().getRealPath("WEB-INF/upload/student/" + newFileName)));
				student.setPhoto("upload/student/"+newFileName);
			} else {
				student.setPhoto(null);
			}
			student.setPwd(MD5Utils.getMD5("123456"));
			// 调用jpa自身的方法插入一条数据
			studentBiz.save(student);
			return 0;
		} else {
			return 1;
		}
	}

	// 学生删除
	@RequestMapping("studentDel")
	@ResponseBody
	public int delStudent(@RequestParam int id) {
		Student x = studentBiz.findById(id);
		File f = new File("WEB-INF/upload/student/"+x.getPhoto());
		f.delete();
		studentBiz.delete(id);
		chooseBiz.deleteBySid(id);
		return 0;
	}

	// 重置密码
	@RequestMapping("toResetStuPwd")
	@ResponseBody
	public int Reset(@RequestParam int id) {
		Student student = studentBiz.findById(id);
		student.setPwd(MD5Utils.getMD5("123456"));
		if (studentBiz.update(student) != null) {
			return 0;
		}
		return 1;
	}

	// 学生列表
	@RequestMapping("toStudentList")
	public String toStudentList(Model model,@RequestParam(defaultValue = "1", required = false) int pageNo) {
		//List<Student> ls = studentBiz.findAll();
		//model.addAttribute("ls", ls);
		Page<Student> pageInfo = studentBiz.findAllStudent(pageNo);
		model.addAttribute("pageInfo", pageInfo);
		return "studentList";
	}

	// 到学生修改界面
	@RequestMapping("toStudentEdit")
	public String toStudentEdit(Model model, @RequestParam int id) {
		Student student = studentBiz.findById(id);
		model.addAttribute("student", student);
		return "studentEdit";
	}

	@RequestMapping("studentEdit")
	@ResponseBody
	public int studentEdit(Student student, HttpServletRequest request,
			Model model,@RequestParam MultipartFile photoFile) throws Exception{
		String str = request.getParameter("id");
		Student x= studentBiz.findById(Integer.parseInt(str));
		String fileName = photoFile.getOriginalFilename();
		if (!fileName.equals("")) {
			// 解决IE下路径问题
			fileName = fileName.lastIndexOf("\\") == -1 ? fileName : fileName.substring(fileName.lastIndexOf("\\") + 1);
			String ext=fileName.substring(fileName.lastIndexOf(".")+1);
			String newFileName=UUID.randomUUID()+"."+ext;
			photoFile.transferTo(new File(request.getServletContext().getRealPath("WEB-INF/upload/student/" + newFileName)));
			student.setPhoto("upload/student/"+newFileName);
		} else {
			student.setPhoto(x.getPhoto());
		}
		Student s = studentBiz.update(student);
		if (s != null) {

			return 0;

		} else {

			return 1;
		}
	}
	// 课程的添加

	@RequestMapping("toClassAdd")
	public String toClassAdd(Model model) {
		TClass tClass = new TClass();
		List<Teacher> ls = teacherBiz.findAll();
		List<Location> location = locationBiz.findAll();//拿到位置
		List<Time> time = timeBiz.findAll();//拿到时间
		model.addAttribute("types", ls);// 拿到教师
		//model.addAttribute("types", ls);
		model.addAttribute("location", location);
		model.addAttribute("time", time);
		model.addAttribute("tClass", tClass);
		return "classAdd";
	}

	@RequestMapping("doClassAdd")
	@ResponseBody
	public int doClassAdd(TClass tClass, Model model, HttpServletRequest request, @RequestParam int tid,
			@RequestParam int count,@RequestParam int timeId,@RequestParam int locationId) {

		int num = tClass.getNum();
		String name = tClass.getName();
		TClass t = tClassBiz.findByNum(num);//添加前查询是否存在
		TClass t1 = tClassBiz.findByName(name);
		if (t != null) {
			return 1;
		}if (t1 != null) {
			return 2;
		} else {
			Time time = timeBiz.findById(timeId);
			Location location = locationBiz.findById(locationId);
			Teacher teacher = teacherBiz.findById(tid);
			tClass.setTimeName(time.getName());//上课时间存放课程表
			tClass.setLocationName(location.getName());//上课地点存放课程表
			tClass.setTname(teacher.getName());//上课教师存放课程表
			tClass.setRest_count(count);
			tClassBiz.save(tClass);
			return 0;
		}

	}

	// 管理员修改课程操作
		@RequestMapping("toClassEdit")
		public String toClassEdit(Model model, @RequestParam int id) {

			TClass tClass = tClassBiz.finById(id);
			List<Teacher> ls = teacherBiz.findAll();
			List<Location> location = locationBiz.findAll();//拿到位置
			List<Time> time = timeBiz.findAll();//拿到时间
			model.addAttribute("types", ls);// 拿到教师
			model.addAttribute("location", location);
			model.addAttribute("time", time);
			model.addAttribute("tClass", tClass);
			return "classEdit";
		}

		@RequestMapping("doClassEdit")
		@ResponseBody
		public int doClassEdit(TClass tClass,Model model,HttpServletRequest request, @RequestParam int tid,
				@RequestParam int timeId,@RequestParam int locationId) {
			
			Time time = timeBiz.findById(timeId);
			Location location = locationBiz.findById(locationId);
			Teacher teacher = teacherBiz.findById(tid);
			tClass.setTname(teacher.getName());//此时只能拿到id存进数据库，还需要把对应的名称存课程表里面
			tClass.setLocationName(location.getName());
			tClass.setTimeName(time.getName());
			TClass t = tClassBiz.save(tClass);
			if (t != null) {
				return 0;
			} else {
				return 1;
			}
		}
	// 课程显示给学生的
	@RequestMapping("classList")
	public String toTClassList(HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "", required = false) String name,
			@RequestParam(defaultValue = "-1", required = false) int tid,
			@RequestParam(defaultValue = "", required = false) String sortName) {
		List<Teacher> ls2 = teacherBiz.findAll();
		Page<TClass> pageInfo = tClassBiz.findAllClass(pageNo, name, tid);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", ls2);// 教师
		return "classList";
	}

	// 课程显示给管理员的
	@RequestMapping("classListAdmin")
	public String toTClassListAdmin(HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "", required = false) String name,
			@RequestParam(defaultValue = "-1", required = false) int tid,
			@RequestParam(defaultValue = "", required = false) String sortName) {
		List<Teacher> ls2 = teacherBiz.findAll();
		Page<TClass> pageInfo = tClassBiz.findAllClass(pageNo, name, tid);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", ls2);// 教师
		return "classListAdmin";
	}

	// 课程显示给教师的
	@RequestMapping("classListTeacher")
	public String toTClassListTeacher(HttpServletRequest request, Model model,
			@RequestParam(defaultValue = "1", required = false) int pageNo,
			@RequestParam(defaultValue = "", required = false) String name,
			@RequestParam(defaultValue = "-1", required = false) int tid,
			@RequestParam(defaultValue = "", required = false) String sortName) {
		List<Teacher> ls2 = teacherBiz.findAll();
		Page<TClass> pageInfo = tClassBiz.findAllClass(pageNo, name, tid);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", ls2);// 教师
		return "classListTeacher";
	}

	// 管理员删除课程操作
	@RequestMapping("classDel")
	@ResponseBody
	public int classDel(@RequestParam int id) {
		tClassBiz.delById(id);
		return 0;
	}
	
	
}
