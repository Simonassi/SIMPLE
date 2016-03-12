package model;

import com.espertech.esper.client.EventBean;

import control.Situation;

public class OngoingWithdrawal extends Situation {
	private ATM doWithdrawal;
	private Account hasWithdrawal;
	private long value;
	private String key;
	
	//used only to test purposes
	public OngoingWithdrawal(ATM doWithdrawal, Account hasWithdrawal, long value, String key){
		this.doWithdrawal = doWithdrawal;
		this.hasWithdrawal = hasWithdrawal;
		this.value = value;
		this.setKey(key);
		this.setActived(true);
	}
	
	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public ATM getDoWithdrawal() {
		return doWithdrawal;
	}
	
	public void setDoWithdrawal(ATM doWithdrawal) {
		this.doWithdrawal = doWithdrawal;
	}
	
	public Account getHasWithdrawal() {
		return hasWithdrawal;
	}
	
	public void setHasWithdrawal(Account hasWithdrawal) {
		this.hasWithdrawal = hasWithdrawal;
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
