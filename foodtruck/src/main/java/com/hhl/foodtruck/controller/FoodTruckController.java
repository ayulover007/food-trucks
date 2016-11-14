package com.hhl.foodtruck.controller;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hhl.foodtruck.base.WebResponse;
import com.hhl.foodtruck.domain.FoodTruck;
import com.hhl.foodtruck.service.FoodTruckCacheService;
import com.hhl.foodtruck.utils.TestUtils;

@RestController
@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
public class FoodTruckController {

	private static final String JSON_FILE_NAME = "sf-readablefoodtruck.json";

	public final FoodTruckCacheService foodTruckCacheService;

	@Autowired
	public FoodTruckController(FoodTruckCacheService inFoodTruckCacheService) {
		this.foodTruckCacheService = inFoodTruckCacheService;
	}

	/**
	 * Rest api to initialize the food truck cache.
	 * 
	 * @return Success.
	 * @throws IOException
	 *             if food truck json file not found.
	 */
	@RequestMapping(value = "/initialCache")
	@ResponseBody
	public WebResponse<String> initializeCache() throws IOException {
		this.foodTruckCacheService.initializeCache(TestUtils
				.getResourceAsString(JSON_FILE_NAME));
		return new WebResponse<String>("SUCCESS");
	}

	/**
	 * Rest api to get a list of food trucks at the specified latitude and
	 * longitude.
	 * 
	 * @param lantitude
	 *            lantitude.
	 * @param longitude
	 *            longitude.
	 * @return Collection of food trucks.
	 */
	@RequestMapping(value = "getFoodTrucks")
	@ResponseBody
	WebResponse<Collection<FoodTruck>> getListOfFoodTrucks(
			@RequestParam("lantitude") final double lantitude,
			@RequestParam("longitude") final double longitude) {
		final WebResponse<Collection<FoodTruck>> response = new WebResponse<>(
				this.foodTruckCacheService.getListOfFoodTrucksNearLocation(
						lantitude, longitude));
		return response;
	}
}
