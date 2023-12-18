package com.bloodbank.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloodbank.dto.BloodDonorDTO;
import com.bloodbank.entity.BloodDonor;
import com.bloodbank.exception.ResourceNotFoundException;
import com.bloodbank.repo.BloodDonorRepository;
import com.bloodbank.service.BloodDonorService;

@Service
public class BloodDonorServiceImpl implements BloodDonorService {

	private final BloodDonorRepository bloodDonorRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public BloodDonorServiceImpl(BloodDonorRepository bloodDonorRepository, ModelMapper modelMapper) {
		this.bloodDonorRepository = bloodDonorRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public BloodDonorDTO getBloodDonorById(Long id) {
		BloodDonor bloodDonor = bloodDonorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blood donor not found with id: " + id));
		return modelMapper.map(bloodDonor, BloodDonorDTO.class);
	}

	@Override
	public List<BloodDonorDTO> getAllBloodDonors() {
		List<BloodDonor> bloodDonors = bloodDonorRepository.findAll();
		return bloodDonors.stream().map(bloodDonor -> modelMapper.map(bloodDonor, BloodDonorDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public BloodDonorDTO addBloodDonor(BloodDonorDTO bloodDonorDTO) {
		BloodDonor bloodDonor = modelMapper.map(bloodDonorDTO, BloodDonor.class);
		BloodDonor savedDonor = bloodDonorRepository.save(bloodDonor);
		return modelMapper.map(savedDonor, BloodDonorDTO.class);
	}

	@Override
	public BloodDonorDTO updateBloodDonor(Long id, BloodDonorDTO bloodDonorDTO) {
		BloodDonor existingDonor = bloodDonorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blood donor not found with id: " + id));

		existingDonor.setName(bloodDonorDTO.getName());
		existingDonor.setSex(bloodDonorDTO.getSex());
		existingDonor.setPhoneNumber(bloodDonorDTO.getPhoneNumber());
		existingDonor.setAddress(bloodDonorDTO.getAddress());
		existingDonor.setBloodGroup(bloodDonorDTO.getBloodGroup());

		BloodDonor updatedDonor = bloodDonorRepository.save(existingDonor);
		return modelMapper.map(updatedDonor, BloodDonorDTO.class);
	}

	@Override
	public boolean deleteBloodDonor(Long id) {
		BloodDonor bloodDonor = bloodDonorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Blood donor not found with id: " + id));
		bloodDonorRepository.delete(bloodDonor);
		return true;
	}

	@Override
	public List<BloodDonorDTO> findBloodDonorsByBloodGroup(String bloodGroup) {
		List<BloodDonor> bloodDonors = bloodDonorRepository.findAllByBloodGroup(bloodGroup);
		return bloodDonors.stream().map(bloodDonor -> modelMapper.map(bloodDonor, BloodDonorDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public BloodDonorDTO findBloodDonorByPhoneNumber(String phoneNumber) {
		BloodDonor bloodDonor = bloodDonorRepository.findByPhoneNumber(phoneNumber);
		if (bloodDonor == null) {
			throw new ResourceNotFoundException("Blood donor not found with phone number: " + phoneNumber);
		}
		return modelMapper.map(bloodDonor, BloodDonorDTO.class);
	}
}
