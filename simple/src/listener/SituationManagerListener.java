package situations.esper.listener;

import situations.esper.control.Situation;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

public class SituationManagerListener<GenericType extends Situation> implements StatementAwareUpdateListener {
    
	public Class<GenericType> typeArgumentClass;
	
	public SituationManagerListener(Class<GenericType> typeArgumentClass){
		this.typeArgumentClass = typeArgumentClass;
	}
	
	public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement stmt, EPServiceProvider epService) {
		SituationManagerListenerThread<GenericType> listener = new SituationManagerListenerThread<GenericType>(); 
		listener.setVariables(newEvents,oldEvents,stmt,epService, typeArgumentClass);
		Thread thread = new Thread(listener);
		thread.start();	
		
    }
}