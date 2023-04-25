package com.dto;

public class Coordonnee {
	private double latitude;
	private double longitude;
	
	public Coordonnee(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}
	
	public double calculDistance(Coordonnee autreCo) {
		double R = 6371; // rayon moyen de la Terre en km
	    double dLat = Math.toRadians(this.getLatitude() - autreCo.getLatitude());
	    double dLon = Math.toRadians(this.getLongitude() - autreCo.getLongitude());
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	            + Math.cos(Math.toRadians(this.getLatitude())) * Math.cos(Math.toRadians(autreCo.getLatitude()))
	            * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c;
	    
	    return distance;
	}
	
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
}
