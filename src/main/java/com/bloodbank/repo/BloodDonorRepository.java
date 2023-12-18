package com.bloodbank.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbank.entity.BloodDonor;

@Repository
public interface BloodDonorRepository extends JpaRepository<BloodDonor, Long> {

	BloodDonor findByPhoneNumber(String phoneNumber);

	List<BloodDonor> findAllByBloodGroup(String bloodGroup);
}
