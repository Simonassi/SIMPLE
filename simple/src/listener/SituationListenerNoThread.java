package listener;

import control.Situation;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

public class SituationListenerNoThread implements StatementAwareUpdateListener {
    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement stmt, EPServiceProvider epService) {
        
    	Situation sit = (Situation)newEvents[0].getUnderlying();
        if(sit.isActived()){
        	sit.doActionAtActivation(newEvents[0]);
        }else{
        	sit.doActionAtDeactivation(newEvents[0]);
        }
        
    }
}