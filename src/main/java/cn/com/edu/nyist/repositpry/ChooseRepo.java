package cn.com.edu.nyist.repositpry;

import cn.com.edu.nyist.model.Choose;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChooseRepo extends CrudRepository<Choose,Integer> {
	
	@Query(value="select * from t_choose where stuId=:stuId",nativeQuery=true)
	List<Choose> findByStuId(@Param("stuId") Integer stuId);

	
	
	@Query(value="select * from t_choose where teaId=:teaId",nativeQuery=true)
	List<Choose> findByTeaId(@Param("teaId")Integer teaId);


	@Query(value="select * from t_choose where teaId=:teaId and claId=:claId and stuId=:stuId",nativeQuery=true)
	Choose findClass(@Param("claId")int claId, @Param("teaId")int teaId,@Param("stuId")int stuId);


	@Query(value="select * from t_choose where timeId=:timeId and locationId=:locationId and stuId=:stuId",nativeQuery=true)
	Choose findByTimeAdnLocation(@Param("stuId")int stuId,@Param("timeId")int timeId,@Param("locationId")int locationId);

//claId,cname,cnum,locationId,locationName,score,sname,snum,stuId,teaId,timeId,timeName,tname,tnum
	@Modifying
	@Query(value="update t_choose set score=?1 where id=?2",nativeQuery=true)
	int save1(@Param("score")int score,@Param("id")int id);


	@Query(value="delete t_choose where teaId=:id",nativeQuery=true)
	void deleteByTid(@Param("id")int id);


	@Query(value="delete t_choose where stuId=:id",nativeQuery=true)
	void deleteBySid(@Param("id")int id);
}
