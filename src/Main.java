import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;


public class Main {

	private JFrame frmMobileBroadbandMeasurement;
	private static Vector<String> columnNames = new Vector<String>();
	private static Vector<Vector<Integer>> data = new Vector<Vector<Integer>>();
	
	int lenght =9;
	String startTime="2012-01-01", endTime="2013-12-31";
	float Delta1=0.4f, Delta2=0.4f;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	
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
		frmMobileBroadbandMeasurement.setBounds(100, 100, 815, 582);
		frmMobileBroadbandMeasurement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMobileBroadbandMeasurement.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlSouth = new JPanel();
		frmMobileBroadbandMeasurement.getContentPane().add(pnlSouth, BorderLayout.SOUTH);
		pnlSouth.setLayout(new BoxLayout(pnlSouth, BoxLayout.Y_AXIS));
		
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
		
		Box verticalBox = Box.createVerticalBox();
		frmMobileBroadbandMeasurement.getContentPane().add(verticalBox, BorderLayout.WEST);
		
		Panel panel_1 = new Panel();
		frmMobileBroadbandMeasurement.getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_2);
		
		JToggleButton btnNewButton_4 = new JToggleButton("Claro");
		panel_1.add(btnNewButton_4);
		btnNewButton_4.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JToggleButton btnNewButton_3 = new JToggleButton("Tim");
		panel_1.add(btnNewButton_3);
		btnNewButton_3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JToggleButton btnNewButton_2 = new JToggleButton("Oi");
		panel_1.add(btnNewButton_2);
		btnNewButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JToggleButton btnNewButton_5 = new JToggleButton("Vivo");
		panel_1.add(btnNewButton_5);
		btnNewButton_5.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_1);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("CQI");
		panel_1.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setActionCommand("CQI");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JRadioButton rdbtnRadio = new JRadioButton("RSCP");
		panel_1.add(rdbtnRadio);
		rdbtnRadio.setSelected(true);
		rdbtnRadio.setActionCommand("RSCP");
		rdbtnRadio.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonGroup.add(rdbtnRadio);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Ec/I0");
		panel_1.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setActionCommand("Ec/i0");
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut);
		
		JButton btnNewButton = new JButton("Valleys");
		panel_1.add(btnNewButton);
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Core.launchChartFTP(startTime, endTime, lenght, Delta1, Delta2);
			}
		});
		
		JButton btnReport = new JButton("Summary");
		panel_1.add(btnReport);
		btnReport.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Core.summary(startTime, endTime, lenght, Delta1, Delta2);
			}
		});
		
		JButton btnReport_1 = new JButton("PDF x CDF");
		panel_1.add(btnReport_1);
		btnReport_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Mai parameter: "+ buttonGroup.getSelection().getActionCommand());
				Core.cdfXPdf(startTime, endTime, lenght, Delta1, Delta2, buttonGroup.getSelection().getActionCommand());
				
			}
		});
		
		JButton btnd = new JButton("3D");
		panel_1.add(btnd);
		btnd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tput=JOptionPane.showInputDialog(null, "Throughput to Analyse", "Throughput Selection", JOptionPane.QUESTION_MESSAGE);
				Core.chart3D(startTime, endTime, lenght, Delta1, Delta2, buttonGroup.getSelection().getActionCommand(), Integer.parseInt(tput));
			}
		});
	}
}
