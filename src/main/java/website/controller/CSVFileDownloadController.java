package website.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import website.dto.SummerTripDto;
import website.model.SummerTripResponseModel;
import website.service.SummerTripService;

@Controller
public class CSVFileDownloadController {

	@Autowired
	private SummerTripService summerTripService;

	@RequestMapping(value = "/downloadCSV")
	public void downloadCSV(HttpServletResponse response) throws IOException {

		String csvFileName = "summerTrip.csv";

		response.setContentType("text/csv");

		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", csvFileName);
		response.setHeader(headerKey, headerValue);

		List<SummerTripDto> allRecords = summerTripService.getAllRecords();

		List<SummerTripResponseModel> data = new ArrayList<>();

		for (int i = 0; i < allRecords.size(); i++) {

			data.add(new SummerTripResponseModel(
					allRecords.get(i).getFirstName(),
					allRecords.get(i).getLastName(),
					allRecords.get(i).getEmail(),
					allRecords.get(i).getIndexNumber(),
					allRecords.get(i).getShirtSize(),
					allRecords.get(i).getTransportOption(),
					allRecords.get(i).getPaid().getPaid()));

		}

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "firstName", "lastName", "email", "indexNumber", "shirtSize", "transportOption", "paid" };

		csvWriter.writeHeader(header);

		for (SummerTripResponseModel temp : data) {

			csvWriter.write(temp, header);
		}

		csvWriter.close();
	}
}
