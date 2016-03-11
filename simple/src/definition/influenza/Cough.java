package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.Patient;

import com.espertech.esper.client.EventBean;

public class Cough extends Situation {
	private Patient patient;

	public Cough(){
		
		setSitName("Cough");
		
		setEplA("select Patient, Patient.key as key1 from Patient(symptoms.anyOf(v => v = 'cough'), name = ?) as Patient");
		
		/*
		setEplD("select Patient.key as key1 "
			 + "from "
			 + "     Cough.std:unique(id) as Cough, "
			 + "     Patient.std:unique(key) as Patient "
			 + "where"
			 + "     Cough.actived = true "
			 + "     and"
			 + "        ("
			 + "         Cough.patient.key = Patient.key and "
			 + "         Patient.name = ? and "
			 + "         not (Patient.symptoms.anyOf(v => v = 'cough') )"
			 + "        )");
		*/
		
		setEplD("select Patient.key as key1 "
				 + "from "
				 + "     Cough.std:unique(id) as Cough, "
				 + "     Patient.std:lastevent() as Patient "
				 + "where"
				 + "     Cough.actived = true "
				 + "     and"
				 + "        ("
				 + "         Cough.patient.key = Patient.key and "
				 + "         Patient.name = ? and "
				 + "         not (Patient.symptoms.anyOf(v => v = 'cough') )"
				 + "        )");
		
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		Cough cough = new Cough();
        
		try{
			cough.setSitName("Cough");
			cough.setPatient((Patient)event.get("Patient"));
		}catch(Exception e){
    		System.out.println("Cough: " + e);
    	}
		
		return cough;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		Cough cough = new Cough();
        
		try{
			cough.setSitName("Cough");
			cough.setPatient(this.patient);
		}catch(Exception e){
    		System.out.println("Cough: " + e);
    	}
		
		return cough;
	}

}
