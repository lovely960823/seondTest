package cn.com.edu.nyist.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.edu.nyist.biz.StudentBiz;
import cn.com.edu.nyist.constant.PageConstant;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.repositpry.StuRepo;
import cn.com.edu.nyist.util.MD5Utils;
@Service
public class StudentBizImpl implements StudentBiz {

	@Autowired
	private StuRepo stuRepo;

	@Override
	public Student save(Student student) {
		return stuRepo.save(student);
	}

	@Override
	public Student findByNum(int num) {
		
		return stuRepo.findByNum(num);
	}
/*
	@Override
	public Student findByNameAndPwd(String name, String pwd) {
		return stuRepo.findByNameAndPwd(name,MD5Utils.getMD5(pwd));
		//return stuRepo.findByNameAndPwd(name,pwd);
	}
*/
	@Override
	public void delete(int id) {
		
		stuRepo.delete(id);
	}

	@Override
	public Student findById(int id) {
		
		return stuRepo.findOne(id);
	}

	@Override
	public Student update(Student student) {

		return stuRepo.save(student);
	}

	@Override
	public List<Student> findAll() {

		return stuRepo.findAll();
	}

	@Override
	public Student findByNumAndPwd(int num, String pwd) {
		return  stuRepo.findByNumAndPwd(num,MD5Utils.getMD5(pwd));
	}

	@Override
	public Page<Student> findAllStudent(int pageNo) {
		//索引从0开始计数，所以实际页数减1
				Pageable pageable = new PageRequest(pageNo-1, PageConstant.S_SIZE);
				
				Page<Student> pageInfo = stuRepo.findAll(pageable);
				return pageInfo;
	}


}
