package cn.com.edu.nyist.biz;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import cn.com.edu.nyist.model.TClass;

public interface TClassBiz {

	@Transactional(readOnly=false)
	TClass save(TClass tClass);

	@Transactional(readOnly=true)
	Page<TClass> findAllClass(int pageNo, String name, int tid);
	
	@Transactional(readOnly=false)
	int doSelect(int id, int tid, int studentId,int timeId,int locationId);
	
	@Transactional(readOnly=false)
	void delById(int id);
	
	@Transactional(readOnly=true)
	TClass finById(int id);

	@Transactional(readOnly=true)
	TClass findByNum(int num);

	@Transactional(readOnly=true)
	TClass findByName(String name);

	@Transactional(readOnly=true)
	List<TClass> findAll();



}
