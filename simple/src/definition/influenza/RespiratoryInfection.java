package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.Patient;

import com.espertech.esper.client.EventBean;

public class RespiratoryInfection extends Situation {
	private Patient patient;

	public RespiratoryInfection(){
		
		setSitName("RespiratoryInfection");
		
		setEplA("select Patient, Patient.key as key1 from Patient(symptoms.anyOf(v => v = 'respiratory infection'), name = ?) as Patient");
		
		/*
		setEplD("select Patient.key as key1 "
			 + "from "
			 + "     RespiratoryInfection.std:unique(id) as RespiratoryInfection, "
			 + "     Patient.std:unique(key) as Patient "
			 + "where "
			 + "     RespiratoryInfection.actived = true"
			 + "     and"
			 + "        ("
			 + "         RespiratoryInfection.patient.key = Patient.key and"
			 + "         Patient.name = ? and"
			 + "         not (symptoms.anyOf(v => v = 'respiratory infection') )"
			 + "        ) ");
		*/
		
		setEplD("select Patient.key as key1 "
				 + "from "
				 + "     RespiratoryInfection.std:unique(id) as RespiratoryInfection, "
				 + "     Patient.std:lastevent() as Patient "
				 + "where "
				 + "     RespiratoryInfection.actived = true"
				 + "     and"
				 + "        ("
				 + "         RespiratoryInfection.patient.key = Patient.key and"
				 + "         Patient.name = ? and"
				 + "         not (symptoms.anyOf(v => v = 'respiratory infection') )"
				 + "        ) ");
		
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		RespiratoryInfection respiratoryInfection = new RespiratoryInfection();
        
		try{
			respiratoryInfection.setSitName("RespiratoryInfection");
			respiratoryInfection.setPatient((Patient)event.get("Patient"));
		}catch(Exception e){
    		System.out.println("RespiratoryInfection: " + e);
    	}
		
		return respiratoryInfection;
	}
	
	@Override
	public Object doActionAtCreateDeactivationEvent() {
		RespiratoryInfection respiratoryInfection = new RespiratoryInfection();
        
		try{
			respiratoryInfection.setSitName("RespiratoryInfection");
			respiratoryInfection.setPatient(this.getPatient());
		}catch(Exception e){
    		System.out.println("RespiratoryInfection: " + e);
    	}
		
		return respiratoryInfection;
	}
	
}
