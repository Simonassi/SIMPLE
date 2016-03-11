package situations.esper.control;

import situations.esper.listener.SituationListener;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPPreparedStatement;
import com.espertech.esper.client.StatementAwareUpdateListener;

public class SituationDefinition {
	String eplActivate;
	String eplDeactivate;
	
	EPPreparedStatement prepared_a;
	EPPreparedStatement prepared_d;
	
	String ID_A;
	String ID_D;
	String ID_S;
	
	int numberOfInstances;
	
	StatementAwareUpdateListener listener;
	EPAdministrator epAdm;
	
	String sitName;
	
	public SituationDefinition(String eplA, String eplD, String sitName, EPAdministrator epAdm){
		this.eplActivate = eplA;
		this.eplDeactivate = eplD;
		this.numberOfInstances = 0;
		this.epAdm = epAdm;
		this.sitName = sitName;
		
		prepared_a = epAdm.prepareEPL(eplA);
		prepared_d = epAdm.prepareEPL(eplD);
	}
	
	public SituationDefinition(String eplA, String eplD, String sitName, EPAdministrator epAdm, StatementAwareUpdateListener listener){
		this(eplA, eplD, sitName, epAdm);
		this.listener = listener;
	}
	
	public void start(String objectsA[], String objectsD[], StatementAwareUpdateListener situationListener){
		
		// Statement already created, just restart
		if(continueStatement()) return;
		
		// EPL with parameters
		if(objectsA != null){
			int i, n = objectsA.length;
			for(i = 1; i <= n; i++){
				prepared_a.setObject(i, objectsA[i-1]);
			}
		}
		if(objectsD != null){
			int i, n = objectsD.length;
			for(i = 1; i <= n; i++){
				prepared_d.setObject(i, objectsD[i-1]);
			}
		}
		
		ID_A = this.sitName + this.numberOfInstances +"_a";
		ID_D = this.sitName + this.numberOfInstances +"_d";
		
		epAdm.create(prepared_a, ID_A).addListener(listener);
		epAdm.create(prepared_d, ID_D).addListener(listener);
		
		if(situationListener == null) situationListener = new SituationListener();
		
		// first time
		if(numberOfInstances == 0){
			ID_S = sitName +  this.numberOfInstances;
			epAdm.createEPL("select * from " + sitName, ID_S ).addListener(situationListener);
		}
		
		numberOfInstances++;
	}
	
	public void start(){
		this.start(null, null, null);
	}
	
	public void start(StatementAwareUpdateListener situationListener){
		this.start(null, null, situationListener);
	}
	
	public void start(String objectsA[], String objectsD[]){
		this.start(objectsA, objectsD, null);
	}
	
	private boolean continueStatement(){
		if(epAdm.getStatement(ID_A) == null) return false;
		
		if(epAdm.getStatement(ID_A).isStopped()){
			
			epAdm.getStatement(ID_A).start();
			epAdm.getStatement(ID_D).start();
			epAdm.getStatement(ID_S).start();
			return true;
		}else{
			return false;
		}
	}
	
	public void destroy(){
		if(epAdm.getStatement(ID_A) == null) return;
		
		if(!epAdm.getStatement(ID_A).isDestroyed() ){
			
			epAdm.getStatement(ID_A).destroy();
			epAdm.getStatement(ID_D).destroy();
			epAdm.getStatement(ID_S).destroy();
		}
	}
	
	public void stop(){
		if(epAdm.getStatement(ID_A) == null) return;
		
		if(epAdm.getStatement(ID_A).isStarted()){
			
			epAdm.getStatement(ID_A).stop();
			epAdm.getStatement(ID_D).stop();
			epAdm.getStatement(ID_S).stop();
		}
	}
}
