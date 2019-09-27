package cn.com.edu.nyist.repositpry;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cn.com.edu.nyist.model.Time;

public interface TimeREpo extends CrudRepository<Time, Integer> {

	List<Time> findAll();
}
