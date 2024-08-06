package in.ashokit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.entity.Counsellor;
import in.ashokit.entity.Student_Enq;
import in.ashokit.repo.CounsellorRepo;
import in.ashokit.repo.StudentRepo;
import in.ashokit.utils.EmailUtils;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepo repo;
	
	@Autowired
	private EmailUtils emailUtils ;
	
	@Autowired
	private StudentRepo studentRepo;
	
	
	@Override
	public String saveCounsellor(Counsellor c) {
		String email = c.getEmail();
		Counsellor findByEmail = repo.findByEmail(email);
		
		if(findByEmail != null) {
			return "Account already exists with this mail id";
		}
		
		Counsellor save = repo.save(c);
		if(save.getId() != null) {
			return "Counsellor Saved Sucessfully";
		}else {
			return "unable to save";
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

	@Override
	public boolean handlePasseword(String email) {
		Counsellor c = repo.findByEmail(email);
	
		if(c==null) {
			return false;
		}
		
		String subject="Recover Password - Student Management Portal";
		String body="<h1>Your Password :"+c.getPwd()+"</h1>";
	
		return emailUtils.sendMail(subject, body, email);
	}

	@Override
	public DashBoardResponse dashBoardInfo(Integer id) {
 
		List<Student_Enq> allEnq = studentRepo.findCounsellor(id);
		
		int enrolled = allEnq.stream().filter(e->e.getEnqStatus().equals("Enrolled")).collect(Collectors.toList()).size();
		
		DashBoardResponse resp=new DashBoardResponse();
		resp.setTotalEnq(allEnq.size());
		resp.setEnrolledEnq(enrolled);
		resp.setLostEnq(allEnq.size()-enrolled);
		
		return resp;
	}


}
