package website.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import website.entity.SummerTrip;
import website.forms.SummerTripValidation;
import website.service.SummerTripService;

@Controller
@RequestMapping("/panel")
public class SummerTripController {
	
	@Autowired
	private SummerTripService summerTripService;
	
	@RequestMapping("/summerTrip")
	public String summerTrip(Model model) {
		
		List<SummerTrip> userSummerTripList = summerTripService.getAllSign();
		model.addAttribute("userSummerTripList", userSummerTripList);
		return "summer-trip";
	}
	
	@RequestMapping("/summerTripRegisterForm")
	public String showSummerTripRegisterForm(Model model) {
		
		SummerTripValidation summerTripValidation = new SummerTripValidation();
		model.addAttribute("summerTripValidation", summerTripValidation);
		
		return "summer-trip-form";
	}
	
	@RequestMapping("/processSummerTripForm")
	public String processSummerTripForm(
			@Valid @ModelAttribute("summerTripValidation") SummerTripValidation summerTripValidation, 
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "summer-trip-form";
		} else {
			
			summerTripService.addSign(summerTripValidation);
		}
		return "redirect:/panel/summerTrip";
	}

}
