package website.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import website.forms.UserValidation;
import website.service.UserServiceInterface;

@Controller
public class AuthenticationController {
	
	@Autowired
	private UserServiceInterface userService;

	boolean isUserLoggedIn(){
		   return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
		}
	
	@RequestMapping("/showLoginForm")
	public String showLoginForm() {
		
		if(isUserLoggedIn()) {
			return "home-panel";
		}
		
		return "login-form";
	}
	
	@RequestMapping("/registration")
	public String registrationForm(Model model) {
		
		UserValidation userValidation = new UserValidation();
		model.addAttribute("userValidation", userValidation);
		
		return "registration-form";
	}
	
	@RequestMapping("/processRegistration")
	public String processRegistration(
			@Valid @ModelAttribute("userValidation") UserValidation userValidation, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "registration-form";
		} else {
			
			userService.registerUser(userValidation);
		}
		return "redirect:/";
	}
	
	@RequestMapping("/noAccess")
	public String noAccess() {
		
		return "no-access";
	}
}
