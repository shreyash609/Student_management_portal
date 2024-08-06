package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.DashBoardResponse;
import in.ashokit.entity.Counsellor;
import in.ashokit.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	public String loginCheck(Counsellor c,HttpServletRequest req, Model model) {
		
		Integer loginCounsellor = service.loginCounsellor(c);
		
		if(loginCounsellor == 0) {
			model.addAttribute("notSucess", "login Not Sucess");
		}else {
			
			HttpSession session = req.getSession(true);
			session.setAttribute("CID",loginCounsellor);
			
			model.addAttribute("sucess","login Sucessfull");
		}
		return "redirect:dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboardView(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Object obj = session.getAttribute("CID");
		Integer id=(Integer)obj;
		
		DashBoardResponse dashBoardInfo = service.dashBoardInfo(id);
		
		model.addAttribute("dashboard",dashBoardInfo);
		
		
		return "dashboardView";
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
	
	
	@GetMapping("/pwdView")
	public String pwdView(Model model) {
		return "pwdView";
	}

	@GetMapping("/forgotpwd")
	public String handlePwd(@RequestParam("email") String email,Model model) {
		boolean handlePasseword = service.handlePasseword(email);
		
		if(handlePasseword) {
			model.addAttribute("sucess","password send to your email");
		}else {
			model.addAttribute("notSucess","Invalid email");
		}
		return "pwdView";
	}
	
	
}
