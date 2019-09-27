package cn.com.edu.nyist.repositpry;

import org.springframework.data.repository.CrudRepository;

import cn.com.edu.nyist.model.Admin;

public interface AdminRepo extends CrudRepository<Admin, Integer> {
	

	Admin findByNumAndPwd(int num, String pwd);

}
