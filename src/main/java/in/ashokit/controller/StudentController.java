package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.Student_Enq;
import in.ashokit.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class StudentController {

	@Autowired
	private StudentService service;
	
	@GetMapping("/addEnq")
	public String addEnq(Model model) {
		model.addAttribute("se",new Student_Enq());
		return "addenquiries";
	}
	
	@PostMapping("/save")
	public String handleEnq(@ModelAttribute("se")  Student_Enq se, HttpServletRequest req, Model model) {
		
		HttpSession session = req.getSession(false);
		Integer id =(Integer)session.getAttribute("CID");
		
		se.setId(id);
		
		boolean saveStudent = service.saveStudent(se);
		
		if(saveStudent) {
			model.addAttribute("sucess","Student saved");
		}else {
			model.addAttribute("X","Student Not Saved");
		}
		return "addenquiries";
	}
	
//	@GetMapping("/")
//	public String viewEnquiries(Model model) {
//		return "ViewEnquires";
//	}
	
	@GetMapping("/enquiries")
	public String handleEnquiries(HttpServletRequest req,Model model) {
		
		HttpSession session = req.getSession(false);
		Integer id = (Integer)session.getAttribute("CID");
		
		List<Student_Enq> enq = service.getEnq(id,null);
		
		model.addAttribute("enquiries", enq);
		
		return"viewEnquires";
	}
}
