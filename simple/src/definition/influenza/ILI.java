package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.Patient;

import com.espertech.esper.client.EventBean;

public class ILI extends Situation {
	private Patient patient;
	private Fever fever;
	private RespiratoryInfection respiratoryInfection;
	private Cough cough;

	public ILI(){
		
		setSitName("ILI");
		
		setEplA("select f, ri, c, f.key as key1, ri.key as key2, c.key as key3 "
				 + "from pattern[ every ("
				 + "                    ("
				 + "                     every f=Fever(actived = true, person.name = ?) ->"
				 + "                     ("
				 + "                      (every ri = RespiratoryInfection(actived = true, patient.name = f.person.name) "
				 + "                       and not Fever(actived=false, id = f.id) ) -> "
				 + "                      (every c = Cough(actived = true, patient.name = f.person.name)"
				 + "                       and not RespiratoryInfection(actived=false, id = ri.id)"
				 + "                       and not Fever(actived=false, id = f.id) )"
				 + "                      ) where timer:within(10 sec) "
				 + "                    )  "
				 + "                    or"
				 + "                    ("
				 + "                     every f=Fever(actived = true, person.name = ?) ->"
				 + "                     ("
				 + "                      (every c = Cough(actived = true, patient.name = f.person.name)"
				 + "                       and not Fever(actived=false, id = f.id) ) -> "
				 + "                      (every ri = RespiratoryInfection(actived = true, patient.name = f.person.name)"
				 + "                       and not Cough(actived=false, id = c.id)"
				 + "                       and not Fever(actived=false, id = f.id) )"
				 + "                     ) where timer:within(10 sec) "
				 + "                    )"
				 
				 + "                    or"
				 
				 + "                    ("
				 + "                     every ri = RespiratoryInfection(actived = true, patient.name = ?) ->"
				 + "                     ("
				 + "                      (every f = Fever(actived = true, person.name = ri.patient.name)"
				 + "                       and not RespiratoryInfection(actived=false, id = ri.id) ) -> "
				 + "                      (every c = Cough(actived = true, patient.name = ri.patient.name)"
				 + "                       and not Fever(actived=false, id = f.id)"
				 + "                       and not RespiratoryInfection(actived=false, id = ri.id) )"
				 + "                     ) where timer:within(10 sec) "
				 + "                    ) "
				 + "                    or"
				 + "                    ("
				 + "                     every ri = RespiratoryInfection(actived = true, patient.name = ?) ->"
				 + "                     ("
				 + "                      (every c = Cough(actived = true, patient.name = ri.patient.name)"
				 + "                       and not RespiratoryInfection(actived=false, id = ri.id) ) -> "
				 + "                      (every f = Fever(actived = true, person.name = ri.patient.name)"
				 + "                       and not Cough(actived=false, id = c.id)"
				 + "                       and not RespiratoryInfection(actived=false, id = ri.id) )"
				 + "                     ) where timer:within(10 sec) "
				 + "                    ) "
				 
				 + "                    or"
				 
				 + "                    ("
				 + "                     every c = Cough(actived = true, patient.name = ?) ->"
				 + "                     ("
				 + "                      (every f = Fever(actived = true, person.name = c.patient.name)  "
				 + "                       and not Cough(actived=false, id = c.id) ) -> "
				 + "                      (every ri = RespiratoryInfection(actived = true, patient.name = c.patient.name)"
				 + "                       and not Fever(actived=false, id = f.id)"
				 + "                       and not Cough(actived=false, id = c.id) )"
				 + "                     ) where timer:within(10 sec) "
				 + "                    ) "
				 + "                    or"
				 + "                    ("
				 + "                     every c = Cough(actived = true, patient.name = ?) ->"
				 + "                     ("
				 + "                      (every ri = RespiratoryInfection(actived = true, patient.name = c.patient.name)"
				 + "                       and not Cough(actived=false, id = c.id) ) -> "
				 + "                      (every f = Fever(actived = true, person.name = c.patient.name)"
				 + "                       and not RespiratoryInfection(actived=false, id = ri.id)"
				 + "                       and not Cough(actived=false, id = c.id) ) "
				 + "                     ) where timer:within(10 sec) "
				 + "                    ) "
				 
				 + ")] ");
				
				/*
				setEplD("select i,  i.fever.key as key1, i.respiratoryInfection.key as key2, i.cough.key as key3 from ILI.std:unique(id) as i, Fever.win:keepall() as f, RespiratoryInfection.win:keepall() as ri, Cough.win:keepall() as c "
						  + "where i.actived = true "
						  + "and i.patient.name = ? "
						  + "and"
						  + "   ("
						  + "    (i.fever.id = f.id and f.actived = false) "
						  + "    or "
						  + "    (i.respiratoryInfection.id = ri.id and ri.actived = false) "
						  + "    or "
						  + "    (i.cough.id = c.id and c.actived = false) "
						  + "   )");
				*/
		
				setEplD("select i,  i.fever.key as key1, i.respiratoryInfection.key as key2, i.cough.key as key3 "
						+ "from "
						+ "		ILI.std:unique(id) as i, "
						+ "		Fever.std:lastevent() as f, "
						+ "		RespiratoryInfection.std:lastevent() as ri, "
						+ "		Cough.std:lastevent() as c "
						+ "where i.actived = true "
						+ "and i.patient.name = ? "
						+ "and"
						+ "   ("
						+ "    (i.fever.id = f.id and f.actived = false) "
						+ "    or "
						+ "    (i.respiratoryInfection.id = ri.id and ri.actived = false) "
						+ "    or "
						+ "    (i.cough.id = c.id and c.actived = false) "
						+ "   )");
	}
	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Fever getFever() {
		return fever;
	}

