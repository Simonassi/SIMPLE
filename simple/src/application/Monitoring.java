package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

@SuppressWarnings("serial")
public class Monitoring extends JFrame {

	private JPanel contentPane;
	@SuppressWarnings("rawtypes")
	public static DefaultListModel listModel;
	public static JTable table;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Monitoring frame = new Monitoring();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes" })
	public Monitoring() {
		setTitle("Monitoring Situations");
		
		/* Esper Configuration */
		Configuration config = new Configuration();
		config.addEventTypeAutoName("situations.esper.model.bank");
		config.addEventTypeAutoName("situations.esper.definition.bank");
		final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		
		epService.getEPAdministrator().createEPL("select * from LoggedIn").addListener(new MonitoringListener());
		epService.getEPAdministrator().createEPL("select * from AccountUnderObservation").addListener(new MonitoringListener());
		epService.getEPAdministrator().createEPL("select * from OngoingSuspiciousWithdrawal").addListener(new MonitoringListener());
		epService.getEPAdministrator().createEPL("select * from SuspiciousFarawayLogin").addListener(new MonitoringListener());
		epService.getEPAdministrator().createEPL("select * from SuspiciousParallelLogin").addListener(new MonitoringListener());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
    	listModel = new DefaultListModel();
    	
		table = new JTable(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Situation Name", "Start Time", "End Time", "Components"
			}
		));
		table.setShowVerticalLines(false);
		table.setRowSelectionAllowed(false);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		        
		        if ( table.getModel().getValueAt(row, 3).equals("")) {
		            c.setBackground( new Color(73, 195, 255));
		        }
		        else {
		            c.setBackground(new Color(255, 112, 112));
		        }
				
		        return c;
		    }
		});
		add(new JScrollPane(table));
    	//contentPane.add(table, BorderLayout.NORTH);
	}

}
