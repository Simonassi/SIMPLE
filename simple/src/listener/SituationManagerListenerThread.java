package listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import control.Situation;
import control.SituationManager;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;

public class SituationManagerListenerThread<GenericType extends Situation> implements Runnable {
	
	private EventBean[] newEvents;
	//private EventBean[] oldEvents;
	private EPStatement stmt;
	private EPServiceProvider epService;
	
	public Class<GenericType> typeArgumentClass;
	public static int id = 0;
	
	
	
	public void setVariables(EventBean[] newEvents, EventBean[] oldEvents, EPStatement stmt, EPServiceProvider epService, Class<GenericType> typeArgumentClass) {

		this.newEvents = newEvents;
		//this.oldEvents = oldEvents;
		this.stmt = stmt;
		this.epService = epService;
		this.typeArgumentClass = typeArgumentClass;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void run() {
		for(EventBean event : newEvents){
			// Verifying is an active or deactive event
	    	String stmtName = stmt.getName();
	    	String[] stmtNameParts = stmtName.split("_");
	    	Boolean isActiveStmt = (stmtNameParts[stmtNameParts.length-1].equals("a"));
	    	
	    	// Replace the last char to "a" to find the name of the active event statement. Then, use it text as key to find the situations objects
	    	EPStatement activeStmt = epService.getEPAdministrator().getStatement(stmtName.substring(0, stmtName.length()-1) + "a");
	    	String key = activeStmt.getText();
	    	
			// Initialize variables
	    	int i = 1;
	    	Boolean hasKeyValue = true;
	    	ArrayList<String> keys = new ArrayList<String>();
	    	String event_key = null;
	    	
	    	// try to use at least one key value
	    	try{
	    		event_key = (String)event.get("key"+i);
	    	}catch(Exception e){ // if a exception is detected it means that the current EPL do has a key value, then set hasKeyValue to false
	    		hasKeyValue = false;
    		}
	    	
	    	// if the current EPL use key value
	    	if(hasKeyValue){
		    	// Store every key value in a array => first verification has event_key = event.get("key1");
		    	while( event_key != null){
		    		keys.add(event_key);
		    		i++;
		    		try{
		    			// try to get the next key value 
		    			event_key = (String)event.get("key"+i);
		    		}catch(Exception e){ // if a exception is detected it means that do not exists a key+i value, then stop the loop
		    			break;
		    		}
		    	}
		    	
		    	// Sort the arrays to be sure that the event order detection will not affect the result
		    	Collections.sort(keys);
		    	
		    	// Create a new key by the composition of the EPL string and every unique key value
		    	for(String string_key : keys){
		    		key += string_key;
		    	}
	    	}
	    	
	    	// Loking for a situation for this stmt
	    	Situation sit = SituationManager.getSituationAt(key);
	
	    	// if stmt is an active event and 
	    	// (    sit is null => the situation was never activated
	    	//   or !sit.isActivated() => the last instance is deactivated
	    	// )	    	
	    	if(	(isActiveStmt && (sit == null || !sit.isActived()))  ){
	    		
	    		// Create a situation object
	    		GenericType sit_event = null;
	    		
	    		try {
					sit_event = typeArgumentClass.newInstance();
					sit_event = (GenericType) sit_event.createNewSit(event);
					sit_event.setActived(true);
					sit_event.setStart_time(new Date());
					sit_event.setId(id);
					sit_event.setKey("@"+id+"S"); // new key code
					id++;
					
					sit_event.doActionAtCreateActivationEvent(newEvents); // newEvents[0]
				} catch (Exception e) {
					System.out.println(e);
				} 
	    		
		        // Insert the object into the situations hash
	    		SituationManager.setSituationAt(key, sit_event);
		        
		        // Sending the event 
				epService.getEPRuntime().sendEvent(sit_event);
	    	}else{
	  		
	    		// if stmt is a deactive event
	    		// and sit is not null => if is null the situation was never activated and then is deactivated
	    		// and the last instance of the situation is activated
	    		if( !isActiveStmt && sit != null && sit.isActived()){
	    			
	    			// Create a situation deactivatin object
	    			GenericType old_sit_event = (GenericType) sit;
	    			GenericType sit_event = (GenericType) old_sit_event.doActionAtCreateDeactivationEvent();
	    			sit_event.setActived(false);
	    			sit_event.setStart_time(old_sit_event.getStart_time());
	    			sit_event.setEnd_time(new Date());
	    			sit_event.setId(old_sit_event.getId());
	    			sit_event.setKey(old_sit_event.getKey());
	    			
	    			
	    	        // Insert the object into the situations hash
	    			SituationManager.setSituationAt(key, sit_event);
	
	    	        // Sending the event 
	    			epService.getEPRuntime().sendEvent(sit_event);
	    		}
	    	}
		}
		
	}

}
