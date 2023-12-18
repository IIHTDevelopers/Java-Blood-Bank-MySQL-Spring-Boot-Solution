package com.bloodbank.service;

import java.util.List;

import com.bloodbank.dto.BloodDonorDTO;

public interface BloodDonorService {

	BloodDonorDTO getBloodDonorById(Long id);

	List<BloodDonorDTO> getAllBloodDonors();

	BloodDonorDTO addBloodDonor(BloodDonorDTO bloodDonorDTO);

	BloodDonorDTO updateBloodDonor(Long id, BloodDonorDTO bloodDonorDTO);

	boolean deleteBloodDonor(Long id);

	List<BloodDonorDTO> findBloodDonorsByBloodGroup(String bloodGroup);

	BloodDonorDTO findBloodDonorByPhoneNumber(String phoneNumber);
}
