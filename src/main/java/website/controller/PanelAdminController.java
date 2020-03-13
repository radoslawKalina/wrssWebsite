package website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import website.entity.SummerTrip;
import website.service.SummerTripService;

@Controller
@RequestMapping("panel/admin")
public class PanelAdminController {
	
	@Autowired
	private SummerTripService rajdService;
	
	@RequestMapping("/all")
	public String getAllSign(Model model) {
		
		List<SummerTrip> allRajd = rajdService.getAllSign();
		model.addAttribute("allRajd", allRajd);
		
		return "all-sign";
	}
		
	
	

}
