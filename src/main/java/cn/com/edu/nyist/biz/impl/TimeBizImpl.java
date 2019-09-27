package cn.com.edu.nyist.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.edu.nyist.biz.TimeBiz;
import cn.com.edu.nyist.model.Time;
import cn.com.edu.nyist.repositpry.TimeREpo;
@Service
public class TimeBizImpl implements TimeBiz {

	@Autowired
	private TimeREpo timeRepo;
	@Override
	public List<Time> findAll() {
		
		return timeRepo.findAll();
	}
	@Override
	public Time findById(int timeId) {
		
		return timeRepo.findOne(timeId);
	}

}
