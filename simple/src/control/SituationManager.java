package situations.esper.control;

import java.util.HashMap;

import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.StatementAwareUpdateListener;

public class SituationManager {
	/*
	 * Para cada EPL diferente (utilzada como chave da hash) existe um vetor
	 * Cada posicao do vetor armazena a ocorrencia de uma situacao (objeto)
	 * geradas pela respectiva EPL
	 */
	private static HashMap<String, Situation> situations = null;
	private static HashMap<String, SituationDefinition> situationDefinitions = null;
	
	/* Apagar */
	public static HashMap<String, Situation> getSituation(){
		return situations;
	}
	
	public SituationManager(){
		situations = new HashMap<String, Situation>();
		situationDefinitions = new HashMap<String, SituationDefinition>();
	}
	
	public HashMap<String, Situation> getSituations(){
		return SituationManager.situations;
	}
	
	public static void setSituationAt(String key, Situation sit){
		situations.put(key, sit);
	}
	
	public static Situation getSituationAt(String key){
		return situations.get(key);
	}
	
	public void start(EPAdministrator epAdm, Situation sit, StatementAwareUpdateListener listener){
		
		SituationDefinition sitDef = new SituationDefinition(sit.getEplA(), sit.getEplD(), sit.getSitName(), epAdm, listener);
		situationDefinitions.put(sit.getSitName(), sitDef);
	}
	
	public SituationDefinition getSituationDefinition(String sitName){
		return situationDefinitions.get(sitName);
	}
}
