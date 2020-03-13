package website.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import website.entity.Rajd;
import website.forms.RajdSignValidation;
import website.service.RajdService;

@Controller
@RequestMapping("/panel")
public class PanelController {
	
	@Autowired
	private RajdService service;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Rajd> mySign = service.getMySign();
		model.addAttribute("mySign", mySign);
		return "home-panel";
	}
	
	@RequestMapping("/rajdSignForm")
	public String showRajdSignForm(Model model) {
		
		RajdSignValidation validation = new RajdSignValidation();
		model.addAttribute("rajd", validation);
		
		return "rajd-sign-form";
	}
	
	@RequestMapping("/processRajdForm")
	public String processRajdForm(
			@Valid @ModelAttribute("rajd") RajdSignValidation rajdForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "rajd-sign-form";
		} else {
			
			service.addSign(rajdForm);
		}
		return "redirect:/";
	}
	

}
