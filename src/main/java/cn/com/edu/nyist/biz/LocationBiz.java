package cn.com.edu.nyist.biz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.com.edu.nyist.model.Location;
@Transactional(readOnly=true)
public interface LocationBiz {

	List<Location> findAll();

	Location findById(int locationId);

}
