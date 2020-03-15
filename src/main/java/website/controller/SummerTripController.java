package website.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import website.entity.SummerTrip;
import website.entity.User;
import website.forms.SummerTripValidation;
import website.service.SummerTripService;
import website.service.UserServiceInterface;

@Controller
@RequestMapping("/panel")
public class SummerTripController {
	
	@Autowired
	private SummerTripService summerTripService;
	
	@Autowired 
	private UserServiceInterface userService;
	
	@RequestMapping("/summerTrip")
	public String summerTrip(Model model) {
		
		List<SummerTrip> userSummerTripList = summerTripService.getUserRecords();
		model.addAttribute("userSummerTripList", userSummerTripList);
		return "summer-trip";
	}
	
	
	@RequestMapping("/updateEntry")
	public String updateEntry(Model model, @RequestParam("id") int id) {
		
		SummerTrip summerTrip = summerTripService.getRecord(id);
		User user = userService.getUser(userService.getCurrentUserEmail());
		
		if (summerTrip.getUser().getId() == user.getId()) {
			
			SummerTripValidation summerTripValidation = summerTripService.changeEntityToValidation(summerTrip);
		
			model.addAttribute("summerTripValidation", summerTripValidation);
			model.addAttribute("id", id);
		
			return "summer-trip-form";
		} else {
			
			return "no-access";
		}
	}
	
	@RequestMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") int id) {
		
		SummerTrip summerTrip = summerTripService.getRecord(id);
		User user = userService.getUser(userService.getCurrentUserEmail());
		
		if (summerTrip.getUser().getId() == user.getId()) {
			
			summerTripService.deleteRecord(id);
			
			return "redirect:/panel/summerTrip";
		} else {
		
			return "no-access";
		}
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
			BindingResult bindingResult,
			@RequestParam("update") int update){
		
			if (update == 0) {

				if (bindingResult.hasErrors()) {
					return "summer-trip-form";
				}
				
			summerTripService.addRecord(summerTripValidation);
			
			} else {
				
				if (bindingResult.hasErrors()) {

					return "redirect:/panel/updateEntry?id=" + update + "&error=1";
				}
				
				summerTripService.updateRecord(summerTripValidation, update);
			}
		
		return "redirect:/panel/summerTrip";
	}
	
}
