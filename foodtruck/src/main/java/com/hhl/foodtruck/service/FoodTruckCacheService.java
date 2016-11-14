package com.hhl.foodtruck.service;

import java.util.Collection;

import com.hhl.foodtruck.domain.FoodTruck;

public interface FoodTruckCacheService {

	/**
	 * Get all the food trucks near the specified latitude and longitude.
	 * 
	 * @param latitude geo co-ordinates latitude.
	 * @param longitude geo co-ordinates longitude.
	 * @return Collection of food trucks.
	 */
	Collection<FoodTruck> getListOfFoodTrucksNearLocation(final double latitude, final double longitude);
	
	/**
	 * Initialize food truck cache from the json file stored at classpath/sf-readablefoodtruck.json.
	 * 
	 * @param foodTruckJson json with the array of food trucks in sf.
	 */
	void initializeCache(final String foodTruckJson);
	
	/**
	 * Updates food truck data cache from url "http://data.sfgov.org/resource/rqzj-sfat.json".
	 */
	void updateCacheFromSFDataUrl();
	
	/**
	 * For debugging purpose only.
	 */
	void printFoodTruckCacheMap();
}
