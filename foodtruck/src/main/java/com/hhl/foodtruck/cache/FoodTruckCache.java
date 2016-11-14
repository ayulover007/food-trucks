package com.hhl.foodtruck.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hhl.foodtruck.domain.FoodTruck;
import com.hhl.foodtruck.domain.FoodTruckCacheKey;
import com.hhl.foodtruck.utils.GeoDistanceCalculatorUtil;
import com.hhl.foodtruck.utils.JSONUtils;

public final class FoodTruckCache {

	static Map<FoodTruckCacheKey, Collection<FoodTruck>> foodTruckCacheMap = new HashMap<>();

	private static final double sfCenterLatitude = 37.785245;

	private static final double sfCenterLongitude = -122.409533;

	private static final char DISTANCE_UNITS = 'M';

	/**
	 * Used for building up the food truck cache map with the specified json.
	 * 
	 * @param foodTruckJson
	 *            Collection of food trucks information.
	 */
	public static void setCacheFromJson(final String foodTruckJson) {
		final Collection<FoodTruck> foodTruckCollection = JSONUtils.fromJSON(
				foodTruckJson, new TypeReference<Collection<FoodTruck>>() {
				});
		setCache(foodTruckCollection);
	}

	/**
	 * Used for building up the food truck cache map with the specified
	 * collection of food trucks.
	 * 
	 * @param foodTruckCollection
	 *            collection of food trucks.
	 */
	public synchronized static void setCache(
			final Collection<FoodTruck> foodTruckCollection) {
		for (FoodTruck rec : foodTruckCollection) {
			final FoodTruckCacheKey cacheKey = createFoodTruckCacheKey(
					rec.getLatitude(), rec.getLongitude());
			Collection<FoodTruck> foodTrucks;
			if (foodTruckCacheMap.containsKey(cacheKey)) {
				foodTrucks = foodTruckCacheMap.get(cacheKey);
				if (!CollectionUtils.isEmpty(foodTrucks)) {
					foodTrucks.add(rec);
					continue;
				}
			}
			foodTrucks = new HashSet<>();
			foodTrucks.add(rec);
			foodTruckCacheMap.put(cacheKey, foodTrucks);
		}
	}

	private static FoodTruckCacheKey createFoodTruckCacheKey(
			final double latitude, final double longitude) {
		final FoodTruckCacheKey key = new FoodTruckCacheKey();
		final double distanceFromCenter = GeoDistanceCalculatorUtil.distance(
				sfCenterLatitude, sfCenterLongitude, latitude, longitude,
				DISTANCE_UNITS);
		key.setDistanceFromCenter((int) ((distanceFromCenter) * 100));
		key.setDirection(GeoDistanceCalculatorUtil.getGeoOrientation(latitude,
				longitude));
		return key;
	}

	public static Collection<FoodTruck> getFoodTruckNearLocation(
			final double latitude, final double longitude) {
		final FoodTruckCacheKey cacheKey = createFoodTruckCacheKey(latitude,
				longitude);
		return foodTruckCacheMap.get(cacheKey);
	}

	public static void printMapString() {
		System.out.println("Size of Food Truck map is = "
				+ foodTruckCacheMap.size());

		for (FoodTruckCacheKey key : foodTruckCacheMap.keySet()) {
			System.out.println("Distance is = " + key.getDistanceFromCenter()
					+ " Direction is = " + key.getDirection());
			System.out.println(" Value size is = "
					+ foodTruckCacheMap.get(key).size());
		}
	}
}
