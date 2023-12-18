package com.bloodbank.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.dto.BloodDonorDTO;
import com.bloodbank.service.BloodDonorService;

@RestController
@RequestMapping("/api/blooddonors")
@Validated
public class BloodDonorController {

	private final BloodDonorService bloodDonorService;

	@Autowired
	public BloodDonorController(BloodDonorService bloodDonorService) {
		this.bloodDonorService = bloodDonorService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<BloodDonorDTO> getBloodDonorById(@PathVariable("id") @Min(1) Long id) {
		BloodDonorDTO bloodDonorDTO = bloodDonorService.getBloodDonorById(id);
		return ResponseEntity.ok(bloodDonorDTO);
	}

	@GetMapping
	public ResponseEntity<List<BloodDonorDTO>> getAllBloodDonors() {
		List<BloodDonorDTO> bloodDonorDTOList = bloodDonorService.getAllBloodDonors();
		return ResponseEntity.ok(bloodDonorDTOList);
	}

	@PostMapping
	public ResponseEntity<BloodDonorDTO> addBloodDonor(@RequestBody @Valid BloodDonorDTO bloodDonorDTO) {
		BloodDonorDTO addedBloodDonorDTO = bloodDonorService.addBloodDonor(bloodDonorDTO);
		return new ResponseEntity<>(addedBloodDonorDTO, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BloodDonorDTO> updateBloodDonor(@PathVariable("id") @Min(1) Long id,
			@RequestBody @Valid BloodDonorDTO bloodDonorDTO) {
		BloodDonorDTO updatedBloodDonorDTO = bloodDonorService.updateBloodDonor(id, bloodDonorDTO);
		return ResponseEntity.ok(updatedBloodDonorDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBloodDonor(@PathVariable("id") @Min(1) Long id) {
		bloodDonorService.deleteBloodDonor(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/bloodgroup/{bloodGroup}")
	public ResponseEntity<List<BloodDonorDTO>> findBloodDonorsByBloodGroup(
			@PathVariable("bloodGroup") String bloodGroup) {
		List<BloodDonorDTO> bloodDonorsByBloodGroup = bloodDonorService.findBloodDonorsByBloodGroup(bloodGroup);
		return ResponseEntity.ok(bloodDonorsByBloodGroup);
	}

	@GetMapping("/phone/{phoneNumber}")
	public ResponseEntity<BloodDonorDTO> findBloodDonorByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		BloodDonorDTO bloodDonorDTO = bloodDonorService.findBloodDonorByPhoneNumber(phoneNumber);
		return ResponseEntity.ok(bloodDonorDTO);
	}
}
