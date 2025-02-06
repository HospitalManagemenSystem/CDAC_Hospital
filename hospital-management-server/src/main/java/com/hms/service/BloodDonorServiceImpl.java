package com.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hms.entity.BloodDonor;
import com.hms.entity.BloodGroup;
import com.hms.repo.BloodDonorRepository;


@Service
@Transactional
public class BloodDonorServiceImpl implements BloodDonorService {

	 @Autowired
	    private BloodDonorRepository bloodDonorRepo;

	@Override
	public List<BloodDonor> getAllBloodDonorsByCityAndBloodGroup(String city, String bloodGroup) {
		 return bloodDonorRepo.findByCityAndBloodGroup(city, BloodGroup.valueOf(bloodGroup.toUpperCase()));
	}

	@Override
	public BloodDonor saveBloodDonor(BloodDonor donor) {
		 return bloodDonorRepo.save(donor);
	}

	@Override
	public List<BloodDonor> getAllBloodDonors() {
		 return bloodDonorRepo.findAll();
	}
}
