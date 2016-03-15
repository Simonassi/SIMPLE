package definition;

import control.Situation;
import model.Account;

import com.espertech.esper.client.EventBean;

public class AccountUnderObservation extends Situation {
	private OngoingSuspiciousWithdrawal ongoingSuspiciousWithdrawal;
	
	public AccountUnderObservation(){
		
		setSitName("AccountUnderObservation");
		
		setEplA("select OngoingSuspiciousWithdrawal, OngoingSuspiciousWithdrawal.account as account "
				 + "from OngoingSuspiciousWithdrawal as OngoingSuspiciousWithdrawal "
				 + "where OngoingSuspiciousWithdrawal.actived = false");
			
		setEplD("select AccountUnderObservation "
				 + "from pattern[ AccountUnderObservation = AccountUnderObservation"
				 + "			 -> every (timer:interval(5 sec) and not OngoingSuspiciousWithdrawal(actived = false)) ] ");
	}
	
	private Account account;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public OngoingSuspiciousWithdrawal getOngoingSuspiciousWithdrawal() {
		return ongoingSuspiciousWithdrawal;
	}

	public void setOngoingSuspiciousWithdrawal( OngoingSuspiciousWithdrawal ongoingSuspiciousWithdrawal) {
		this.ongoingSuspiciousWithdrawal = ongoingSuspiciousWithdrawal;
	}
	
	@Override
	public Situation createNewSit(EventBean event) {
		AccountUnderObservation accountUnderObservation = new AccountUnderObservation();
        
		try{
			accountUnderObservation.setOngoingSuspiciousWithdrawal((OngoingSuspiciousWithdrawal)event.get("OngoingSuspiciousWithdrawal"));
			accountUnderObservation.setAccount((Account)event.get("account"));
		}catch(Exception e){
    		System.out.println("AccountUnderObservation: " + e);
    	}
		
		return accountUnderObservation;
	}

	@Override
	public Object doActionAtCreateDeactivationEvent() {
		AccountUnderObservation accountUnderObservation = new AccountUnderObservation();
        
		try{
			accountUnderObservation.setOngoingSuspiciousWithdrawal(this.getOngoingSuspiciousWithdrawal());
			accountUnderObservation.setAccount(this.getAccount());
		}catch(Exception e){
    		System.out.println("AccountUnderObservation: " + e);
    	}
		
		return accountUnderObservation;
	}

}
