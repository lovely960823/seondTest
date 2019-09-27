package cn.com.edu.nyist.repositpry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.com.edu.nyist.model.Teacher;

public interface TeaRepo extends PagingAndSortingRepository<Teacher, Integer>,JpaSpecificationExecutor<Teacher>{



	//重写List方法
	List<Teacher> findAll();

	Teacher findByNumAndPwd(int num, String pwd);

	Teacher findByNum(int num);

	

}
