package cn.com.edu.nyist.biz;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.com.edu.nyist.model.Time;
@Transactional(readOnly=true)
public interface TimeBiz {

	List<Time> findAll();

	Time findById(int timeId);

}
