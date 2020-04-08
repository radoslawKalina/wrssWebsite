package website.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
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

import website.dto.PaidDto;
import website.dto.SummerTripDto;
import website.dto.UserDto;
import website.model.PaidListModel;
import website.model.SummerTripRequestModel;
import website.model.SummerTripResponseModel;
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

		List<SummerTripDto> allRecords = summerTripService.getAllRecords();
		List<SummerTripResponseModel> summerTripList = new ArrayList<>();

		for (SummerTripDto temp : allRecords) {

			SummerTripResponseModel tempModel = new ModelMapper().map(temp, SummerTripResponseModel.class);
			tempModel.setPaid(temp.getPaid().getPaid());

			summerTripList.add(tempModel);
		}

		LinkedHashMap<String, Integer> sizesCount = getSizesCount(allRecords);

		model.addAttribute("summerTripList", summerTripList);
		model.addAttribute("sizeCount", sizesCount);

		return "admin-summer-trip";

	}

	@RequestMapping("/updateEntryAdmin")
	public String updateEntry(Model model, @RequestParam("id") int id) {

		SummerTripDto summerTripDto = summerTripService.getRecord(id);

		SummerTripRequestModel summerTripRequestModel = new ModelMapper().map(summerTripDto,
				SummerTripRequestModel.class);

		model.addAttribute("summerTripRequestModel", summerTripRequestModel);
		model.addAttribute("id", id);

		return "summer-trip-form";
	}

	@RequestMapping("/processSummerTripForm")
	public String processSummerTripForm(
			@Valid @ModelAttribute("summerTripValidation") SummerTripRequestModel summerTripRequestModel,
			BindingResult bindingResult, @RequestParam("update") int update) {

		if (bindingResult.hasErrors()) {
			return "redirect:/panel/admin/updateEntryAdmin?id=" + update + "&error=1";
		}

		UserDto userDto = summerTripService.getRecord(update).getUser();
		
		SummerTripDto summerTripDto = new ModelMapper().map(summerTripRequestModel, SummerTripDto.class);

		summerTripService.updateRecord(summerTripDto, update, userDto);

		return "redirect:/panel/admin/summerTrip";
	}


	@RequestMapping("/changePaidStatus")
	public String changePaidStatus(Model model) {

		List<PaidDto> paidDtoList = paidService.getAllRecords();

		PaidListModel paidListModel = new PaidListModel(paidDtoList);

		model.addAttribute("paidForm", paidListModel);

		return "change-paid-status";
	}

	@RequestMapping("/processPaidStatusChange")
	public String changePaidStatus(@ModelAttribute("paidForm") PaidListModel paidListModel) {

		List<PaidDto> updatedPaidListModel = paidListModel.getPaidList();

		for (PaidDto temp : updatedPaidListModel) {

			PaidDto paid = paidService.getRecord(temp.getId());
			paid.setPaid(temp.getPaid());

			paidService.updateRecord(paid);
		}

		return "redirect:/panel/admin/summerTrip";
	}

	@RequestMapping("/deleteEntryAdmin")
	public String deleteEntry(@RequestParam("id") int id) {

		summerTripService.deleteRecord(id);

		return "redirect:/panel/admin/summerTrip";
	}

	public LinkedHashMap<String, Integer> getSizesCount(List<SummerTripDto> allRecords) {

		List<String> shirtSizes = new ArrayList<>();

		LinkedHashMap<String, Integer> sizesCount = new LinkedHashMap<String, Integer>();

		for (SummerTripDto temp : summerTripService.getPaidRecords()) {
			shirtSizes.add(temp.getShirtSize());
		}
		sizesCount.put("S", Collections.frequency(shirtSizes, "S"));
		sizesCount.put("M", Collections.frequency(shirtSizes, "M"));
		sizesCount.put("L", Collections.frequency(shirtSizes, "L"));
		sizesCount.put("XL", Collections.frequency(shirtSizes, "XL"));

		return sizesCount;

	}

}
