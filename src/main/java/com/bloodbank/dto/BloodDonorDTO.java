package com.bloodbank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BloodDonorDTO {

	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Sex is mandatory")
	private String sex;

	@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	private String phoneNumber;

	@NotBlank(message = "Address is mandatory")
	private String address;

	@NotBlank(message = "Blood group is mandatory")
	private String bloodGroup;

	public BloodDonorDTO() {
		super();
	}

	public BloodDonorDTO(Long id, @NotBlank(message = "Name is mandatory") String name,
			@NotBlank(message = "Sex is mandatory") String sex,
			@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") String phoneNumber,
			@NotBlank(message = "Address is mandatory") String address,
			@NotBlank(message = "Blood group is mandatory") String bloodGroup) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.bloodGroup = bloodGroup;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Override
	public String toString() {
		return "BloodDonorDTO [id=" + id + ", name=" + name + ", sex=" + sex + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", bloodGroup=" + bloodGroup + "]";
	}
}
