package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.Patient;

import com.espertech.esper.client.EventBean;

public class RequiresHospitalization extends Situation {
	private Patient patient;

	public RequiresHospitalization(){
		
		setSitName("RequiresHospitalization");
		
		setEplA("select Patient, Patient.key as key1 from Patient(requiresHospitalization = true, name = ?) as Patient");
		
		/*
		setEplD("select Patient.key as key1 "
			 + "from "
			 + "     RequiresHospitalization.std:unique(id) as RequiresHospitalization, "
			 + "     Patient.std:unique(key) as Patient "
			 + "where "
			 + "     RequiresHospitalization.actived = true "
			 + "     and "
			 + "        ( "
			 + "         RequiresHospitalization.patient.key = Patient.key and "
			 + "         Patient.name = ? and "
			 + "         not (Patient.requiresHospitalization = true) "
			 + "        )");
		*/
		
		setEplD("select Patient.key as key1 "
				 + "from "
				 + "     RequiresHospitalization.std:unique(id) as RequiresHospitalization, "
				 + "     Patient.std:lastevent() as Patient "
				 + "where "
				 + "     RequiresHospitalization.actived = true "
				 + "     and "
				 + "        ( "
				 + "         RequiresHospitalization.patient.key = Patient.key and "
				 + "         Patient.name = ? and "
				 + "         not (Patient.requiresHospitalization = true) "
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
		RequiresHospitalization requiresHospitalization = new RequiresHospitalization();
        
		try{
			requiresHospitalization.setSitName("RequiresHospitalization");
			requiresHospitalization.setPatient((Patient)event.get("Patient"));
		}catch(Exception e){
    		System.out.println("RequiresHospitalization: " + e);
    	}
		
		return requiresHospitalization;
	}
	
	@Override
	public Object doActionAtCreateDeactivationEvent() {
		RequiresHospitalization requiresHospitalization = new RequiresHospitalization();
        
		try{
			requiresHospitalization.setSitName("RequiresHospitalization");
			requiresHospitalization.setPatient(this.getPatient());
		}catch(Exception e){
    		System.out.println("RequiresHospitalization: " + e);
    	}
		
		return requiresHospitalization;
	}
	
}
