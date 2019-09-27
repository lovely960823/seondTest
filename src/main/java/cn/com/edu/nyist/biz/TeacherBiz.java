package cn.com.edu.nyist.biz;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import cn.com.edu.nyist.model.Teacher;

public interface TeacherBiz {

	@Transactional(readOnly=false)
	Teacher save(Teacher teacher);

	@Transactional(readOnly=true)
	List<Teacher> findAll();

	@Transactional(readOnly=false)
	void delete(int id);

	@Transactional(readOnly=true)
	Teacher findById(int id);

	@Transactional(readOnly=false)
	Teacher update(Teacher teacher);

	@Transactional(readOnly=false)
	Teacher findByNumAndPwd(int num, String pwd);

	@Transactional(readOnly=true)
	Teacher findByNum(int num);

	@Transactional(readOnly=true)
	Page<Teacher> findAllTeacher(int pageNo);

	



}
