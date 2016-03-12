package model;

import com.espertech.esper.client.EventBean;

import situations.esper.control.Situation;

public class Access extends Situation{
	
	private Device isAcessing;
	private Account isAcessed;
	private String key;
	
	// used only to tests purposes
	public Access(Device isAcessing, Account isAcessed, String key){
		this.isAcessed = isAcessed;
		this.isAcessing = isAcessing;
		this.key = key;
		this.setActived(true);
	}
	
	public Device getIsAcessing() {
		return isAcessing;
	}
	
	public void setIsAcessing(Device isAcessing) {
		this.isAcessing = isAcessing;
	}
	
	public Account getIsAcessed() {
		return isAcessed;
	}
	
	public void setIsAcessed(Account isAcessed) {
		this.isAcessed = isAcessed;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
