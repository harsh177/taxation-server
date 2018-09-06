package com.taxation.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaxationSchedular {
	@Scheduled(fixedRate = 10000)
	public void call() {
		//System.out.println("Schedular is running"); 
	}
}
