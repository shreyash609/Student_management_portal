package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Student_Enq;

public interface StudentRepo extends JpaRepository<Student_Enq, Integer>{

//	 select * from student_enq where id=id
//	public List<Student_Enq> findById(int id);
	
	@Query("from Student_Enq where id=:id")
	public List<Student_Enq> findCounsellor(Integer id);
}
