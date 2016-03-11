package situations.esper.main;
import org.apache.log4j.PropertyConfigurator;

import situations.esper.control.SituationDefinition;
import situations.esper.control.SituationManager;
import situations.esper.definition.influenza.Cough;
import situations.esper.definition.influenza.Epidemic;
import situations.esper.definition.influenza.EpidemicSpread;
import situations.esper.definition.influenza.Fever;
import situations.esper.definition.influenza.ILI;
import situations.esper.definition.influenza.RequiresHospitalization;
import situations.esper.definition.influenza.RespiratoryInfection;
import situations.esper.definition.influenza.SARI;
import situations.esper.listener.SituationListenerNoThread;
import situations.esper.listener.SituationManagerListener;
import situations.esper.listener.SituationManagerListenerNoThread;
import situations.esper.testers.InfluenzaTester;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.StatementAwareUpdateListener;


public class MainInfluenza {
	
	public static boolean SIT_MANAGER_T = false;
	public static boolean SIT_LISTENER_T = false;
	
	public static void readConfig(String[] args){
		for (int i = 0; i < args.length; i++){
			if(args[i].equals("-sitLT")){
				SIT_LISTENER_T = true;
			}else if(args[i].equals("-sitMLT")){
				SIT_MANAGER_T = true;
			}
		}
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		readConfig(args);
		
		/* Esper Configuration */
		Configuration config = new Configuration();
		config.addEventTypeAutoName("situations.esper.model.influenza");
		config.addEventTypeAutoName("situations.esper.definition.influenza");
		config.addPlugInSingleRowFunction("cityDistance", "situations.esper.model.influenza.City", "distance");
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		EPAdministrator epAdm = epService.getEPAdministrator();
		
		/* Situation Manager Start */
		SituationManager sitManager = new SituationManager();
		
		if(SIT_MANAGER_T){
			sitManager.start(epAdm, new Fever(), new SituationManagerListener<Fever>(Fever.class));
			sitManager.start(epAdm, new Cough(), new SituationManagerListener<Cough>(Cough.class));
			sitManager.start(epAdm, new RespiratoryInfection(), new SituationManagerListener<RespiratoryInfection>(RespiratoryInfection.class));
			sitManager.start(epAdm, new RequiresHospitalization(), new SituationManagerListener<RequiresHospitalization>(RequiresHospitalization.class));
			sitManager.start(epAdm, new ILI(), new SituationManagerListener<ILI>(ILI.class));
			sitManager.start(epAdm, new SARI(), new SituationManagerListener<SARI>(SARI.class));
			sitManager.start(epAdm, new Epidemic(), new SituationManagerListener<Epidemic>(Epidemic.class));
			sitManager.start(epAdm, new EpidemicSpread(), new SituationManagerListener<EpidemicSpread>(EpidemicSpread.class));
		}else{
			sitManager.start(epAdm, new Fever(), new SituationManagerListenerNoThread<Fever>(Fever.class));
			sitManager.start(epAdm, new Cough(), new SituationManagerListenerNoThread<Cough>(Cough.class));
			sitManager.start(epAdm, new RespiratoryInfection(), new SituationManagerListenerNoThread<RespiratoryInfection>(RespiratoryInfection.class));
			sitManager.start(epAdm, new RequiresHospitalization(), new SituationManagerListenerNoThread<RequiresHospitalization>(RequiresHospitalization.class));
			sitManager.start(epAdm, new ILI(), new SituationManagerListenerNoThread<ILI>(ILI.class));
			sitManager.start(epAdm, new SARI(), new SituationManagerListenerNoThread<SARI>(SARI.class));
			sitManager.start(epAdm, new Epidemic(), new SituationManagerListenerNoThread<Epidemic>(Epidemic.class));
			sitManager.start(epAdm, new EpidemicSpread(), new SituationManagerListenerNoThread<EpidemicSpread>(EpidemicSpread.class));
		}
		
		
		SituationDefinition fever = sitManager.getSituationDefinition("Fever");
		SituationDefinition cough = sitManager.getSituationDefinition("Cough");
		SituationDefinition respiratoryInfection = sitManager.getSituationDefinition("RespiratoryInfection");
		SituationDefinition requiresHospitalization = sitManager.getSituationDefinition("RequiresHospitalization");
		SituationDefinition ili = sitManager.getSituationDefinition("ILI");
		SituationDefinition sari = sitManager.getSituationDefinition("SARI");
		SituationDefinition epidemic = sitManager.getSituationDefinition("Epidemic");
		SituationDefinition epidemicSpread = sitManager.getSituationDefinition("EpidemicSpread");
		
		String[] parameters = new String[1];
		String[] parameters2 = new String[3];
		String[] parameters3 = new String[2];
		String[] parameters6 = new String[6];
		String[] pessoas = {"Rafael", "Gabriel", "Joao", "Mario"};
		//String[] pessoas = {"Rafael"};
		
		
		StatementAwareUpdateListener listener = null;
		if(!SIT_LISTENER_T) listener = new SituationListenerNoThread();
		
		
		for(String p : pessoas){
			parameters[0] = p;
			
			parameters3[0] = p;
			parameters3[1] = p;
			
			parameters2[0] = p;
			parameters2[1] = p;
			parameters2[2] = p;
			
			parameters6[0] = p;
			parameters6[1] = p;
			parameters6[2] = p;
			parameters6[3] = p;
			parameters6[4] = p;
			parameters6[5] = p;
			
			fever.start(parameters, parameters, listener);
			cough.start(parameters, parameters, listener);
			respiratoryInfection.start(parameters, parameters, listener);
			requiresHospitalization.start(parameters, parameters, listener);
			//ili.start(parameters2, parameters, listener);
			//ili.start(parameters, parameters, listener);
			ili.start(parameters6, parameters, listener);
			sari.start(parameters3, parameters, listener);
		}
		
		String[] cidades = {"Vitoria-1", "Serra-1"};
		for(String arg : cidades){
			String[] args1 = arg.split("-");
			parameters2[0] = args1[0];
			parameters2[1] = args1[0];
			parameters2[2] = args1[1];
			epidemic.start(parameters2, parameters2);
		}
		
		epidemicSpread.start(listener);
		
		InfluenzaTester tester = new InfluenzaTester();
		//InfluenzaTester2 tester = new InfluenzaTester2();
		//InfluenzaTester4 tester = new InfluenzaTester4();
		tester.start();		
	}
}
