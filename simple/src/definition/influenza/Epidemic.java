package situations.esper.definition.influenza;

import situations.esper.control.Situation;
import situations.esper.model.influenza.City;

import com.espertech.esper.client.EventBean;

public class Epidemic extends Situation {
	private long numberOfCases;
	private City city;
	
	public Epidemic(){
		
		setSitName("Epidemic");
		
		setEplA("select count(*) as cases, patient.city as city from SARI as SARI where ((select count(*) from SARI(actived = true, patient.city.name = ?).win:keepall()) - (select count(*) from SARI(actived = false, patient.city.name = ?).win:keepall())) > cast(?, int)");
		setEplD("select count(*) as cases, patient.city as city from SARI as SARI where ((select count(*) from SARI(actived = true, patient.city.name = ?).win:keepall()) - (select count(*) from SARI(actived = false, patient.city.name = ?).win:keepall())) <= cast(?, int)");
	}
	
	public long getNumberOfCases() {
		return numberOfCases;
	}

	public void setNumberOfCases(long numberOfCases) {
		this.numberOfCases = numberOfCases;
	}
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		Epidemic epidemic = new Epidemic();
        
		try{
			epidemic.setSitName("Epidemic");
			epidemic.setNumberOfCases( (long)event.get("cases"));
			epidemic.setCity( (City)event.get("city"));
		}catch(Exception e){
    		System.out.println("Epidemic: " + e);
    	}
		
		return epidemic;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		Epidemic epidemic = new Epidemic();
        
		try{
			epidemic.setSitName("Epidemic");
			epidemic.setNumberOfCases( this.getNumberOfCases());
			epidemic.setCity( this.getCity());
		}catch(Exception e){
    		System.out.println("Epidemic: " + e);
    	}
		
		return epidemic;
	}

	@Override
	public void doActionAtActivation(EventBean event) {
		try{
			super.doActionAtActivation(event);
			Epidemic sit = (Epidemic)event.getUnderlying();
			System.out.println("Situation Epidemic - City Name = " + sit.getCity().getName());
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	@Override
	public void doActionAtDeactivation(EventBean event) {
		try{
			super.doActionAtDeactivation(event);
			Epidemic sit = (Epidemic)event.getUnderlying();
			System.out.println("Situation Epidemic - City Name = " + sit.getCity().getName());
		}catch(Exception e){
			System.out.println(e);
		}
	}
}
