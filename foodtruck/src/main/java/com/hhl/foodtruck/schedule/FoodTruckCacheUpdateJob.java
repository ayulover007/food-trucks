package com.hhl.foodtruck.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hhl.foodtruck.service.FoodTruckCacheService;

@Service
public class FoodTruckCacheUpdateJob {

	private static final int FIXED_DELAY_IN_MILLS = 1000;

	@Autowired
	private FoodTruckCacheService foodTruckCacheService;

	@Scheduled(fixedDelay = FIXED_DELAY_IN_MILLS)
	protected void executeJob() {
		this.foodTruckCacheService.updateCacheFromSFDataUrl();
	}
}
