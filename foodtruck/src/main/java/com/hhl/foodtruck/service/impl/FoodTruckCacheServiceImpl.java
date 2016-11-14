package com.hhl.foodtruck.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhl.foodtruck.cache.FoodTruckCache;
import com.hhl.foodtruck.domain.FoodTruck;
import com.hhl.foodtruck.service.FoodTruckCacheService;
import com.hhl.foodtruck.service.RestService;
import com.hhl.foodtruck.utils.TestUtils;

@Service
public class FoodTruckCacheServiceImpl implements FoodTruckCacheService {

    private static String SF_FOOD_TRUCK_URL = "http://data.sfgov.org/resource/rqzj-sfat.json";
    
    // TODO remove the below hard code. Move it to resources.
    private static final String JSON_FILE_NAME = "sf-readablefoodtruck.json";

    @Autowired
    private RestService restService;

    public FoodTruckCacheServiceImpl() throws IOException {
        initializeCache(TestUtils.getResourceAsString(JSON_FILE_NAME));
    }
    
    @Override
    public Collection<FoodTruck> getListOfFoodTrucksNearLocation(final double latitude, final double longitude) {
        return FoodTruckCache.getFoodTruckNearLocation(latitude, longitude);
    }

    @Override
    public void initializeCache(final String foodTruckJson) {
        FoodTruckCache.setCacheFromJson(foodTruckJson);
    }

    @Override
    public void updateCacheFromSFDataUrl() {
        final FoodTruck[] resp = this.restService.getUnsecureRestTemplate().getForObject(SF_FOOD_TRUCK_URL, FoodTruck[].class);
        final Collection<FoodTruck> foodTruckCollection = new HashSet<>(Arrays.asList(resp));
        FoodTruckCache.setCache(foodTruckCollection);
    }
    
    @Override
    public void printFoodTruckCacheMap() {
        FoodTruckCache.printMapString();
    }
}
