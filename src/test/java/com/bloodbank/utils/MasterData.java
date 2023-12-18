package com.bloodbank.utils;

import java.util.ArrayList;
import java.util.List;

import com.bloodbank.dto.BloodDonorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class MasterData {

	public static BloodDonorDTO getBloodDonorDTO() {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setId(1L);
		bloodDonorDTO.setName("John Doe");
		bloodDonorDTO.setSex("Male");
		bloodDonorDTO.setPhoneNumber("1234567890");
		bloodDonorDTO.setAddress("123 Main St");
		bloodDonorDTO.setBloodGroup("A+");

		return bloodDonorDTO;
	}

	public static List<BloodDonorDTO> getBloodDonorDTOList() {
		List<BloodDonorDTO> bloodDonorDTOList = new ArrayList<>();

		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setId(1L);
		bloodDonorDTO.setName("John Doe");
		bloodDonorDTO.setSex("Male");
		bloodDonorDTO.setPhoneNumber("1234567890");
		bloodDonorDTO.setAddress("123 Main St");
		bloodDonorDTO.setBloodGroup("A+");

		bloodDonorDTOList.add(bloodDonorDTO);

		return bloodDonorDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);

			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
