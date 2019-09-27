package cn.com.edu.nyist.repositpry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.com.edu.nyist.model.Location;

public interface LocationRepo extends CrudRepository<Location, Integer> {

	List<Location> findAll();
}
