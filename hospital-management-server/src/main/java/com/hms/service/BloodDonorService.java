package com.hms.service;

import java.util.List;

import com.hms.entity.BloodDonor;

public interface BloodDonorService {
	 List<BloodDonor> getAllBloodDonorsByCityAndBloodGroup(String city, String bloodGroup);
	    BloodDonor saveBloodDonor(BloodDonor donor);
	    List<BloodDonor> getAllBloodDonors();
}
