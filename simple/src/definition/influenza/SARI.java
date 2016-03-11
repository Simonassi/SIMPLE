package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.Patient;

import com.espertech.esper.client.EventBean;

public class SARI extends Situation {
	private Patient patient;
	private ILI ili;
	private RequiresHospitalization requiresHospitalization;
	
	public SARI(){
		
		setSitName("SARI");
		
		setEplA("select i.patient as patient, i, rh, i.key as key1, rh.key as key2 "
				+ "from pattern[ "
				+ "              every ("
				+ "                     ("
				+ "                      every i=ILI(actived = true, patient.name = ?) ->"
				+ "                      ( every rh = RequiresHospitalization(actived = true, patient.name = i.patient.name)"
				+ "	                       and not ILI(actived = false, id = i.id) ) where timer:within(10 sec)"
				+ "                     )"
				
				+ "                     or "
				
				+ "                     ("
				+ "                      every rh = RequiresHospitalization(actived = true, patient.name = ?) ->"
				+ "                      ( every i=ILI(actived = true, patient.name = rh.patient.name)"
				+ "	                       and not RequiresHospitalization(actived = false, id = rh.id) ) where timer:within(10 sec)"
				+ "                     )"
				+ "                    )"
				+ "           ]");
		/*
		setEplD("select s, s.ili.key as key1, s.requiresHospitalization.key as key2 "
			      + "from "
			      + "	SARI.std:unique(id) as s, "
			      + "	ILI.win:keepall() as i, "
			      + "	RequiresHospitalization.win:keepall() as rh "
				  + "where "
				  + "	s.actived = true "
				  + "	and s.patient.name = ? "
				  + "	and"
				  + "   	("
				  + "    	(s.ili.id = i.id and i.actived = false) "
				  + "    	or "
				  + "    	(s.requiresHospitalization.id = rh.id and rh.actived = false) "
				  + "   	)");
		*/
		setEplD("select s, s.ili.key as key1, s.requiresHospitalization.key as key2 "
			      + "from "
			      + "	SARI.std:unique(id) as s, "
			      + "	ILI.std:lastevent() as i, "
			      + "	RequiresHospitalization.std:lastevent() as rh "
				  + "where "
				  + "	s.actived = true "
				  + "	and s.patient.name = ? "
				  + "	and"
				  + "   	("
				  + "    	(s.ili.id = i.id and i.actived = false) "
				  + "    	or "
				  + "    	(s.requiresHospitalization.id = rh.id and rh.actived = false) "
				  + "   	)");
		
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public ILI getIli() {
		return ili;
	}

	public void setIli(ILI ili) {
		this.ili = ili;
	}

	public RequiresHospitalization getRequiresHospitalization() {
		return requiresHospitalization;
	}

	public void setRequiresHospitalization(
			RequiresHospitalization requiresHospitalization) {
		this.requiresHospitalization = requiresHospitalization;
	}
	
	@Override
	public Situation createNewSit(EventBean event) {
		SARI sari = new SARI();
        
		try{
			sari.setSitName("SARI");
			sari.setIli( (ILI)event.get("i"));
			sari.setRequiresHospitalization( (RequiresHospitalization)event.get("rh"));
			sari.setPatient(sari.getIli().getPatient());
		}catch(Exception e){
    		System.out.println("SARI: " + e);
    	}
		
		return sari;
	}
		
	@Override
	public Object doActionAtCreateDeactivationEvent(){
		SARI sari = new SARI();
        
		try{
			sari.setSitName("SARI");
			sari.setPatient( this.getPatient());
			sari.setIli( this.getIli());
			sari.setRequiresHospitalization( this.getRequiresHospitalization());
		}catch(Exception e){
    		System.out.println("SARI: " + e);
    	}
		
		return sari;
	}
		
	/*
	@Override
	public void doActionAtActivation(EventBean event) {
		// TODO Auto-generated method stub
		super.doActionAtActivation(event);
		System.out.println(this.getRequiresHospitalization().getId());
		System.out.println(this.getIli().getId());
	}
	*/
}
