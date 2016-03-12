package control;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.espertech.esper.client.EventBean;

public abstract class Situation {
	private boolean actived;
	private Date start_time;
	private Date end_time;
	
	private long start_time_m;
	private long end_time_m;
	
	private String sitName;
	private int id;
	
	private String key;
	
	private String eplA, eplD;
	
	public boolean isActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
		this.start_time_m = start_time.getTime();
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
		this.end_time_m = end_time.getTime();
	}
	public void setSitName(String sitName){
		this.sitName = sitName;
	}
	public String getSitName(){
		return sitName;
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	public long getStart_time_m() {
		return start_time_m;
	}
	
	public void setStart_time_m(long start_time_m) {
		this.start_time_m = start_time_m;
	}
	
	public long getEnd_time_m() {
		return end_time_m;
	}
	
	public void setEnd_time_m(long end_time_m) {
		this.end_time_m = end_time_m;
	}
	
	public void doActionAtDeactivation(EventBean event){
		System.out.println("Situation " + this.getSitName() + " deactivated. ID = " + this.getId() + " Start Time = " + this.getStart_time() + " -- " + "End Time = " + this.getEnd_time());
	}
	
	public void doActionAtActivation(EventBean event){
		System.out.println("Situation " + this.getSitName() + " activated. ID = " + this.getId() + " Start Time = " + this.getStart_time() + " -- " + "End Time = " + this.getEnd_time());
	}
	
	public abstract Object doActionAtCreateDeactivationEvent();
	public abstract Situation createNewSit(EventBean event);
	
	public Object doActionAtCreateActivationEvent(EventBean[] newEvents){
		// DO NOTHING
		// overload if you wish
		return null;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public String toString() {
		return "Situation " + this.getSitName() + " / ID = " + this.getId() + " Start Time = " + formatDate(this.getStart_time()) + " -- " + "End Time = " + formatDate(this.getEnd_time());
	}
	
	public String formatDate(Date oldDate){
		if(oldDate == null) return "";
		String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(oldDate);
		return newDate;
	}
	public String getEplA() {
		return eplA;
	}
	public void setEplA(String eplA) {
		this.eplA = eplA;
	}
	public String getEplD() {
		return eplD;
	}
	public void setEplD(String eplD) {
		this.eplD = eplD;
	}
}
