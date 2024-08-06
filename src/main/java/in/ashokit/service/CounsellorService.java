package in.ashokit.service;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.entity.Counsellor;

public interface CounsellorService {

	public String saveCounsellor(Counsellor c);
	
	public Integer loginCounsellor(Counsellor c);
	
	public boolean handlePasseword(String email);
	
	public DashBoardResponse dashBoardInfo(Integer id);
	
}
