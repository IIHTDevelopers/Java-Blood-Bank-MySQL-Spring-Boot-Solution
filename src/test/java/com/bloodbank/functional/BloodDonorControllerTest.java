package com.bloodbank.functional;

import static com.bloodbank.utils.MasterData.getBloodDonorDTO;
import static com.bloodbank.utils.MasterData.getBloodDonorDTOList;
import static com.bloodbank.utils.TestUtils.businessTestFile;
import static com.bloodbank.utils.TestUtils.currentTest;
import static com.bloodbank.utils.TestUtils.testReport;
import static com.bloodbank.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class BloodDonorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BloodDonorService bloodDonorService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllBloodDonors() throws Exception {
		List<BloodDonorDTO> bloodDonorDTOs = getBloodDonorDTOList();

		when(this.bloodDonorService.getAllBloodDonors()).thenReturn(bloodDonorDTOs);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blooddonors")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bloodDonorDTOs))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetBloodDonorById() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();
		when(this.bloodDonorService.getBloodDonorById(bloodDonorDTO.getId())).thenReturn(bloodDonorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blooddonors/" + bloodDonorDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bloodDonorDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreateBloodDonor() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();

		when(this.bloodDonorService.addBloodDonor(any())).thenReturn(bloodDonorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/blooddonors")
				.content(MasterData.asJsonString(bloodDonorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bloodDonorDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdateBloodDonor() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();

		when(this.bloodDonorService.updateBloodDonor(eq(bloodDonorDTO.getId()), any())).thenReturn(bloodDonorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/blooddonors/" + bloodDonorDTO.getId())
				.content(MasterData.asJsonString(bloodDonorDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bloodDonorDTO))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeleteBloodDonor() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();
		when(this.bloodDonorService.deleteBloodDonor(bloodDonorDTO.getId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/blooddonors/" + bloodDonorDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testFindBloodDonorsByBloodGroup() throws Exception {
		String bloodGroup = "A+";
		List<BloodDonorDTO> bloodDonorDTOList = getBloodDonorDTOList();

		when(this.bloodDonorService.findBloodDonorsByBloodGroup(bloodGroup)).thenReturn(bloodDonorDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/blooddonors/bloodgroup/" + bloodGroup)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bloodDonorDTOList))
						? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testFindBloodDonorByPhoneNumber() throws Exception {
		BloodDonorDTO bloodDonorDTO = getBloodDonorDTO();
		when(this.bloodDonorService.findBloodDonorByPhoneNumber(bloodDonorDTO.getPhoneNumber()))
				.thenReturn(bloodDonorDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/api/blooddonors/phone/" + bloodDonorDTO.getPhoneNumber()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bloodDonorDTO))
						? "true"
						: "false"),
				businessTestFile);
	}
}
