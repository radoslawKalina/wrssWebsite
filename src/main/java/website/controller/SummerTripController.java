package website.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import website.dto.SummerTripDto;
import website.dto.UserDto;
import website.model.SummerTripRequestModel;
import website.model.SummerTripResponseModel;
import website.service.SummerTripService;
import website.service.UserService;

@Controller
@RequestMapping("/panel")
public class SummerTripController {

	@Autowired
	private UserService userService;

	@Autowired
	private SummerTripService summerTripService;

	@RequestMapping("/summerTrip")
	public String summerTrip(Model model) {

		List<SummerTripDto> userRecordsDto = summerTripService.getUserRecords();

		List<SummerTripResponseModel> userRecords = new ArrayList<>();

		for (SummerTripDto temp : userRecordsDto) {

			SummerTripResponseModel tempModel = new ModelMapper().map(temp, SummerTripResponseModel.class);
			tempModel.setPaid(temp.getPaid().getPaid());

			userRecords.add(tempModel);
		}

		model.addAttribute("userRecords", userRecords);

		return "summer-trip";
	}

	@RequestMapping("/summerTripRegisterForm")
	public String showSummerTripRegisterForm(Model model) {

		SummerTripRequestModel summerTripRequestModel = new SummerTripRequestModel();
		model.addAttribute("summerTripRequestModel", summerTripRequestModel);

		return "summer-trip-form";
	}

	@RequestMapping("/processSummerTripForm")
	public String processSummerTripForm(
			@Valid @ModelAttribute("summerTripRequestModel") SummerTripRequestModel summerTripRequestModel,
			BindingResult bindingResult, @RequestParam("update") int update) {

		if (update == 0) {

			if (bindingResult.hasErrors()) {
				return "summer-trip-form";
			}

			SummerTripDto summerTripDto = new ModelMapper().map(summerTripRequestModel, SummerTripDto.class);

			summerTripService.addRecord(summerTripDto);

		} else {

			if (bindingResult.hasErrors()) {
				return "redirect:/panel/updateEntry?id=" + update + "&error=1";
			}

			SummerTripDto summerTripDto = new ModelMapper().map(summerTripRequestModel, SummerTripDto.class);

			summerTripService.updateRecord(summerTripDto, update);
		}

		return "redirect:/panel/summerTrip";
	}

	@RequestMapping("/updateEntry")
	public String updateEntry(Model model, @RequestParam("id") int id) {

		SummerTripDto summerTripDto = summerTripService.getRecord(id);
		UserDto userDto = userService.getUserDto(userService.getCurrentUserEmail());

		if (summerTripDto.getUser().getId() == userDto.getId()) {

			SummerTripRequestModel summerTripRequestModel = new ModelMapper().map(summerTripDto,
					SummerTripRequestModel.class);

			model.addAttribute("summerTripRequestModel", summerTripRequestModel);
			model.addAttribute("id", id);

			return "summer-trip-form";

		} else {
			return "no-access";

		}
	}

	@RequestMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("id") int id) {

		SummerTripDto summerTripDto = summerTripService.getRecord(id);
		UserDto userDto = userService.getUserDto(userService.getCurrentUserEmail());

		if (summerTripDto.getUser().getId() == userDto.getId()) {

			summerTripService.deleteRecord(id);

			return "redirect:/panel/summerTrip";

		} else {
			return "no-access";

		}
	}

}
