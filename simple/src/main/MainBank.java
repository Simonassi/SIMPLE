package main;
import org.apache.log4j.PropertyConfigurator;

import application.Initi;
import application.Monitoring;
import control.SituationDefinition;
import control.SituationManager;
import definition.AccountUnderObservation;
import definition.LoggedIn;
import definition.OngoingSuspiciousWithdrawal;
import definition.SuspiciousFarawayLogin;
import definition.SuspiciousParallelLogin;
import listener.SituationListenerNoThread;
import listener.SituationManagerListener;
import listener.SituationManagerListenerNoThread;
import tester.BankTester;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.StatementAwareUpdateListener;


public class MainBank {
	
	public static int NUMBER_OF_CLIENTS = 0;
	public static boolean SIT_MANAGER_T = false;
	public static boolean SIT_LISTENER_T = false;
	
	public static void readConfig(String[] args){
		for (int i = 0; i < args.length; i++){
			if(args[i].equals("-cSimulatorN")){
				i++;
				NUMBER_OF_CLIENTS = Integer.parseInt(args[i]);
			}else if(args[i].equals("-sitLT")){
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
		config.addEventTypeAutoName("situations.esper.model.bank");
		config.addEventTypeAutoName("situations.esper.definition.bank");
		config.addPlugInSingleRowFunction("locationDistance", "situations.esper.model.bank.Location", "distance");
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		EPAdministrator epAdm = epService.getEPAdministrator();
		
		/* Situation Manager Start */
		SituationManager sitManager = new SituationManager();
		
		if(SIT_MANAGER_T){
			sitManager.start(epAdm, new LoggedIn(), new SituationManagerListener<LoggedIn>(LoggedIn.class));
			sitManager.start(epAdm, new SuspiciousParallelLogin(), new SituationManagerListener<SuspiciousParallelLogin>(SuspiciousParallelLogin.class));
			sitManager.start(epAdm, new OngoingSuspiciousWithdrawal(), new SituationManagerListener<OngoingSuspiciousWithdrawal>(OngoingSuspiciousWithdrawal.class));
			sitManager.start(epAdm, new SuspiciousFarawayLogin(), new SituationManagerListener<SuspiciousFarawayLogin>(SuspiciousFarawayLogin.class));
			sitManager.start(epAdm, new AccountUnderObservation(), new SituationManagerListener<AccountUnderObservation>(AccountUnderObservation.class));
		}else{
			sitManager.start(epAdm, new LoggedIn(), new SituationManagerListenerNoThread<LoggedIn>(LoggedIn.class));
			sitManager.start(epAdm, new SuspiciousParallelLogin(), new SituationManagerListenerNoThread<SuspiciousParallelLogin>(SuspiciousParallelLogin.class));
			sitManager.start(epAdm, new OngoingSuspiciousWithdrawal(), new SituationManagerListenerNoThread<OngoingSuspiciousWithdrawal>(OngoingSuspiciousWithdrawal.class));
			sitManager.start(epAdm, new SuspiciousFarawayLogin(), new SituationManagerListenerNoThread<SuspiciousFarawayLogin>(SuspiciousFarawayLogin.class));
			sitManager.start(epAdm, new AccountUnderObservation(), new SituationManagerListenerNoThread<AccountUnderObservation>(AccountUnderObservation.class));
		}
				
		SituationDefinition loggedIn = sitManager.getSituationDefinition("LoggedIn");
		SituationDefinition suspiciousParallelLogin = sitManager.getSituationDefinition("SuspiciousParallelLogin");
		SituationDefinition ongoingSuspiciousWithdrawal = sitManager.getSituationDefinition("OngoingSuspiciousWithdrawal");
		SituationDefinition suspiciousFarawayLogin = sitManager.getSituationDefinition("SuspiciousFarawayLogin");
		SituationDefinition accountUnderObservation = sitManager.getSituationDefinition("AccountUnderObservation");
		
		StatementAwareUpdateListener listener = null;
		if(!SIT_LISTENER_T) listener = new SituationListenerNoThread();
		
		loggedIn.start(listener);
		suspiciousParallelLogin.start(listener);
		ongoingSuspiciousWithdrawal.start(listener);
		suspiciousFarawayLogin.start(listener);
		accountUnderObservation.start(listener);
		
		
		if(NUMBER_OF_CLIENTS > 0){
			for(int i = 0; i < NUMBER_OF_CLIENTS; i++){
				Initi init = new Initi();
				init.setVisible(true);
				init.setTitle("CLient Simulator #"+(i+1));
			}
				
			Monitoring m = new Monitoring();
			m.setVisible(true);
		}else{
			BankTester tester = new BankTester();
			tester.start();
		}
	}
}
