package listener;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

public class SituationListener implements StatementAwareUpdateListener {
    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement stmt, EPServiceProvider epService) {
        
    	SituationListenerThread listener = new SituationListenerThread(newEvents,oldEvents,stmt,epService); 
		Thread thread = new Thread(listener);
		thread.start();	
        
    }
}