	public void setFever(Fever fever) {
		this.fever = fever;
	}

	public RespiratoryInfection getRespiratoryInfection() {
		return respiratoryInfection;
	}

	public void setRespiratoryInfection(RespiratoryInfection respiratoryInfection) {
		this.respiratoryInfection = respiratoryInfection;
	}

	public Cough getCough() {
		return cough;
	}

	public void setCough(Cough cough) {
		this.cough = cough;
	}
	
	@Override
	public Situation createNewSit(EventBean event) {
		ILI ili = new ILI();
    	try{
			ili.setSitName("ILI");
			ili.setRespiratoryInfection( (RespiratoryInfection)event.get("ri"));
			ili.setFever( (Fever)event.get("f"));
			ili.setCough( (Cough)event.get("c"));
			ili.setPatient( ili.getRespiratoryInfection().getPatient());
    	}catch(Exception e){
    		System.out.println("ILI: " + e);
    	}
    	
		return ili;
	}
	
	@Override
	public Object doActionAtCreateDeactivationEvent() {
		ILI ili = new ILI();
    	try{
			ili.setSitName("ILI");
			ili.setPatient( this.getPatient());
			ili.setRespiratoryInfection( this.getRespiratoryInfection());
			ili.setFever( this.getFever());
			ili.setCough( this.getCough());
    	}catch(Exception e){
    		System.out.println("ILI: " + e);
    	}
    	
		return ili;
	}
	
	/*
	@Override
	public void doActionAtActivation(EventBean event) {
		// TODO Auto-generated method stub
		super.doActionAtActivation(event);
		System.out.println("C: " + this.getCough().getId());
		System.out.println("F: " + this.getFever().getId());
		System.out.println("RI: " + this.getRespiratoryInfection().getId());
	}
	*/
	
	/*
	@Override
	public void doActionAtDeactivation(EventBean event) {
		// TODO Auto-generated method stub
		super.doActionAtDeactivation(event);
		if(this.getCough() != null)	System.out.println("C: " + this.getCough().getId());
		if(this.getFever() != null)	System.out.println("F: " + this.getFever().getId());
		if(this.getRespiratoryInfection() != null)	System.out.println("RI: " + this.getRespiratoryInfection().getId());
	}
	*/
}
