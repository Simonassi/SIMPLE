package situations.esper.model.influenza;

public class City {
	String name;
	double longitude, latitude;
	
	public City(String name, double d, double e){
		this.name = name;
		this.latitude = d;
		this.longitude = e;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public static double distance(City city1, City city2) {
		double lon1, lon2, lat1, lat2;
		
		lat1 = city1.getLatitude();
		lon1 = city1.getLongitude();
		
		lat2 = city2.getLatitude();
		lon2 = city2.getLongitude();
		
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		
		dist = dist * 1.609344;
		
		return (dist);
	}
	
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
	
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

}
