package com.bloodbank.exception;

import static com.bloodbank.utils.MasterData.getBloodDonorDTO;
import static com.bloodbank.utils.TestUtils.currentTest;
import static com.bloodbank.utils.TestUtils.exceptionTestFile;
import static com.bloodbank.utils.TestUtils.testReport;
import static com.bloodbank.utils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bloodbank.controller.BloodDonorController;
import com.bloodbank.dto.BloodDonorDTO;
import com.bloodbank.service.BloodDonorService;
import com.bloodbank.utils.MasterData;

@WebMvcTest(BloodDonorController.class)
@AutoConfigureMockMvc
public class BloodDonorExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BloodDonorService bloodDonorService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateBloodDonorInvalidDataException() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();
		bloodDonorDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blooddonors")
				.content(MasterData.asJsonString(bloodDonorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateBloodDonorInvalidDataException() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();
		bloodDonorDTO.setName(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blooddonors/" + bloodDonorDTO.getId())
				.content(MasterData.asJsonString(bloodDonorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetBloodDonorByIdResourceNotFoundException() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Blood donor not found");

		when(this.bloodDonorService.getBloodDonorById(bloodDonorDTO.getId()))
				.thenThrow(new ResourceNotFoundException("Blood donor not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blooddonors/" + bloodDonorDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
