import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;


public class Main {

	private JFrame frmMobileBroadbandMeasurement;
	private JTable table;
	private static Vector<String> columnNames = new Vector<String>();
	private static Vector<Vector<Integer>> data = new Vector<Vector<Integer>>();
	
	int lenght =9;
	String startTime="2012-01-01", endTime="2013-12-31";
	float Delta1=0.4f, Delta2=0.4f;
	
	
	public static void populateTable(){
		
		columnNames.add("Sessions");
		columnNames.add("Valid Sessions");
		columnNames.add("Samples");
		columnNames.add("Valleys");
		
		for (int i = 0; i < 30; i++) {
			Vector<Integer> line = new Vector<Integer>();
			for (int j = 0; j < 4; j++) {
				line.add((int) Math.round(Math.random()));
			}
			data.add(line);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmMobileBroadbandMeasurement.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		populateTable();
		
		frmMobileBroadbandMeasurement = new JFrame();
		frmMobileBroadbandMeasurement.setTitle("Mobile Broadband Measurement Compiler");
		frmMobileBroadbandMeasurement.setBounds(100, 100, 817, 438);
		frmMobileBroadbandMeasurement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMobileBroadbandMeasurement.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSouth = new JPanel();
		frmMobileBroadbandMeasurement.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
		JPanel pnlReports = new JPanel();
		FlowLayout fl_pnlReports = (FlowLayout) pnlReports.getLayout();
		fl_pnlReports.setAlignment(FlowLayout.LEFT);
		pnlSouth.add(pnlReports);
		
		JButton btnNewButton = new JButton("RSCP, EC/I0, CQI X FTP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Core.putValleysOnPentaho_needsAbetterName(startTime, endTime, lenght, Delta1, Delta2);
			}
		});
		pnlReports.add(btnNewButton);
		
