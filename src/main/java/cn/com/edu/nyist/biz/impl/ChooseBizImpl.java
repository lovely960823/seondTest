package cn.com.edu.nyist.biz.impl;

import cn.com.edu.nyist.biz.ChooseBiz;
import cn.com.edu.nyist.model.Choose;
import cn.com.edu.nyist.repositpry.ChooseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChooseBizImpl implements ChooseBiz {

    @Autowired
    private ChooseRepo chooseRepo;


	@Override
	public List<Choose> findByStuId(int stuId) {
		return chooseRepo.findByStuId(stuId);
	}

	@Override
	public void deleteById(int id) {
		
		chooseRepo.delete(id);
	}

	@Override
	public List<Choose> findByTeaId(int teaId) {
	
		return chooseRepo.findByTeaId(teaId);
	}

	@Override
	public Choose findClass(int claId, int teaId,int stuId) {
		
		return chooseRepo.findClass(claId,teaId,stuId);
	}

	@Override
	public Choose findByClaId(int id) {
		return chooseRepo.findOne(id);
	}

	@Override
	public Choose update(Choose choose) {
		return chooseRepo.save(choose);
		
	}

	@Override
	public Choose findById(int id) {
		return chooseRepo.findOne(id);
	}

	@Override
	public Choose findByTimeAdnLocation(int stuId,int timeId, int locationId) {
		return chooseRepo.findByTimeAdnLocation(stuId,timeId, locationId);
	}

	@Override
	public int update1(Choose choose) {
		int id=choose.getId();
		int score=choose.getScore();
		System.out.println(id+"**********************************************"+score);
		return chooseRepo.save1(id,score);
	}

	@Override
	public void deleteByTid(int id) {
		chooseRepo.deleteByTid(id);
		
	}

	@Override
	public void deleteBySid(int id) {

		chooseRepo.deleteBySid(id);
	}

	

}
