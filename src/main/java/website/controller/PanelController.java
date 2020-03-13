package website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import website.entity.SummerTrip;
import website.service.SummerTripService;

@Controller
@RequestMapping("/panel")
public class PanelController {
	
	@Autowired
	private SummerTripService summerTripService;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<SummerTrip> savedByUser = summerTripService.getMySign();
		model.addAttribute("savedByUser", savedByUser);
		return "home-panel";
	}
	

}
