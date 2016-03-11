package situations.esper.definition.bank;

import situations.esper.control.Situation;

import com.espertech.esper.client.EventBean;

public class SuspiciousParallelLogin extends Situation {
	
	private LoggedIn[] loggedIn = new LoggedIn[2];
	
	public SuspiciousParallelLogin(){
		
		setSitName("SuspiciousParallelLogin");
		
		setEplA("select LoggedIn1, LoggedIn2, LoggedIn1.key as key1, LoggedIn2.key as key2 "
				+ " from pattern[ every ("
				+ "                      ("
				+ "                       every LoggedIn1 = LoggedIn(actived = true)->"
				+ "                      (every LoggedIn2 = LoggedIn(actived = true) and"
				+ "                       not LoggedIn(actived = false, id = LoggedIn1.id))"
				+ "                      )"
				+ ""
				+ "                     or"
				+ ""
				+ "                     ("
				+ "                      every LoggedIn2 = LoggedIn(actived = true)->"
				+ "                      (every LoggedIn1 = LoggedIn(actived = true) and"
				+ "                       not LoggedIn(actived = false, id = LoggedIn2.id))"
				+ "                     )"
				+ "                    )"
				+ "              ]"
				+ " where LoggedIn1.account.number = LoggedIn2.account.number");
		/*	
		setEplD("select SuspiciousParallelLogin, SuspiciousParallelLogin.loggedIn[0].key as key1, SuspiciousParallelLogin.loggedIn[1].key as key2 "
				+ "from"
				+ "     SuspiciousParallelLogin.std:unique(id) as SuspiciousParallelLogin,"
				+ "     LoggedIn.win:keepall() as LoggedIn "
				+ "where"
				+ "     SuspiciousParallelLogin.actived is true"
				+ "     and"
				+ "        ("
				+ "         (SuspiciousParallelLogin.loggedIn[0].id = LoggedIn.id and LoggedIn.actived is not true)"
				+ "         or"
				+ "         (SuspiciousParallelLogin.loggedIn[1].id = LoggedIn.id and LoggedIn.actived is not true)"
				+ "         or"
				+ "         not(SuspiciousParallelLogin.loggedIn[0].account.number = SuspiciousParallelLogin.loggedIn[1].account.number)"
				+ "        )");
		*/
		setEplD("select SuspiciousParallelLogin, SuspiciousParallelLogin.loggedIn[0].key as key1, SuspiciousParallelLogin.loggedIn[1].key as key2 "
				+ "from"
				+ "     SuspiciousParallelLogin.std:unique(id) as SuspiciousParallelLogin,"
				+ "     LoggedIn.std:lastevent() as LoggedIn "
				+ "where"
				+ "     SuspiciousParallelLogin.actived is true"
				+ "     and"
				+ "        ("
				+ "         (SuspiciousParallelLogin.loggedIn[0].id = LoggedIn.id and LoggedIn.actived is not true)"
				+ "         or"
				+ "         (SuspiciousParallelLogin.loggedIn[1].id = LoggedIn.id and LoggedIn.actived is not true)"
				+ "         or"
				+ "         not(SuspiciousParallelLogin.loggedIn[0].account.number = SuspiciousParallelLogin.loggedIn[1].account.number)"
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
		SuspiciousParallelLogin suspiciousParallelLogin = new SuspiciousParallelLogin();
        
		try{
			suspiciousParallelLogin.setLoggedIn(0,(LoggedIn)event.get("LoggedIn1"));
			suspiciousParallelLogin.setLoggedIn(1,(LoggedIn)event.get("LoggedIn2"));
		}catch(Exception e){
    		System.out.println("SuspiciousParallelLogin: " + e);
    	}
		
		return suspiciousParallelLogin;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		SuspiciousParallelLogin suspiciousParallelLogin = new SuspiciousParallelLogin();
        
		try{
			suspiciousParallelLogin.setLoggedIn(0,this.getLoggedIn(0));
			suspiciousParallelLogin.setLoggedIn(1,this.getLoggedIn(1));
		}catch(Exception e){
    		System.out.println("SuspiciousParallelLogin: " + e);
    	}
		
		return suspiciousParallelLogin;
	}
	
	@Override
	public void doActionAtActivation(EventBean event) {
		// TODO Auto-generated method stub
		super.doActionAtActivation(event);
		System.out.println(this.getLoggedIn(0).getId());
		System.out.println(this.getLoggedIn(1).getId());
	}
	

}
