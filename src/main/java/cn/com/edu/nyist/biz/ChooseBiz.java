package cn.com.edu.nyist.biz;


import cn.com.edu.nyist.model.Choose;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChooseBiz {

	 @Transactional(readOnly = true)	
	List<Choose> findByStuId(int stuId);

	 @Transactional(readOnly = false)
	void deleteById(int id);
	 
	 @Transactional(readOnly = true)
	List<Choose> findByTeaId(int teaId);

	 @Transactional(readOnly = true)	
	Choose findClass(int claId, int teaId,int stuId);

	 @Transactional(readOnly = true)	
	Choose findByClaId(int id);

	 @Transactional(readOnly = false)
	Choose update(Choose choose);

	 @Transactional(readOnly = true)
	Choose findById(int id);

	 @Transactional(readOnly = true)
	Choose findByTimeAdnLocation(int stuId,int timeId, int locationId);

	 @Transactional
	int update1(Choose choose);

	 @Transactional(readOnly = false)
	void deleteByTid(int id);

	 @Transactional(readOnly = false)
	void deleteBySid(int id);


	
}
