package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;

@Controller
public class ControllerService {

	@Autowired
	private CounsellorService service;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("counsellor",new Counsellor());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginCheck(Counsellor c, Model model) {
		
		Integer loginCounsellor = service.loginCounsellor(c);
		
		if(loginCounsellor == 0) {
			model.addAttribute("notSucess", "login Not Sucess");
		}else {
			model.addAttribute("sucess","login Sucessfull");
		}
		return "login";
	}
	
	@GetMapping("/reg")
	public String register(Model model) {
		model.addAttribute("counsellor",new Counsellor());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerCounsellor(Counsellor c , Model model) {
		
		String saveCounsellor = service.saveCounsellor(c);
		
		if(saveCounsellor.equals("Counsellor Saved Sucessfully")) {
			model.addAttribute("msg","saved sucessfully");
		}else {
			model.addAttribute("msg1", "Account with this mail id is already register");
		}
		return "register";
	}
}
