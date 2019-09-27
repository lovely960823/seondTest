package cn.com.edu.nyist.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.edu.nyist.biz.LocationBiz;
import cn.com.edu.nyist.model.Location;
import cn.com.edu.nyist.repositpry.LocationRepo;
@Service
public class LocationBizImpl implements LocationBiz {

	@Autowired
	private LocationRepo locationRepo;
	@Override
	public List<Location> findAll() {
		
		return locationRepo.findAll();
	}
	@Override
	public Location findById(int locationId) {
		
		return locationRepo.findOne(locationId);
	}

}
