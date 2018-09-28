package com.taxation.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.taxation.service.impl.CleaningService;

@Component
public class TaxationSchedular {
	@Autowired
	private	CleaningService	cleaningService;
	
	@Scheduled(fixedRate = 1000*60*10)
	public void call() {
		cleaningService.clearDanglingDocuments();
		//System.out.println("Schedular is running"); 
	}
}
