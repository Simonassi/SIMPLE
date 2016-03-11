package situations.esper.testers;
import java.util.concurrent.TimeUnit;

import situations.esper.model.influenza.City;
import situations.esper.model.influenza.Patient;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;


public class InfluenzaTester2 {
	public void start() throws InterruptedException{
		
		/* Esper Configuration */
		Configuration config = new Configuration();
		config.addEventTypeAutoName("situations.esper.model");
		config.addEventTypeAutoName("situations.esper.definition");
		EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		
		/* 
		 * Arrays
		 */
		String[] coughAndInfection = {"cough", "respiratory infection"};
		String[] justCough = {"nothing", "cough"};
		String[] justInfection = {"respiratory infection", "nothing"};
		String[] noSymptoms = {"nothing", "nothing"};
		
		/*
		 * Cities
		 */
		
		// Mais de 100 km
		City vitoria = new City("Vitoria", 32.9697, -96.80322);
		City serra = new City("Serra", 29.46786, -98.53506);
		
		/*
		// Menos de 100 km
		City vitoria = new City("Vitoria", -20.2976178, -40.2957768);
		City serra = new City("Serra", -20.1215224, -40.3077898);
		*/
		
		City rafaelsCity = vitoria;
		City gabrielsCity = vitoria;
		City joaosCity = serra;
		City mariosCity = serra;
		
		/*
		 * Variables
		 */
		Patient patient;
		
		System.out.println(City.distance(vitoria, serra) + "\n");
		
		System.out.println("Event 1 - Rafael / temp 36 / RH false / Infection");
		patient = new Patient("Rafael", rafaelsCity, 36, false, justInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 2 - Rafael / temp 36 / RH false / Nothing");
		patient = new Patient("Rafael", rafaelsCity, 36, false, noSymptoms);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 3 - Gabriel / temp 36 / RH false / Cough");
		patient = new Patient("Gabriel", gabrielsCity, 36, false, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 4 - Gabriel / temp 36 / RH false / Nothing");
		patient = new Patient("Gabriel", gabrielsCity, 36, false, noSymptoms);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 5 - Rafael / temp 36 / RH false / Cough");
		patient = new Patient("Rafael", rafaelsCity, 36, false, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 6 - Gabriel / temp 36 / RH false / Infection");
		patient = new Patient("Gabriel", gabrielsCity, 36, false, justInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 7 - Rafael / temp 36 / RH false / Both");
		patient = new Patient("Rafael", rafaelsCity, 36, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 8 - Gabriel / temp 36 / RH false / Both");
		patient = new Patient("Gabriel", gabrielsCity, 36, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 9 - Rafael / temp 38 / RH false / Both");
		patient = new Patient("Rafael", rafaelsCity, 38, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		/*
		System.out.println("Event 9 - Rafael / temp 38 / RH false / Cough");
		patient = new Patient("Rafael", rafaelsCity, 38, false, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		*/
		System.out.println("Event 10 - Gabriel / temp 38 / RH false / Both");
		patient = new Patient("Gabriel", gabrielsCity, 38, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 11 - Rafael / temp 38 / RH false / Both");
		patient = new Patient("Rafael", rafaelsCity, 38, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 12 - Gabriel / temp 38 / RH false / Both");
		patient = new Patient("Gabriel", gabrielsCity, 38, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 13 - Rafael / temp 38 / RH true / Both");
		patient = new Patient("Rafael", rafaelsCity, 38, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 14 - Gabriel / temp 38 / RH true / Both");
		patient = new Patient("Gabriel", gabrielsCity, 38, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 15 - Rafael / temp 35 / RH false / Both");
		patient = new Patient("Rafael", rafaelsCity, 35, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 16 - Gabriel / temp 38 / RH false / Cough");
		patient = new Patient("Gabriel", gabrielsCity, 38, false, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 17 - Rafael / temp 39 / RH false / Both");
		patient = new Patient("Rafael", rafaelsCity, 39, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 18 - Gabriel / temp 38 / RH false / Both");
		patient = new Patient("Gabriel", gabrielsCity, 38, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 19 - Rafael / temp 36 / RH false / Nothing");
		patient = new Patient("Rafael", rafaelsCity, 36, false, noSymptoms);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Event 20 - Gabriel / temp 36 / RH false / Nothing");
		patient = new Patient("Gabriel", gabrielsCity, 36, false, noSymptoms);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		/*
		System.out.println("Event 21.0 - Rafael / temp 36 / RH true / Cough");
		patient = new Patient("Rafael", rafaelsCity, 36, true, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		*/
		System.out.println("Event 21 - Rafael / temp 39 / RH true / Cough");
		patient = new Patient("Rafael", rafaelsCity, 39, true, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 22 - Gabriel / temp 38 / RH false / Infection");
		patient = new Patient("Gabriel", gabrielsCity, 38, false, justInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 23 - Rafael / temp 39 / RH true / Both");
		patient = new Patient("Rafael", rafaelsCity, 39, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 24 - Gabriel / temp 38 / RH true / Both");
		patient = new Patient("Gabriel", gabrielsCity, 38, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 25 - Rafael / temp 39 / RH true / Both");
		patient = new Patient("Rafael", rafaelsCity, 39, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 26 - Gabriel / temp 38 / RH true / Both");
		patient = new Patient("Gabriel", gabrielsCity, 38, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 27 - Joao / temp 39 / RH false / Nothing");
		patient = new Patient("Joao", joaosCity, 39, false, noSymptoms);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 28 - Mario / temp 38 / RH false / Nothing");
		patient = new Patient("Mario", mariosCity, 38, false, noSymptoms);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 29 - Joao / temp 39 / RH false / Cough");
		patient = new Patient("Joao", joaosCity, 39, false, justCough);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 30 - Mario / temp 38 / RH false / Infection");
		patient = new Patient("Mario", mariosCity, 38, false, justInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 31 - Joao / temp 39 / RH false / Both");
		patient = new Patient("Joao", joaosCity, 39, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 32 - Mario / temp 38 / RH false / Both");
		patient = new Patient("Mario", mariosCity, 38, false, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 33 - Joao / temp 39 / RH true / Both");
		patient = new Patient("Joao", joaosCity, 39, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 34 - Mario / temp 38 / RH true / Both");
		patient = new Patient("Mario", mariosCity, 38, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 35 - Mario / temp 38 / RH true / Both");
		patient = new Patient("Mario", mariosCity, 38, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
		
		System.out.println("Event 36 - Mario / temp 36 / RH true / Both");
		patient = new Patient("Mario", mariosCity, 36, true, coughAndInfection);
		epService.getEPRuntime().sendEvent(patient);
		System.out.println();
	}
}
