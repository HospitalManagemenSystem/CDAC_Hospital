package com.hms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entity.BloodDonor;
import com.hms.entity.BloodGroup;

public interface BloodDonorRepository extends JpaRepository<BloodDonor, Long>{
	 List<BloodDonor> findByCityAndBloodGroup(String city, BloodGroup bloodGroup);
	    
	    List<BloodDonor> findByBloodGroup(BloodGroup bloodGroup);
	    
	    List<BloodDonor> findByCity(String city);
}
