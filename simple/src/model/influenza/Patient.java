package situations.esper.model.influenza;


public class Patient extends Person{
	
	boolean requiresHospitalization; 
	String[] symptoms;
	
	public Patient(String name, City city, int temperature, boolean requiresHospitalization, String[] symptoms) {
		super(name, temperature, city);
		this.requiresHospitalization = requiresHospitalization;
		this.symptoms = symptoms;
	}

	public String[] getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String[] symptoms) {
		this.symptoms = symptoms;
	}

	public boolean getRequiresHospitalization() {
		return requiresHospitalization;
	}

	public void setRequiresHospitalization(boolean requiresHospitalization) {
		this.requiresHospitalization = requiresHospitalization;
	}

}
