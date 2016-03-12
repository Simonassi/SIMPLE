package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import control.Situation;
import definition.SuspiciousFarawayLogin;
import definition.SuspiciousParallelLogin;

import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.StatementAwareUpdateListener;

public class MonitoringListener implements StatementAwareUpdateListener {
    public void update(EventBean[] newEvents, EventBean[] oldEvents, EPStatement stmt, EPServiceProvider epService) {
        
    	Situation sit = (Situation)newEvents[0].getUnderlying();
    	DefaultTableModel defaultModel = (DefaultTableModel) Monitoring.table.getModel();
    	
    	if(sit.isActived() == false){
    		defaultModel.removeRow(sit.getId());
    	}
    	
    	Object[] rowData = new Object[Monitoring.table.getModel().getColumnCount()];
    	rowData[0] = sit.getId();
    	rowData[1] = sit.getSitName();
    	rowData[2] = formatDate(sit.getStart_time());
    	rowData[3] = formatDate(sit.getEnd_time());
    	
    	if(sit instanceof SuspiciousFarawayLogin){
    		SuspiciousFarawayLogin sfl = (SuspiciousFarawayLogin)sit;
    		rowData[4] = sfl.getLoggedIn(0).getId() + ", " + sfl.getLoggedIn(1).getId();
    	}else if (sit instanceof SuspiciousParallelLogin){
    		SuspiciousParallelLogin spl = (SuspiciousParallelLogin)sit;
    		rowData[4] = spl.getLoggedIn(0).getId() + ", " + spl.getLoggedIn(1).getId();
    	}else{
    		rowData[4] = null;
    	}
		
		defaultModel.insertRow(sit.getId(),rowData);
    }
    
    public String formatDate(Date oldDate){
		if(oldDate == null) return "";
		String newDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(oldDate);
		return newDate;
	}
}