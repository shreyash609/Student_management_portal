package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Counsellor;

public interface CounsellorRepo extends JpaRepository<Counsellor,Integer>{

//	select * from counsellor where email=email;
	public Counsellor findByEmail(String email);
	

	@Query("from Counsellor where email=:email AND pwd=:password")
	public Counsellor loginCheck(String email,String password);
	

	
}
