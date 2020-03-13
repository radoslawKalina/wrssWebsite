package website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import website.entity.Rajd;
import website.service.RajdService;

@Controller
@RequestMapping("panel/admin")
public class PanelAdminController {
	
	@Autowired
	private RajdService rajdService;
	
	@RequestMapping("/all")
	public String getAllSign(Model model) {
		
		List<Rajd> allRajd = rajdService.getAllSign();
		model.addAttribute("allRajd", allRajd);
		
		return "all-sign";
	}
		
	
	

}
