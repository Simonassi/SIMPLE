package situations.esper.definition.influenza;

import situations.esper.control.Situation;

import com.espertech.esper.client.EventBean;

public class IntermittentFever extends Situation{
	private Fever[] fevers = new Fever[2];

	public Fever getFevers(int index) {
		return fevers[index];
	}

	public void setFevers(int index, Fever f) {
		fevers[index] = f;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		IntermittentFever intermittentfever = new IntermittentFever();
		
		try{
	        intermittentfever.setSitName("IntermittentFever");
	        intermittentfever.setFevers(0, (Fever)event.get("f1"));
	        intermittentfever.setFevers(1, (Fever)event.get("f2"));
		}catch(Exception e){
    		System.out.println("IntermittentFever: " + e);
    	}
		
		return intermittentfever;
	}
	
	@Override
	public Object doActionAtCreateDeactivationEvent() {
		IntermittentFever intermittentfever = new IntermittentFever();
		
		try{
	        intermittentfever.setSitName("IntermittentFever");
	        intermittentfever.setFevers(0, this.getFevers(0));
	        intermittentfever.setFevers(1, this.getFevers(1));
		}catch(Exception e){
    		System.out.println("IntermittentFever: " + e);
    	}
		
		return intermittentfever;
	}
	
}
