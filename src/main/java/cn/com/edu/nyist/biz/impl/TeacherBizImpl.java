package cn.com.edu.nyist.biz.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.edu.nyist.biz.TeacherBiz;
import cn.com.edu.nyist.constant.PageConstant;
import cn.com.edu.nyist.model.Teacher;
import cn.com.edu.nyist.repositpry.TeaRepo;
import cn.com.edu.nyist.util.MD5Utils;
@Service
public class TeacherBizImpl implements TeacherBiz {

	@Autowired
	private TeaRepo teaRepo;

	@Override
	public Teacher save(Teacher teacher) {
		return teaRepo.save(teacher);
	}

	@Override
	public List<Teacher> findAll() {
		return teaRepo.findAll();
	}
	@Override
	public void delete(int id) {
		 teaRepo.delete(id);
	}
	@Override
	public Teacher findById(int id) {
		return teaRepo.findOne(id);
	}
	@Override
	public Teacher update(Teacher teacher) {
		
		return teaRepo.save(teacher);
	}
	@Override
	public Teacher findByNumAndPwd(int num, String pwd) {
		return  teaRepo.findByNumAndPwd(num,MD5Utils.getMD5(pwd));
	}
	@Override
	public Teacher findByNum(int num) {
		return teaRepo.findByNum(num);
	}

	@Override
	public Page<Teacher> findAllTeacher(int pageNo) {
		//索引从0开始计数，所以实际页数减1
		Pageable pageable = new PageRequest(pageNo-1, PageConstant.T_SIZE);
		
		Page<Teacher> pageInfo = teaRepo.findAll(pageable);
		return pageInfo;
	}

	
	


}
