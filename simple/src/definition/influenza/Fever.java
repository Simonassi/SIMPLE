package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.Person;

import com.espertech.esper.client.EventBean;

public class Fever extends Situation {
	private Person person;
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Fever(){
		
		setSitName("Fever");
		
		setEplA("select Person, Person.key as key1 from Person(temperature > 36, name = ?) as Person");
		
		/*
		setEplD("select Person.key as key1 "
			 + "from "
			 + "      Fever.std:unique(id) as Fever, "
			 + "      Person.std:unique(key) as Person "
			 + "where "
			 + "      Fever.actived = true "
			 + "      and"
			 + "         ("
			 + "           Fever.person.key = Person.key and "
			 + "           Person.name = ? and "
			 + "           not(Person.temperature > 36)"
			 + "         )");
		*/
		
		setEplD("select Person.key as key1 "
				 + "from "
				 + "      Fever.std:unique(id) as Fever, "
				 + "      Person.std:lastevent() as Person "
				 + "where "
				 + "      Fever.actived = true "
				 + "      and"
				 + "         ("
				 + "           Fever.person.key = Person.key and "
				 + "           Person.name = ? and "
				 + "           not(Person.temperature > 36)"
				 + "         )");
	}
	
	@Override
	public Situation createNewSit(EventBean event) {
		Fever fever = new Fever();
        
		try{
			fever.setSitName("Fever");
	        fever.setPerson((Person)event.get("Person"));
		}catch(Exception e){
    		System.out.println("Fever: " + e);
    	}
		
		return fever;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		Fever fever = new Fever();
        
		try{
			fever.setSitName("Fever");
	        fever.setPerson(this.getPerson());
		}catch(Exception e){
    		System.out.println("Fever: " + e);
    	}
		
		return fever;
	}

}
