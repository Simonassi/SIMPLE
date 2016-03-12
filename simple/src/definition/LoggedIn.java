package definition;

import control.Situation;
import model.Access;
import model.Account;
import model.Device;

import com.espertech.esper.client.EventBean;

public class LoggedIn extends Situation{
	private Device device;
	private Account account;
	private Access access;
	
	public LoggedIn(){
		
		setSitName("LoggedIn");
		
		setEplA("select Access as access, Access.isAcessing as device, Access.isAcessed as account, Access.key as key1 "
			            + "from Access(actived = true) as Access");
						
		setEplD("select Access.key as key1 "
				 + "from "
				 + "     LoggedIn.std:unique(id) as LoggedIn, "
				 + "     Access.std:lastevent() as Access "
				 + "where"
				 + "     LoggedIn.actived = true and "
				 + "     LoggedIn.access.key = Access.key "
				 + "     and "
				 + "        ("
				 + "         not(Access.actived = true) "
				 + "        )");
		
	}
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Access getAccess() {
		return access;
	}

	public void setAccess(Access access) {
		this.access = access;
	}
	
	@Override
	public Situation createNewSit(EventBean event) {
		LoggedIn loggedIn = new LoggedIn();
        
		try{
			loggedIn.setAccount((Account)event.get("account"));
			loggedIn.setDevice((Device)event.get("device"));
			loggedIn.setAccess((Access)event.get("access"));
		}catch(Exception e){
    		System.out.println("LoggedIn: " + e);
    	}
		
		return loggedIn;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		LoggedIn loggedIn = new LoggedIn();
        
		try{
			loggedIn.setAccount(this.getAccount());
			loggedIn.setDevice(this.getDevice());
			loggedIn.setAccess(this.getAccess());
		}catch(Exception e){
    		System.out.println("LoggedIn: " + e);
    	}
		
		return loggedIn;
	}

}
