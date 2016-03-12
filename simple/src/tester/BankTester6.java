package situations.esper.testers;
import java.util.concurrent.TimeUnit;

import situations.esper.model.bank.ATM;
import situations.esper.model.bank.Access;
import situations.esper.model.bank.Account;
import situations.esper.model.bank.Device;
import situations.esper.model.bank.Location;
import situations.esper.model.bank.OngoingWithdrawal;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;


public class BankTester6 {
	public void start() throws InterruptedException{
		
		/* Esper Configuration */
		Configuration config = new Configuration();
		config.addEventTypeAutoName("situations.esper.model.bank");
		config.addEventTypeAutoName("situations.esper.definition.bank");
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		
		/* Variables */
		// Mais de 100 km
		Location location1 = new Location(32.9697, -96.80322);
		Location location2 = new Location(29.46786, -98.53506);
		
		/*
		// Menos de 100 km
		Location location1 = new Location(-20.2976178, -40.2957768);
		Location location2 = new Location(-20.1215224, -40.3077898);
		*/
		
		Device device1 = new Device();
		Account account1 = new Account();
		account1.setNumber("1");
		Access access1 = new Access(device1, account1, "account1");
		
		Device device2 = new Device();
		Account account2 = new Account();
		account2.setNumber("1");
		Access access2 = new Access(device2, account2, "account2");
		
		Device device3 = new Device();
		Account account3 = new Account();
		account3.setNumber("1");
		Access access3 = new Access(device3, account3, "account3");
		
		Device device4 = new Device();
		Account account4 = new Account();
		account4.setNumber("1");
		Access access4 = new Access(device4, account4, "account4");
		
		ATM atm1 = new ATM();
		OngoingWithdrawal onGoingWithdrawal1= new OngoingWithdrawal(atm1, account1, 500, "OngoingWithdrawal1");
		
		ATM atm2 = new ATM();
		OngoingWithdrawal onGoingWithdrawal2= new OngoingWithdrawal(atm2, account2, 2000, "OngoingWithdrawal2");
		
		device1.setLocation(location1);
		device2.setLocation(location1);
		
		System.out.println(Location.distance(location1, location2) + "\n");
		
		/* Events */
		System.out.println("Event 1 - Access1");
		epService.getEPRuntime().sendEvent(access1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 2 - Access1 (actived = false)");
		access1 = new Access(device1, account1, "account1");
		access1.setActived(false);
		epService.getEPRuntime().sendEvent(access1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 3 - Access2");
		epService.getEPRuntime().sendEvent(access2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 4 - Access2");
		epService.getEPRuntime().sendEvent(access2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 5 - Access1");
		access1 = new Access(device1, account1, "account1"); // new
		epService.getEPRuntime().sendEvent(access1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 6 - Access1");
		epService.getEPRuntime().sendEvent(access1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 7 - Access2");
		epService.getEPRuntime().sendEvent(access2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 8 - Access3");
		epService.getEPRuntime().sendEvent(access3);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 9 - Access2 (actived = false)");
		access2 = new Access(device2, account2, "account2");
		access2.setActived(false);
		epService.getEPRuntime().sendEvent(access2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 10 - Access2");
		access2 = new Access(device2, account2, "account2");
		epService.getEPRuntime().sendEvent(access2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 11 - OnGoingSuspiciousWithdrawal1 (500)");
		epService.getEPRuntime().sendEvent(onGoingWithdrawal1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 12 - OnGoingSuspiciousWithdrawal2 (2000)");
		epService.getEPRuntime().sendEvent(onGoingWithdrawal2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 13 - OnGoingSuspiciousWithdrawal1 (1500)");
		onGoingWithdrawal1.setValue(1500);
		epService.getEPRuntime().sendEvent(onGoingWithdrawal1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 14 - OnGoingSuspiciousWithdrawal2 (actived = false)");
		onGoingWithdrawal2.setActived(false);
		epService.getEPRuntime().sendEvent(onGoingWithdrawal2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 15 - Access4 (actived = false)");
		access4 = new Access(device4, account4, "account4");
		access4.setActived(false);
		epService.getEPRuntime().sendEvent(access4);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);

		System.out.println("Event 16 - Access4 (actived = true, location2)");
		access4 = new Access(device4, account4, "account4");
		device4.setLocation(location2);
		access4.setActived(false);
		epService.getEPRuntime().sendEvent(access4);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 17 - Access4");
		access4 = new Access(device4, account4, "account4");
		epService.getEPRuntime().sendEvent(access4);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 18 - Access2 (actived = false)");
		access2 = new Access(device2, account2, "account2");
		access2.setActived(false);
		epService.getEPRuntime().sendEvent(access2);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 19 - OnGoingSuspiciousWithdrawal1 (actived = false)");
		onGoingWithdrawal1.setActived(false);
		epService.getEPRuntime().sendEvent(onGoingWithdrawal1);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
	}
}