		JButton btnReport = new JButton("Summary");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Core.summary(startTime, endTime, lenght, Delta1, Delta2);
			}
		});
		pnlReports.add(btnReport);
		
		JButton btnReport_1 = new JButton("Report 3");
		btnReport_1.setEnabled(false);
		pnlReports.add(btnReport_1);
		
		JPanel pnlCommand = new JPanel();
		FlowLayout fl_pnlCommand = (FlowLayout) pnlCommand.getLayout();
		fl_pnlCommand.setAlignment(FlowLayout.RIGHT);
		pnlSouth.add(pnlCommand);
		
		JButton button = new JButton("Config");
		button.setEnabled(false);
		pnlCommand.add(button);
		
		JButton button_1 = new JButton("Quit");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pnlCommand.add(button_1);
		
		JPanel mainPanel = new JPanel();
		frmMobileBroadbandMeasurement.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JLabel lblVivo = new JLabel("Vivo");
		lblVivo.setEnabled(false);
		lblVivo.setBounds(26, 69, 61, 16);
		mainPanel.add(lblVivo);
		
		JLabel lblTim = new JLabel("Tim");
		lblTim.setEnabled(false);
		lblTim.setBounds(26, 119, 61, 16);
		mainPanel.add(lblTim);
		
		JLabel lblOi = new JLabel("Oi");
		lblOi.setEnabled(false);
		lblOi.setBounds(26, 94, 61, 16);
		mainPanel.add(lblOi);
		
		JLabel lblClaro = new JLabel("Claro");
		lblClaro.setEnabled(false);
		lblClaro.setBounds(26, 144, 61, 16);
		mainPanel.add(lblClaro);
		
		JLabel lblOperator = new JLabel("Operator");
		lblOperator.setEnabled(false);
		lblOperator.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblOperator.setBounds(26, 41, 61, 16);
		mainPanel.add(lblOperator);
		
		JLabel lblValidSessions = new JLabel("Valid Sessions");
		lblValidSessions.setEnabled(false);
		lblValidSessions.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblValidSessions.setBounds(190, 41, 95, 16);
		mainPanel.add(lblValidSessions);
		
		JLabel lblSamples = new JLabel("Samples");
		lblSamples.setEnabled(false);
		lblSamples.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSamples.setBounds(308, 41, 56, 16);
		mainPanel.add(lblSamples);
		
		JLabel lblValleys = new JLabel("Valleys");
		lblValleys.setEnabled(false);
		lblValleys.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblValleys.setBounds(387, 41, 48, 16);
		mainPanel.add(lblValleys);
		
		JLabel lblCampaigns = new JLabel("Campaigns");
		lblCampaigns.setEnabled(false);
		lblCampaigns.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblCampaigns.setBounds(458, 41, 76, 16);
		mainPanel.add(lblCampaigns);
		
		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setEnabled(false);
		lblPlan.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblPlan.setBounds(557, 41, 29, 16);
		mainPanel.add(lblPlan);
		
		JLabel lblSessions = new JLabel("Sessions");
		lblSessions.setEnabled(false);
		lblSessions.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSessions.setBounds(110, 41, 57, 16);
		mainPanel.add(lblSessions);
		
		JLabel label = new JLabel("88888888");
		label.setEnabled(false);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(106, 69, 64, 16);
		mainPanel.add(label);
		
		JLabel label_1 = new JLabel("88888888");
		label_1.setEnabled(false);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(205, 69, 64, 16);
		mainPanel.add(label_1);
		
		JLabel label_2 = new JLabel("88888888");
		label_2.setEnabled(false);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(304, 69, 64, 16);
		mainPanel.add(label_2);
		
		JLabel label_3 = new JLabel("88888888");
		label_3.setEnabled(false);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(379, 69, 64, 16);
		mainPanel.add(label_3);
		
		JLabel label_4 = new JLabel("88888888");
		label_4.setEnabled(false);
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(464, 69, 64, 16);
		mainPanel.add(label_4);
		
		JLabel lblTimLiberty = new JLabel("Tim Liberty");
		lblTimLiberty.setEnabled(false);
		lblTimLiberty.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimLiberty.setBounds(536, 69, 71, 16);
		mainPanel.add(lblTimLiberty);
		
		JLabel label_5 = new JLabel("88888888");
		label_5.setEnabled(false);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setBounds(106, 94, 64, 16);
		mainPanel.add(label_5);
		
		JLabel label_6 = new JLabel("88888888");
		label_6.setEnabled(false);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setBounds(205, 94, 64, 16);
		mainPanel.add(label_6);
		
		JLabel label_7 = new JLabel("88888888");
		label_7.setEnabled(false);
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setBounds(304, 94, 64, 16);
		mainPanel.add(label_7);
		
		JLabel label_8 = new JLabel("88888888");
		label_8.setEnabled(false);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(379, 94, 64, 16);
		mainPanel.add(label_8);
		
		JLabel label_9 = new JLabel("88888888");
		label_9.setEnabled(false);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(464, 94, 64, 16);
		mainPanel.add(label_9);
		
		JLabel label_10 = new JLabel("Tim Liberty");
		label_10.setEnabled(false);
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(536, 94, 71, 16);
		mainPanel.add(label_10);
		
		JLabel label_11 = new JLabel("88888888");
		label_11.setEnabled(false);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(106, 119, 64, 16);
		mainPanel.add(label_11);
		
		JLabel label_12 = new JLabel("88888888");
		label_12.setEnabled(false);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(205, 119, 64, 16);
		mainPanel.add(label_12);
		
		JLabel label_13 = new JLabel("88888888");
		label_13.setEnabled(false);
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(304, 119, 64, 16);
		mainPanel.add(label_13);
		
		JLabel label_14 = new JLabel("88888888");
		label_14.setEnabled(false);
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setBounds(379, 119, 64, 16);
		mainPanel.add(label_14);
		
		JLabel label_15 = new JLabel("88888888");
		label_15.setEnabled(false);
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setBounds(464, 119, 64, 16);
		mainPanel.add(label_15);
		
		JLabel label_16 = new JLabel("Tim Liberty");
		label_16.setEnabled(false);
		label_16.setHorizontalAlignment(SwingConstants.CENTER);
		label_16.setBounds(536, 119, 71, 16);
		mainPanel.add(label_16);
		
		JLabel label_17 = new JLabel("88888888");
		label_17.setEnabled(false);
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setBounds(106, 144, 64, 16);
		mainPanel.add(label_17);
		
		JLabel label_18 = new JLabel("88888888");
		label_18.setEnabled(false);
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setBounds(205, 144, 64, 16);
		mainPanel.add(label_18);
		
		JLabel label_19 = new JLabel("88888888");
		label_19.setEnabled(false);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setBounds(304, 144, 64, 16);
		mainPanel.add(label_19);
		
		JLabel label_20 = new JLabel("88888888");
		label_20.setEnabled(false);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setBounds(379, 144, 64, 16);
		mainPanel.add(label_20);
		
		JLabel label_21 = new JLabel("88888888");
		label_21.setEnabled(false);
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setBounds(464, 144, 64, 16);
		mainPanel.add(label_21);
		
		JLabel label_22 = new JLabel("Tim Liberty");
		label_22.setEnabled(false);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setBounds(536, 144, 71, 16);
		mainPanel.add(label_22);
		
		JLabel lblSummary = new JLabel("Summary");
		lblSummary.setEnabled(false);
		lblSummary.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblSummary.setBounds(26, 13, 64, 16);
		mainPanel.add(lblSummary);
		
		table = new JTable(data, columnNames);
		table.setBounds(26, 271, 456, -78);
		mainPanel.add(table);
		
		JPanel pnlWhiteTop = new JPanel();
		pnlWhiteTop.setBackground(Color.WHITE);
		FlowLayout fl_pnlWhiteTop = (FlowLayout) pnlWhiteTop.getLayout();
		fl_pnlWhiteTop.setAlignment(FlowLayout.LEFT);
		fl_pnlWhiteTop.setVgap(25);
		frmMobileBroadbandMeasurement.getContentPane().add(pnlWhiteTop, BorderLayout.NORTH);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlWhiteTop.add(horizontalStrut_1);
		
		JLabel lblSelectOperatorsAnd = new JLabel("Select operators and campaigns to update the summary");
		lblSelectOperatorsAnd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		pnlWhiteTop.add(lblSelectOperatorsAnd);
		
		JPanel pnlOperators = new JPanel();
		frmMobileBroadbandMeasurement.getContentPane().add(pnlOperators, BorderLayout.WEST);
		pnlOperators.setLayout(new BoxLayout(pnlOperators, BoxLayout.Y_AXIS));
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		pnlOperators.add(verticalStrut_4);
		
		JLabel lblNewLabel = new JLabel("Select Operators");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlOperators.add(lblNewLabel);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		pnlOperators.add(verticalStrut_3);
		
		JButton btnNewButton_2 = new JButton("Oi");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlOperators.add(btnNewButton_2);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		pnlOperators.add(verticalStrut);
		
		JButton btnNewButton_5 = new JButton("Vivo");
		btnNewButton_5.setEnabled(false);
		btnNewButton_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlOperators.add(btnNewButton_5);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		pnlOperators.add(verticalStrut_1);
		
		JButton btnNewButton_4 = new JButton("Claro");
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlOperators.add(btnNewButton_4);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		pnlOperators.add(verticalStrut_2);
		
		JButton btnNewButton_3 = new JButton("Tim");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		pnlOperators.add(btnNewButton_3);
		
		Component horizontalStrut = Box.createHorizontalStrut(125);
		pnlOperators.add(horizontalStrut);
	}
}
