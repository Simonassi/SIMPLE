package situations.esper.model.bank;


public class Location {
	
	private double latitude;
	private double longitude;
	
	// used only to tests purposes
	public Location(double d, double e){
		this.latitude = d;
		this.longitude = e;
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
	
	public static double distance(Location location1, Location location2) {
		double lon1, lon2, lat1, lat2;
		
		lat1 = location1.getLatitude();
		lon1 = location1.getLongitude();
		
		lat2 = location2.getLatitude();
		lon2 = location2.getLongitude();
		
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
