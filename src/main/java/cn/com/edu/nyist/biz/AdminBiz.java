package cn.com.edu.nyist.biz;

import org.springframework.transaction.annotation.Transactional;

import cn.com.edu.nyist.model.Admin;

public interface AdminBiz {
/*
	@Transactional(readOnly = true)
	Admin findByNameAndPwd(String name, String pwd);
*/
	@Transactional(readOnly = true)
	Admin findById(int id);

	@Transactional(readOnly = false)
	void save(Admin admin);

	@Transactional(readOnly = true)
	Admin findByNameAndNum(int num, String pwd);

	

}
