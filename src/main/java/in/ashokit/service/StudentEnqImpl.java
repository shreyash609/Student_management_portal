package in.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.SearchCriteria;
import in.ashokit.entity.Student_Enq;
import in.ashokit.repo.StudentRepo;

@Service
public class StudentEnqImpl implements StudentService {

	@Autowired
	private StudentRepo repo;
	
	@Override
	public boolean saveStudent(Student_Enq se) {
		Student_Enq save = repo.save(se);
		return save.getId() != null;
	}

	@Override
	public List<Student_Enq> getEnq(Integer id,SearchCriteria sc) {
		List<Student_Enq> enquiries = repo.findCounsellor(id);
		return enquiries;
	}

}
