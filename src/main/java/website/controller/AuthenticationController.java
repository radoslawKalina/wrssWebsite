package website.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import website.dto.UserDto;
import website.model.UserRequestModel;
import website.service.UserService;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@RequestMapping("/showLoginForm")
	public String showLoginForm() {

		if (isUserLoggedIn()) {
			return "home-panel";
		}

		return "login-form";
	}

	@RequestMapping("/registration")
	public String registrationForm(Model model) {

		UserRequestModel userModel = new UserRequestModel();
		model.addAttribute("userModel", userModel);

		return "registration-form";
	}

	@RequestMapping("/processRegistration")
	public String processRegistration(@Valid @ModelAttribute("userModel") UserRequestModel userModel,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "registration-form";

		} else {

			UserDto userDto = new ModelMapper().map(userModel, UserDto.class);
			userService.registerUser(userDto);

		}
		return "redirect:/";
	}

	@RequestMapping("/noAccess")
	public String noAccess() {

		return "no-access";
	}

	boolean isUserLoggedIn() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails;
	}
}
