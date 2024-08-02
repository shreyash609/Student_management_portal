package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.Counsellor;
import in.ashokit.repo.CounsellorRepo;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepo repo;
	
	@Override
	public String saveCounsellor(Counsellor c) {
		String email = c.getEmail();
		Counsellor findByEmail = repo.findByEmail(email);
		if(findByEmail == null) {
			repo.save(c);
			return "Counsellor Saved Sucessfully";
		}else {
			return "Duplicate Email";
		}
	}

	@Override
	public Integer loginCounsellor(Counsellor c ) {
		
		String email = c.getEmail();
		String password = c.getPwd();
		
		Counsellor loginCheck = repo.loginCheck(email, password);
		if(loginCheck != null) {
			return loginCheck.getId();
		}
		return 0;
	}
}
