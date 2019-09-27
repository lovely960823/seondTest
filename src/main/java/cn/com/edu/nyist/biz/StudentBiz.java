package cn.com.edu.nyist.biz;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import cn.com.edu.nyist.model.Student;

import java.util.List;

public interface StudentBiz {
	
	@Transactional(readOnly=false)
	Student save(Student student);

	@Transactional(readOnly=true)
	Student findByNum(int num);

	@Transactional(readOnly=false)
	void delete(int id);

	@Transactional(readOnly=true)
	Student findById(int id);

	@Transactional(readOnly=false)
	Student update(Student student);

	@Transactional(readOnly=true)
	List<Student> findAll();

	@Transactional(readOnly=true)
	Student findByNumAndPwd(int num, String pwd);

	@Transactional(readOnly=true)
	Page<Student> findAllStudent(int pageNo);



}
