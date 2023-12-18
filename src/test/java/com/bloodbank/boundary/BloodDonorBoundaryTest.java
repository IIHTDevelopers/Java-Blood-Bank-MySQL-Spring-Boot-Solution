package com.bloodbank.boundary;

import static com.bloodbank.utils.TestUtils.boundaryTestFile;
import static com.bloodbank.utils.TestUtils.currentTest;
import static com.bloodbank.utils.TestUtils.testReport;
import static com.bloodbank.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bloodbank.dto.BloodDonorDTO;

public class BloodDonorBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testNameNotNull() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setName(null);
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameMinThree() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setName("ab"); // Less than 3 characters
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testNameMax255() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setName("a".repeat(256)); // More than 255 characters
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testSexNotNull() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setSex(null);
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPhoneNumberPattern() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setPhoneNumber("12345"); // Invalid phone number pattern
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testAddressNotNull() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setAddress(null);
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testBloodGroupNotNull() throws IOException {
		BloodDonorDTO bloodDonorDTO = new BloodDonorDTO();
		bloodDonorDTO.setBloodGroup(null);
		Set<ConstraintViolation<BloodDonorDTO>> violations = validator.validate(bloodDonorDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
