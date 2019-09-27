package cn.com.edu.nyist.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.edu.nyist.biz.AdminBiz;
import cn.com.edu.nyist.model.Admin;
import cn.com.edu.nyist.repositpry.AdminRepo;
@Service
public class AdminBizImpl implements AdminBiz {

	@Autowired
	private AdminRepo adminRepo;
/*	@Override
	public Admin findByNameAndPwd(String name, String pwd) {
		return adminRepo.findByNameAndPwd(name, pwd);
	}
*/
	@Override
	public Admin findById(int id) {
		return adminRepo.findOne(id);
	}
	@Override
	public void save(Admin admin) {

		adminRepo.save(admin);
	}
	@Override
	public Admin findByNameAndNum(int num, String pwd) {
		return adminRepo.findByNumAndPwd(num, pwd);
	}

}
