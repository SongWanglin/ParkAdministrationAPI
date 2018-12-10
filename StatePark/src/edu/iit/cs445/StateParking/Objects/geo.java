package edu.iit.cs445.StateParking.Objects;

public class geo {
	private double lat;
	private double lng;
	public geo(double lat, double lng) {
		this.setLat(lat);
		this.setLng(lng);
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat)
		throws RuntimeException{
		if (lat>90 || lat<-90)
			throw new RuntimeException("Invalid value");
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng)
		throws RuntimeException{
		if (lng>180 || lng<-180)
			throw new RuntimeException("Invalid value");
		this.lng = lng;
	}
	
}
