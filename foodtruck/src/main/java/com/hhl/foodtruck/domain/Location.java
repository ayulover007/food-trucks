package com.hhl.foodtruck.domain;

public class Location {
	
	private double latitude;
	private double longitude;
	private boolean needs_recoding;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public boolean isNeeds_recoding() {
		return needs_recoding;
	}

	public void setNeeds_recoding(boolean needs_recoding) {
		this.needs_recoding = needs_recoding;
	}
}
