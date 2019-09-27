package cn.com.edu.nyist.repositpry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.com.edu.nyist.model.Student;

public interface StuRepo extends PagingAndSortingRepository<Student, Integer>,JpaSpecificationExecutor<Student> {

	//Student findByNameAndPwd(String name, String pwd);

	List<Student> findAll();

	Student findByNumAndPwd(int num, String pwd);

	Student findByNum(int num);
}
