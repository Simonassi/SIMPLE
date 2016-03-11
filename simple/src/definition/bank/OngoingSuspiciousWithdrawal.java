package situations.esper.definition.bank;

import situations.esper.control.Situation;
import situations.esper.model.bank.Account;
import situations.esper.model.bank.OngoingWithdrawal;

import com.espertech.esper.client.EventBean;

public class OngoingSuspiciousWithdrawal extends Situation {
	
	private Account account;
	private OngoingWithdrawal ongoingWithdrawal;
	
	public OngoingSuspiciousWithdrawal(){
		
		setSitName("OngoingSuspiciousWithdrawal");
		
		setEplA("select OngoingWithdrawal as ongoingWithdrawal, OngoingWithdrawal.hasWithdrawal as account, OngoingWithdrawal.key as key1 "
				 + "from OngoingWithdrawal(actived = true) as OngoingWithdrawal "
			 	 + "where OngoingWithdrawal.value > 1000 ");
		
		/*
		setEplD("select OngoingWithdrawal.key as key1 "
				+ "from "
				+ "      OngoingSuspiciousWithdrawal.std:unique(id) as OngoingSuspiciousWithdrawal, "
				+ "      OngoingWithdrawal.std:unique(key) as OngoingWithdrawal "
				+ "     "
				+ "where "
				+ "      OngoingSuspiciousWithdrawal.actived = true and "
				+ "      OngoingSuspiciousWithdrawal.ongoingWithdrawal.key = OngoingWithdrawal.key "
				+ "      and "
				+ "         ("
				+ "          not (OngoingWithdrawal.actived = true) "
				+ "          or "
				+ "          not (OngoingWithdrawal.value > 1000) "
				+ "         )");
		*/
		
		setEplD("select OngoingWithdrawal.key as key1 "
				+ "from "
				+ "      OngoingSuspiciousWithdrawal.std:unique(id) as OngoingSuspiciousWithdrawal, "
				+ "      OngoingWithdrawal.std:lastevent() as OngoingWithdrawal "
				+ "     "
				+ "where "
				+ "      OngoingSuspiciousWithdrawal.actived = true and "
				+ "      OngoingSuspiciousWithdrawal.ongoingWithdrawal.key = OngoingWithdrawal.key "
				+ "      and "
				+ "         ("
				+ "          not (OngoingWithdrawal.actived = true) "
				+ "          or "
				+ "          not (OngoingWithdrawal.value > 1000) "
				+ "         )");
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public OngoingWithdrawal getOngoingWithdrawal() {
		return ongoingWithdrawal;
	}

	public void setOngoingWithdrawal(OngoingWithdrawal ongoingWithdrawal) {
		this.ongoingWithdrawal = ongoingWithdrawal;
	}

	@Override
	public Situation createNewSit(EventBean event) {
		OngoingSuspiciousWithdrawal ongoingSuspiciousWithdrawal = new OngoingSuspiciousWithdrawal();
        
		try{
			ongoingSuspiciousWithdrawal.setAccount((Account)event.get("account"));
			ongoingSuspiciousWithdrawal.setOngoingWithdrawal((OngoingWithdrawal)event.get("ongoingWithdrawal"));
		}catch(Exception e){
    		System.out.println("OngoingSuspiciousWithdrawal: " + e);
    	}
		
		return ongoingSuspiciousWithdrawal;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		OngoingSuspiciousWithdrawal ongoingSuspiciousWithdrawal = new OngoingSuspiciousWithdrawal();
        
		try{
			ongoingSuspiciousWithdrawal.setAccount(this.getAccount());
			ongoingSuspiciousWithdrawal.setOngoingWithdrawal(this.getOngoingWithdrawal());
		}catch(Exception e){
    		System.out.println("OngoingSuspiciousWithdrawal: " + e);
    	}
		
		return ongoingSuspiciousWithdrawal;
	}

}
