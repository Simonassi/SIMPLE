package situations.esper.model.influenza;

public class Person {
	private String name;
	private int temperature;
	private City city;
	private String key;
	
	public Person(String name, int temperature, City city){
		this.name = name;
		this.temperature = temperature;
		this.city = city;
		this.key = name; // new code
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}


