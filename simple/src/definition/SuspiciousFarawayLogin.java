package definition;

import control.Situation;

import com.espertech.esper.client.EventBean;

public class SuspiciousFarawayLogin extends Situation {
	
	private LoggedIn[] loggedIn = new LoggedIn[2];
	
	public SuspiciousFarawayLogin(){
		
		setSitName("SuspiciousFarawayLogin");
		
		setEplA("select LoggedIn1, LoggedIn2, LoggedIn1.key as key1, LoggedIn2.key as key2 "
				+ " from pattern[ every ("
				+ "                      ("
				+ "                       every LoggedIn1 = LoggedIn(actived = true)->"
				+ "                      (every LoggedIn2 = LoggedIn(actived = true) and"
				+ "                       not LoggedIn(actived = false, id = LoggedIn1.id)) where timer:within(10 sec)"
				+ "                      )"
				+ ""
				+ "                     or"
				+ ""
				+ "                     ("
				+ "                      every LoggedIn2 = LoggedIn(actived = true)->"
				+ "                      (every LoggedIn1 = LoggedIn(actived = true) and"
				+ "                       not LoggedIn(actived = false, id = LoggedIn2.id)) where timer:within(10 sec)"
				+ "                     )"
				+ "                    )"
				+ "              ]"
				+ "where locationDistance(LoggedIn1.device.location, LoggedIn2.device.location) > 100");
		
		setEplD("select SuspiciousFarawayLogin, SuspiciousFarawayLogin.loggedIn[0].key as key1, SuspiciousFarawayLogin.loggedIn[1].key as key2 "
				+ "from"
				+ "     SuspiciousFarawayLogin.std:unique(id) as SuspiciousFarawayLogin,"
				+ "     LoggedIn.std:lastevent() as LoggedIn "
				+ "where"
				+ "     SuspiciousFarawayLogin.actived is true"
				+ "     and"
				+ "        ("
				+ "         (SuspiciousFarawayLogin.loggedIn[0].id = LoggedIn.id and LoggedIn.actived is not true)"
				+ "         or"
				+ "         (SuspiciousFarawayLogin.loggedIn[1].id = LoggedIn.id and LoggedIn.actived is not true)"
				+ "        )");
	}
	
	public LoggedIn getLoggedIn(int index) {
		return loggedIn[index];
	}

	public void setLoggedIn(int index, LoggedIn loggedIn) {
		this.loggedIn[index] = loggedIn;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		SuspiciousFarawayLogin suspiciousFarawayLogin = new SuspiciousFarawayLogin();
        
		try{
			suspiciousFarawayLogin.setLoggedIn(0,(LoggedIn)event.get("LoggedIn1"));
			suspiciousFarawayLogin.setLoggedIn(1,(LoggedIn)event.get("LoggedIn2"));
		}catch(Exception e){
    		System.out.println("SuspiciousFarawayLogin: " + e);
    	}
		
		return suspiciousFarawayLogin;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		SuspiciousFarawayLogin suspiciousFarawayLogin = new SuspiciousFarawayLogin();
        
		try{
			suspiciousFarawayLogin.setLoggedIn(0,this.getLoggedIn(0));
			suspiciousFarawayLogin.setLoggedIn(1,this.getLoggedIn(1));
		}catch(Exception e){
    		System.out.println("SuspiciousFarawayLogin: " + e);
    	}
		
		return suspiciousFarawayLogin;
	}

}
