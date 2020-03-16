package website.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import website.entity.Paid;
import website.entity.SummerTrip;
import website.entity.User;
import website.forms.PaidForm;
import website.forms.SummerTripValidation;
import website.service.PaidService;
import website.service.SummerTripService;

@Controller
@RequestMapping("panel/admin")
public class SummerTripAdminController {
	
	@Autowired
	private SummerTripService summerTripService;
	
	@Autowired
	private PaidService paidService;
	
	@RequestMapping("/summerTrip")
	public String adminSummerTrip(Model model) {
		
		List<SummerTrip> summerTripList = summerTripService.getAllRecords();
		List<String> shirtSizes = new ArrayList<>();
		
		for (SummerTrip trip : summerTripService.getPaidRecords()) {
			shirtSizes.add(trip.getShirtSize());
		}
		
		@SuppressWarnings("serial")
		LinkedHashMap<String, Integer> sizesCount = new LinkedHashMap<String, Integer>() {{
			put("S", Collections.frequency(shirtSizes, "S"));
			put("M", Collections.frequency(shirtSizes, "M"));
			put("L", Collections.frequency(shirtSizes, "L"));
			put("XL", Collections.frequency(shirtSizes, "XL"));
		}};
		
		model.addAttribute("summerTripList", summerTripList);
		model.addAttribute("shirtSizes", shirtSizes);
		model.addAttribute("sizeCount", sizesCount);
		
		return "admin-summer-trip";
	}
	
	@RequestMapping("/updateEntryAdmin")
	public String updateEntry(Model model, @RequestParam("id") int id) {
		
		SummerTrip summerTrip = summerTripService.getRecord(id);
		SummerTripValidation summerTripValidation = summerTripService.changeEntityToValidation(summerTrip);
		
		model.addAttribute("summerTripValidation", summerTripValidation);
		model.addAttribute("id", id);
		
		return "summer-trip-form";
	}
	
	@RequestMapping("/deleteEntryAdmin")
	public String deleteEntry(@RequestParam("id") int id) {

		summerTripService.deleteRecord(id);
			
		return "redirect:/panel/admin/summerTrip";
	}
	
	@RequestMapping("/changePaidStatus")
	public String changePaidStatus(Model model) {
		
		List<Paid> paidList = paidService.getAllRecords();
		PaidForm paidForm = new PaidForm(paidList);
		model.addAttribute("paidForm", paidForm);
		
		return "change-paid-status";
	}
	
	@RequestMapping("/processPaidStatusChange")
	public String changePaidStatus(@ModelAttribute("paidForm") PaidForm paidForm) {
		
		List<Paid> paidList = paidForm.getPaidList();
		
		
		for (Paid temp : paidList) {
			
			Paid paid = paidService.getRecord(temp.getId());
			paid.setPaid(temp.getPaid());
			paidService.update(paid);
		} 
		
		return "redirect:/panel/admin/summerTrip";
	}
	
	@RequestMapping("/processSummerTripForm")
	public String processSummerTripForm(
			@Valid @ModelAttribute("summerTripValidation") SummerTripValidation summerTripValidation,
			BindingResult bindingResult,
			@RequestParam("update") int update){
		
			if (bindingResult.hasErrors()) {
				return "redirect:/panel/admin/updateEntryAdmin?id=" + update + "&error=1";
				}
			
			User user = summerTripService.getRecord(update).getUser();
			
			summerTripService.updateRecord(summerTripValidation, update, user);

		
		return "redirect:/panel/admin/summerTrip";
	}
		
	
	

}
