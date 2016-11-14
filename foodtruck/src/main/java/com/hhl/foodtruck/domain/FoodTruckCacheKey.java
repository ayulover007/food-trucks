package com.hhl.foodtruck.domain;

import com.hhl.foodtruck.base.Orientation;

public class FoodTruckCacheKey {
	
	private int distanceFromCenter;

	private Orientation direction;

	public FoodTruckCacheKey() {

	}

	public final int getDistanceFromCenter() {
		return distanceFromCenter;
	}

	public final void setDistanceFromCenter(final int distanceFromCenter) {
		this.distanceFromCenter = distanceFromCenter;
	}

	public final Orientation getDirection() {
		return direction;
	}

	public final void setDirection(final Orientation direction) {
		this.direction = direction;
	}

	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + distanceFromCenter;
		return result;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		FoodTruckCacheKey other = (FoodTruckCacheKey) obj;
		if (direction != other.direction) {
			return false;
		}
		if (distanceFromCenter != other.distanceFromCenter) {
			return false;
		}
		return true;
	}

	@Override
	public final String toString() {
		return "FoodTruckCacheKey [distanceFromCenter=" + distanceFromCenter
				+ ", direction=" + direction + "]";
	}
}
