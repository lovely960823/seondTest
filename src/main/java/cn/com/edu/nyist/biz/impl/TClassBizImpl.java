package cn.com.edu.nyist.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.com.edu.nyist.model.Choose;
import cn.com.edu.nyist.model.Location;
import cn.com.edu.nyist.model.Student;
import cn.com.edu.nyist.model.Teacher;
import cn.com.edu.nyist.model.Time;
import cn.com.edu.nyist.repositpry.ChooseRepo;
import cn.com.edu.nyist.repositpry.LocationRepo;
import cn.com.edu.nyist.repositpry.StuRepo;
import cn.com.edu.nyist.repositpry.TeaRepo;
import cn.com.edu.nyist.repositpry.TimeREpo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cn.com.edu.nyist.biz.TClassBiz;
import cn.com.edu.nyist.constant.PageConstant;
import cn.com.edu.nyist.model.TClass;
import cn.com.edu.nyist.repositpry.TClassRepo;
@Service
public class TClassBizImpl implements TClassBiz {

	@Autowired
	private TClassRepo tClassRepo;
	@Autowired
	private StuRepo stuRepo;
	@Autowired
	private TeaRepo teaRepo;
	@Autowired
	private ChooseRepo chooseRepo;
	@Autowired
	private TimeREpo timeRepo;
	@Autowired
	private LocationRepo locationRepo;
	@Override
	public TClass save(TClass tClass) {
		tClass.setRest_count(0);
		return tClassRepo.save(tClass);
	}
	

	@Override
	public Page<TClass> findAllClass(int pageNo, String name, int tid) {
		
		//索引从0开始计数，所以实际页数减1
		Pageable pageable = new PageRequest(pageNo-1, PageConstant.PAGE_SIZE);
		//实现搜索的模糊查询
		Specification<TClass> spec = new Specification<TClass>() {
			@Override
			public Predicate toPredicate(Root<TClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				//可能搜索条件有0~2个
				List<Predicate> ls = new ArrayList<Predicate>();
				if(tid!=-1) {
					Predicate p1 = cb.equal(root.get("tid"), tid);
					ls.add(p1);
				}
				if(name!=null&&!name.equals("")) {
					Predicate p2 = cb.like(root.get("name").as(String.class), "%"+name+"%");
					ls.add(p2);
				}
				//List向数组转换
				Predicate[] arr = new Predicate[ls.size()];
				return cb.and(ls.toArray(arr));
			}
		};
		Page<TClass> pageInfo = tClassRepo.findAll(spec, pageable);
		return pageInfo;
	}

	/*
	 * id用来查找课程
	 * tid用来查找教师
	 * studentID用来查找对应的学生
	 * */
	@Override
	public int doSelect(int id, int tid, int studentId,int timeId,int locationId) {
		TClass tClass = tClassRepo.findOne(id);
		Teacher teacher = teaRepo.findOne(tid);
		Student student = stuRepo.findOne(studentId);
		Time time = timeRepo.findOne(timeId);
		Location location = locationRepo.findOne(locationId); 
		//把几个名称放入选择表中
		Choose choose=new Choose();
		choose.setCname(tClass.getName());
		choose.setSname(student.getName());
		choose.setTname(teacher.getName());
		choose.setLocationName(location.getName());
		choose.setTimeName(time.getName());
		choose.setLocationId(locationId);
		choose.setTimeId(timeId);
		choose.setTnum(teacher.getNum());
		choose.setSnum(student.getNum());
		choose.setCnum(tClass.getNum());
		choose.setClaId(id);
		choose.setTeaId(tid);
		choose.setStuId(studentId);
		tClass.setRest_count(tClass.getRest_count()+1);
		tClassRepo.save(tClass);
		chooseRepo.save(choose);
		return 0;
	}

	@Override
	public void delById(int id) {
		tClassRepo.delete(id);
		
	}


	@Override
	public TClass finById(int id) {
		
		return tClassRepo.findOne(id);
	}


	@Override
	public TClass findByNum(int num) {

		return tClassRepo.findByNum(num);
	}


	@Override
	public TClass findByName(String name) {

		return tClassRepo.findByName(name);
	}


	@Override
	public List<TClass> findAll() {
		
		return tClassRepo.findAll();
	}

}
