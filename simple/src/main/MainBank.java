package situations.esper.main;
import org.apache.log4j.PropertyConfigurator;

import situations.esper.application.Initi;
import situations.esper.application.Monitoring;
import situations.esper.control.SituationDefinition;
import situations.esper.control.SituationManager;
import situations.esper.definition.bank.AccountUnderObservation;
import situations.esper.definition.bank.LoggedIn;
import situations.esper.definition.bank.OngoingSuspiciousWithdrawal;
import situations.esper.definition.bank.SuspiciousFarawayLogin;
import situations.esper.definition.bank.SuspiciousParallelLogin;
import situations.esper.listener.SituationListenerNoThread;
import situations.esper.listener.SituationManagerListener;
import situations.esper.listener.SituationManagerListenerNoThread;
import situations.esper.testers.BankTester7;

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
			//BankTester1 tester = new BankTester1();
			//BankTester2 tester = new BankTester2();
			//BankTester3 tester = new BankTester3();
			//BankTester4 tester = new BankTester4();
			//BankTester5 tester = new BankTester5();
			//BankTester6 tester = new BankTester6();
			BankTester7 tester = new BankTester7();
			tester.start();
		}
	}
}
