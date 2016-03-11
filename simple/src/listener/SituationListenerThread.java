package situations.esper.listener;

import situations.esper.control.Situation;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;

public class SituationListenerThread implements Runnable{
	
	private EventBean[] newEvents;
	//private EventBean[] oldEvents;
	//private EPStatement stmt;
	//private EPServiceProvider epService;
	
	public SituationListenerThread(EventBean[] newEvents, EventBean[] oldEvents, EPStatement stmt, EPServiceProvider epService) {

		this.newEvents = newEvents;
		//this.oldEvents = oldEvents;
		//this.stmt = stmt;
		//this.epService = epService;
	}

	
	@Override
	public void run() {
		Situation sit = (Situation)newEvents[0].getUnderlying();
        if(sit.isActived()){
        	sit.doActionAtActivation(newEvents[0]);
        }else{
        	sit.doActionAtDeactivation(newEvents[0]);
        }
	}

}
