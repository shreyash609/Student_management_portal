package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.SearchCriteria;
import in.ashokit.entity.Student_Enq;

public interface StudentService {

	public boolean saveStudent(Student_Enq se);
	
	public List<Student_Enq> getEnq(Integer id,SearchCriteria sc);
}
