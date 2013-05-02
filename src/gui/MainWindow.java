package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;

import control.Core;

public class MainWindow extends JFrame {

	// private JFrame frmMobileBroadbandMeasurement;

	Vector<Integer> operatorsSelected = new Vector<Integer>();

	int lenght = 9;
	String startTime = "2012-01-01", endTime = "2013-12-31";
	float Delta1 = 0.4f, Delta2 = 0.4f;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JToggleButton btnClaro;
	private JToggleButton btnTim;
	private JToggleButton btnOi;
	private JToggleButton btnVivo;
	private JTable table;
	private Vector<String> campaignsList = new Vector<String>();
	private String errorCampaing="At least one campaign must be selected. To select, use control (win) or command (mac) and shift keys";

	//private TableModel model = new DefaultTableModel();
	private JPanel panel_2;

	private boolean testIfAtLeastOneCampaignWasSelected() {
		if (campaignsList.size() > 0)
			return true;
		else
			return false;
	}
	
	private void updateCampaignsList() {
			campaignsList = new Vector<String>();
			for (int i = 0; i < table.getSelectedRows().length; i++) {
				campaignsList.add((String) table.getModel().getValueAt(
						table.getSelectedRows()[i], 0));
			}
		

	}
	
	private void updateOperators() {
		operatorsSelected = new Vector<Integer>();
		if (btnClaro.isSelected()) {
			operatorsSelected
					.add(Integer.parseInt(btnClaro.getActionCommand()));
		}
		if (btnVivo.isSelected()) {
			operatorsSelected.add(Integer.parseInt(btnVivo.getActionCommand()));
		}
		if (btnOi.isSelected()) {
			operatorsSelected.add(Integer.parseInt(btnOi.getActionCommand()));
		}
		if (btnTim.isSelected()) {
			operatorsSelected.add(Integer.parseInt(btnTim.getActionCommand()));
		}
		System.out.print("Operators: ");
		for (int i = 0; i < operatorsSelected.size(); i++) {
			System.out.print(operatorsSelected.get(i) + " ");
		}
		System.out.println();
		table.setModel(Core.updateTable(operatorsSelected));
		

		
		
	}

	private boolean testsIfExistsOperatorsSelected() {
		if ((btnClaro.isSelected() == false) && (btnTim.isSelected() == false)
				&& (btnOi.isSelected() == false)
				&& (btnVivo.isSelected() == false)) {
			JOptionPane.showMessageDialog(null,
					"At least one operator must be selected");
			return false;
		} else
			return true;
	}

	private void populateTable() {
		Core.updateTable(operatorsSelected);
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// frmMobileBroadbandMeasurement = new JFrame();
		setTitle("Mobile Broadband Measurement Compiler");
		setBounds(100, 100, 815, 582);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel pnlSouth = new JPanel();
		getContentPane().add(pnlSouth, BorderLayout.SOUTH);
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
		getContentPane().add(pnlWhiteTop, BorderLayout.NORTH);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		pnlWhiteTop.add(horizontalStrut_1);

		JLabel lblSelectOperatorsAnd = new JLabel(
				"Select operators and campaigns to update the summary");
		lblSelectOperatorsAnd.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		pnlWhiteTop.add(lblSelectOperatorsAnd);

		Box verticalBox = Box.createVerticalBox();
		getContentPane().add(verticalBox, BorderLayout.WEST);

		Panel panel_1 = new Panel();
		getContentPane().add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panel_1.add(verticalStrut_2);

		btnClaro = new JToggleButton("Claro");
		btnClaro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOperators();
			}
		});
		btnClaro.setActionCommand("5");
		panel_1.add(btnClaro);
		btnClaro.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnTim = new JToggleButton("Tim");
		btnTim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOperators();
			}
		});
		btnTim.setActionCommand("2");
		panel_1.add(btnTim);
		btnTim.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnOi = new JToggleButton("Oi");
		btnOi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOperators();
			}
		});
		btnOi.setActionCommand("31");
		panel_1.add(btnOi);
		btnOi.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnVivo = new JToggleButton("Vivo");
		btnVivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateOperators();
			}
		});
		btnVivo.setActionCommand("11");
		panel_1.add(btnVivo);
		btnVivo.setAlignmentX(Component.CENTER_ALIGNMENT);

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
				updateCampaignsList();
				if (testIfAtLeastOneCampaignWasSelected()) {
					if (testsIfExistsOperatorsSelected())
						updateCampaignsList();
					Core.launchChartFTP(operatorsSelected, campaignsList, lenght, Delta1,
							Delta2);
				} else
					JOptionPane.showMessageDialog(null,
							errorCampaing);
			}
		});

		JButton btnReport = new JButton("Summary");
		panel_1.add(btnReport);
		btnReport.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCampaignsList();
				if (testIfAtLeastOneCampaignWasSelected()) {
					if (testsIfExistsOperatorsSelected())
						Core.summary(operatorsSelected, campaignsList, lenght, Delta1, Delta2);
				} else
					JOptionPane.showMessageDialog(null,
							errorCampaing);
			}
		});

		JButton btnReport_1 = new JButton("PDF x CDF");
		panel_1.add(btnReport_1);
		btnReport_1.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnReport_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCampaignsList();
				if (testIfAtLeastOneCampaignWasSelected()) {
					if (testsIfExistsOperatorsSelected())
						Core.cdfXPdf(operatorsSelected, campaignsList, lenght, Delta1, Delta2,	buttonGroup.getSelection().getActionCommand());
				}
				else
					JOptionPane.showMessageDialog(null, errorCampaing);

			}
		});

		JButton btnd = new JButton("3D");
		panel_1.add(btnd);
		btnd.setAlignmentX(Component.CENTER_ALIGNMENT);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
				panel_2.setLayout(null);
				
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(6, 6, 672, 155);
				panel_2.add(scrollPane);
				
						table = new JTable();
						scrollPane.setViewportView(table);
						table.setShowVerticalLines(false);
						table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
						table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
						
						table.setGridColor(Color.BLACK);

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		btnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCampaignsList();
				
				if (testIfAtLeastOneCampaignWasSelected()) {
				if (testsIfExistsOperatorsSelected()) {
					String tput = JOptionPane.showInputDialog(null,
							"Throughput to Analyse", "Throughput Selection",
							JOptionPane.QUESTION_MESSAGE);
					Core.chart3D(operatorsSelected, campaignsList, lenght, Delta1, Delta2,
							buttonGroup.getSelection().getActionCommand(),
							Integer.parseInt(tput));
				}
				}
				else
					JOptionPane.showMessageDialog(null, errorCampaing);
			}
		});
	}
}
