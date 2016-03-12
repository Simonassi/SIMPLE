package application;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.ATM;
import model.Access;
import model.Account;
import model.Computer;
import model.Device;
import model.Location;
import model.MobileDevice;
import model.OngoingWithdrawal;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;

import java.awt.Component;

import javax.swing.Box;

@SuppressWarnings("serial")
public class Initi extends JFrame {

	private JPanel contentPane;
	private JTextField tfAccount;
	@SuppressWarnings("rawtypes")
	private JComboBox cbDevice;
	@SuppressWarnings("rawtypes")
	private JComboBox cbLocation;
	private JLabel lbValue;
	private JTextField tfValue;
	private JPanel panel;
	private JLabel lbWithdrawal;
	private JButton btnDoWithdrawal;
	private JButton btnLogout;
	private Access access;
	private OngoingWithdrawal lastOngoingWithdrawal;
	@SuppressWarnings("unused")
	private final String WITHDRAWAL_LABEL = "Withdrawal";
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private Component verticalStrut_4;
	private JButton btnCancelWithdrawal;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Initi frame = new Initi();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Initi() {
		
		/* Esper Configuration */
		Configuration config = new Configuration();
		config.addEventTypeAutoName("situations.esper.model.bank");
		config.addEventTypeAutoName("situations.esper.definition.bank");
		final EPServiceProvider epService = EPServiceProviderManager.getDefaultProvider(config);
		
		final Location location1 = new Location(32.9697, -96.80322);
		final Location location2 = new Location(29.46786, -98.53506);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 267, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel_1 = new JLabel("Account Loggin");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridheight = 2;
		gbc_lblNewLabel_1.gridwidth = 5;
		gbc_lblNewLabel_1.anchor = GridBagConstraints.ABOVE_BASELINE;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 2;
		contentPane.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblNewLabel = new JLabel("Account");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 3;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		tfAccount = new JTextField();
		GridBagConstraints gbc_tfAccount = new GridBagConstraints();
		gbc_tfAccount.gridwidth = 4;
		gbc_tfAccount.insets = new Insets(0, 0, 5, 5);
		gbc_tfAccount.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAccount.gridx = 1;
		gbc_tfAccount.gridy = 3;
		contentPane.add(tfAccount, gbc_tfAccount);
		tfAccount.setColumns(10);
		
		JLabel lblDevice = new JLabel("Device");
		GridBagConstraints gbc_lblDevice = new GridBagConstraints();
		gbc_lblDevice.anchor = GridBagConstraints.WEST;
		gbc_lblDevice.insets = new Insets(0, 0, 5, 5);
		gbc_lblDevice.gridx = 0;
		gbc_lblDevice.gridy = 4;
		contentPane.add(lblDevice, gbc_lblDevice);
		
		cbDevice = new JComboBox();
		cbDevice.setModel(new DefaultComboBoxModel(new String[] {"ATM", "Computer", "Mobile"}));
		GridBagConstraints gbc_cbDevice = new GridBagConstraints();
		gbc_cbDevice.gridwidth = 4;
		gbc_cbDevice.insets = new Insets(0, 0, 5, 5);
		gbc_cbDevice.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbDevice.gridx = 1;
		gbc_cbDevice.gridy = 4;
		contentPane.add(cbDevice, gbc_cbDevice);
		
		JLabel lblLocation = new JLabel("Location");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.anchor = GridBagConstraints.WEST;
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 0;
		gbc_lblLocation.gridy = 5;
		contentPane.add(lblLocation, gbc_lblLocation);
		
		cbLocation = new JComboBox();
		cbLocation.setModel(new DefaultComboBoxModel(new String[] {"City1", "City2"}));
		GridBagConstraints gbc_cbLocation = new GridBagConstraints();
		gbc_cbLocation.gridwidth = 4;
		gbc_cbLocation.insets = new Insets(0, 0, 5, 5);
		gbc_cbLocation.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbLocation.gridx = 1;
		gbc_cbLocation.gridy = 5;
		contentPane.add(cbLocation, gbc_cbLocation);
		
		final JButton btnAccess = new JButton("Access");
		btnAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAccess.addActionListener(new ActionListener() {
		     public void actionPerformed(ActionEvent ae) {
				String deviceType = cbDevice.getSelectedItem().toString();
				Account account = new Account(tfAccount.getText(), null);
				access = new Access(null, account, account.getNumber() + "--" + deviceType);
								
				if(deviceType.equals("ATM")){
					access.setIsAcessing(new ATM());
					lbValue.setVisible(true);
					tfValue.setVisible(true);
					btnDoWithdrawal.setVisible(true);
				}else {
					lbValue.setVisible(false);
					tfValue.setVisible(false);
					btnDoWithdrawal.setVisible(false);
					if(deviceType.equals("Computer")) access.setIsAcessing(new Computer());
					else if(deviceType.equals("Mobile")) access.setIsAcessing(new MobileDevice());
				}
				
				String cityType = cbLocation.getSelectedItem().toString();
				if(cityType.equals("City1")) access.getIsAcessing().setLocation(location1);
				else access.getIsAcessing().setLocation(location2);
				
				epService.getEPRuntime().sendEvent(access);
				
				lbWithdrawal.setText("Account #" + access.getIsAcessed().getNumber());
				panel.setVisible(true);
				
				btnAccess.setEnabled(false);
			}
		});
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
		gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_1.gridx = 1;
		gbc_verticalStrut_1.gridy = 6;
		contentPane.add(verticalStrut_1, gbc_verticalStrut_1);
		
		GridBagConstraints gbc_btnAccess = new GridBagConstraints();
		gbc_btnAccess.insets = new Insets(0, 0, 5, 0);
		gbc_btnAccess.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAccess.gridwidth = 5;
		gbc_btnAccess.gridx = 0;
		gbc_btnAccess.gridy = 7;
		contentPane.add(btnAccess, gbc_btnAccess);
		
		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 6;
		gbc_panel.gridwidth = 5;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 8;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setVisible(false);
		panel.setLayout(gbl_panel);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_3 = new GridBagConstraints();
		gbc_verticalStrut_3.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_3.gridx = 1;
		gbc_verticalStrut_3.gridy = 0;
		panel.add(verticalStrut_3, gbc_verticalStrut_3);
		
		lbWithdrawal = new JLabel("WITHDRAWAL_LABEL");
		lbWithdrawal.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_lbWithdrawal = new GridBagConstraints();
		gbc_lbWithdrawal.gridheight = 2;
		gbc_lbWithdrawal.gridwidth = 6;
		gbc_lbWithdrawal.insets = new Insets(0, 0, 5, 5);
		gbc_lbWithdrawal.gridx = 0;
		gbc_lbWithdrawal.gridy = 1;
		panel.add(lbWithdrawal, gbc_lbWithdrawal);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
		gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_2.gridx = 1;
		gbc_verticalStrut_2.gridy = 3;
		panel.add(verticalStrut_2, gbc_verticalStrut_2);
		
		lbValue = new JLabel("Value");
		GridBagConstraints gbc_lbValue = new GridBagConstraints();
		gbc_lbValue.insets = new Insets(0, 0, 5, 5);
		gbc_lbValue.anchor = GridBagConstraints.EAST;
		gbc_lbValue.gridx = 0;
		gbc_lbValue.gridy = 4;
		panel.add(lbValue, gbc_lbValue);
		
		tfValue = new JTextField();
		GridBagConstraints gbc_tfValue = new GridBagConstraints();
		gbc_tfValue.insets = new Insets(0, 0, 5, 5);
		gbc_tfValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfValue.gridx = 1;
		gbc_tfValue.gridy = 4;
		panel.add(tfValue, gbc_tfValue);
		tfValue.setColumns(10);
		
		btnDoWithdrawal = new JButton("Do Withdrawal");
		btnDoWithdrawal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Device device = access.getIsAcessing();
				Account account = access.getIsAcessed();
				long value = Integer.parseInt(tfValue.getText());
				
				OngoingWithdrawal onGoingWithdrawal1 = new OngoingWithdrawal((ATM)device, account, value, String.valueOf(System.currentTimeMillis()));
				lastOngoingWithdrawal = onGoingWithdrawal1;
				epService.getEPRuntime().sendEvent(onGoingWithdrawal1);
			}
		});
		GridBagConstraints gbc_btnDoWithdrawal = new GridBagConstraints();
		gbc_btnDoWithdrawal.insets = new Insets(0, 0, 5, 5);
		gbc_btnDoWithdrawal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDoWithdrawal.gridwidth = 4;
		gbc_btnDoWithdrawal.gridx = 2;
		gbc_btnDoWithdrawal.gridy = 4;
		panel.add(btnDoWithdrawal, gbc_btnDoWithdrawal);
		
		btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String deviceType = cbDevice.getSelectedItem().toString();
				Account account = new Account(tfAccount.getText(), null);
				access = new Access(null, account, account.getNumber() + "--" + deviceType);
								
				if(deviceType.equals("ATM")) access.setIsAcessing(new ATM());
				else if(deviceType.equals("Computer")) access.setIsAcessing(new Computer());
				else if(deviceType.equals("Mobile")) access.setIsAcessing(new MobileDevice());
				
				String cityType = cbLocation.getSelectedItem().toString();
				if(cityType.equals("City1")) access.getIsAcessing().setLocation(location1);
				else access.getIsAcessing().setLocation(location2);
				
				access.setActived(false);
				epService.getEPRuntime().sendEvent(access);
				
				panel.setVisible(false);
				
				btnAccess.setEnabled(true);
			}
		});
		
		btnCancelWithdrawal = new JButton("Cancel Last Withtrawal");
		btnCancelWithdrawal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lastOngoingWithdrawal.setActived(false);
				epService.getEPRuntime().sendEvent(lastOngoingWithdrawal);
			}
		});
		GridBagConstraints gbc_btnCancelWithdrawal = new GridBagConstraints();
		gbc_btnCancelWithdrawal.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelWithdrawal.gridwidth = 6;
		gbc_btnCancelWithdrawal.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelWithdrawal.gridx = 0;
		gbc_btnCancelWithdrawal.gridy = 5;
		panel.add(btnCancelWithdrawal, gbc_btnCancelWithdrawal);
		
		verticalStrut_4 = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut_4 = new GridBagConstraints();
		gbc_verticalStrut_4.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut_4.gridx = 3;
		gbc_verticalStrut_4.gridy = 6;
		panel.add(verticalStrut_4, gbc_verticalStrut_4);
		GridBagConstraints gbc_btnLogout = new GridBagConstraints();
		gbc_btnLogout.gridwidth = 6;
		gbc_btnLogout.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLogout.gridx = 0;
		gbc_btnLogout.gridy = 7;
		panel.add(btnLogout, gbc_btnLogout);
	}

}
