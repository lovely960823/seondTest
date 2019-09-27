package cn.com.edu.nyist.repositpry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import cn.com.edu.nyist.model.TClass;

public interface TClassRepo extends PagingAndSortingRepository<TClass, Integer>,JpaSpecificationExecutor<TClass>{

	TClass findByNum(int num);

	TClass findByName(String name);
	
	List<TClass> findAll();
}
