package chat.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

/**
 * package proiect_UI;
 * 
 * import java.awt.EventQueue;
 * 
 * /**
 * 
 * @author IulianC
 *
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private int peopleSelected;

	/**
	 * 
	 * @author IulianC lista conversatiilor campuri: persoana, ip
	 */
	class selectedPersons {
		public String name;
		public String ip;
		public int isPaused;

		public selectedPersons(String name, String ip) {
			this.name = name;
			this.ip = ip;
			isPaused = 0;
		}
	}

	List<selectedPersons> listPersons = new ArrayList<MainFrame.selectedPersons>();

	JList list;
	private JTextField nameTextField;
	private JTextField ipTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 371);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel listPanel = new JPanel();
		listPanel.setBackground(SystemColor.inactiveCaption);

		final DefaultListModel<String> listModel = new DefaultListModel<String>();

		/**
		 * stergerea din lista de contacte
		 */
		JButton removeContactButton = new JButton("Remove");
		removeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listModel.isEmpty())
					return;
				listModel.remove(peopleSelected);
			}
		});
		/*
		 * adaugarea in lista
		 */
		JButton addContactButton = new JButton("Add");
		addContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton addButton = (JButton) e.getSource();
				JPanel listPanel = (JPanel) addButton.getParent();
				JPanel mainFrame = (JPanel) listPanel.getParent();
				JPanel addPanel = (JPanel) listPanel.getComponent(0);
				JList jList = (JList) listPanel.getComponent(1);

				JTextField name = (JTextField) addPanel.getComponent(2);
				JTextField ip = (JTextField) addPanel.getComponent(3);

				try {

					if (!name.getText().trim().equals("")
							&& !ip.getText().trim().equals("")) {
						Pattern pattern = Pattern
								.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
						Matcher matcher = pattern.matcher(ip.getText());
						if (matcher.matches())
							listModel.addElement(name.getText() + " "
									+ ip.getText());
						else {
							JOptionPane.showMessageDialog(mainFrame,
									"adresa IP invalida");
						}
					} else {
						JOptionPane.showMessageDialog(mainFrame,
								"Ambele campuri sunt obligatorii");
					}

				} catch (PatternSyntaxException ex) {
					// JOptionPane.showMessageDialog(listPanel, "Error");
					return;
				}
			}
		});
		JPanel peoplePanel = new JPanel();
		peoplePanel.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(listPanel, GroupLayout.PREFERRED_SIZE,
								225, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(peoplePanel, GroupLayout.PREFERRED_SIZE,
								225, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addComponent(listPanel, Alignment.TRAILING,
						GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(peoplePanel, GroupLayout.DEFAULT_SIZE,
						GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		list = new JList(listModel);
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				peopleSelected = list.getSelectedIndex();

				// JList jList = (JList) e.getSource();
				// JPanel listPanel = (JPanel) jList.getParent();
				// JOptionPane.showMessageDialog(listPanel, peopleSelected);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		list.setForeground(SystemColor.activeCaptionBorder);

		JLabel listLabel = new JLabel("Lista de persoane");
		listLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JPanel addPanel = new JPanel();
		addPanel.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_listPanel = new GroupLayout(listPanel);
		gl_listPanel
				.setHorizontalGroup(gl_listPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_listPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_listPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_listPanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_listPanel
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								addPanel,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								list,
																								Alignment.LEADING,
																								GroupLayout.PREFERRED_SIZE,
																								200,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								gl_listPanel
																										.createSequentialGroup()
																										.addComponent(
																												addContactButton,
																												GroupLayout.PREFERRED_SIZE,
																												80,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(40)
																										.addComponent(
																												removeContactButton,
																												GroupLayout.PREFERRED_SIZE,
																												80,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(0,
																												0,
																												Short.MAX_VALUE)))
																		.addGap(15))
														.addGroup(
																gl_listPanel
																		.createSequentialGroup()
																		.addComponent(
																				listLabel,
																				GroupLayout.PREFERRED_SIZE,
																				118,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap(
																				97,
																				Short.MAX_VALUE)))));
		gl_listPanel.setVerticalGroup(gl_listPanel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_listPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(listLabel, GroupLayout.DEFAULT_SIZE, 30,
								Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 161,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(addPanel, GroupLayout.PREFERRED_SIZE, 56,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(
								gl_listPanel
										.createParallelGroup(Alignment.LEADING)
										.addComponent(removeContactButton,
												GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(addContactButton,
												GroupLayout.PREFERRED_SIZE, 35,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));

		JLabel nameLabel = new JLabel("Name:");

		JLabel ipLabel = new JLabel("IP:");

		nameTextField = new JTextField();
		nameTextField.setColumns(10);

		ipTextField = new JTextField();
		ipTextField.setColumns(10);
		GroupLayout gl_addPanel = new GroupLayout(addPanel);
		gl_addPanel.setHorizontalGroup(gl_addPanel.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_addPanel
						.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_addPanel
										.createParallelGroup(Alignment.LEADING,
												false)
										.addComponent(ipLabel,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(nameLabel,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGap(20)
						.addGroup(
								gl_addPanel
										.createParallelGroup(Alignment.LEADING,
												false)
										.addComponent(nameTextField)
										.addComponent(ipTextField,
												GroupLayout.DEFAULT_SIZE, 129,
												Short.MAX_VALUE))
						.addContainerGap()));
		gl_addPanel
				.setVerticalGroup(gl_addPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_addPanel
										.createSequentialGroup()
										.addGap(5)
										.addGroup(
												gl_addPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(nameLabel)
														.addComponent(
																nameTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_addPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																ipLabel,
																GroupLayout.DEFAULT_SIZE,
																20,
																Short.MAX_VALUE)
														.addComponent(
																ipTextField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		addPanel.setLayout(gl_addPanel);
		listPanel.setLayout(gl_listPanel);

		JButton callButton = new JButton("Call");
		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JButton callButton = (JButton) e.getSource();
				JPanel peoplePanel = (JPanel) callButton.getParent();
				JTabbedPane tabbedPanel = (JTabbedPane) peoplePanel
						.getComponent(3);

				String listElem = listModel.get(peopleSelected);
				String[] parsedElem = listElem.split(" ");

				selectedPersons elem = new selectedPersons(parsedElem[0],
						parsedElem[1]);
				listPersons.add(elem);

				JPanel jp1 = new JPanel();
				JLabel text = new JLabel();
				text.setText("You talk with " + parsedElem[0]);
				jp1.add(text);
				jp1.setName(parsedElem[0]);
				tabbedPanel.addTab(parsedElem[0], jp1);
			}
		});

		JButton rejectButton = new JButton("Reject");
		rejectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton callButton = (JButton) e.getSource();
				JPanel peoplePanel = (JPanel) callButton.getParent();
				JTabbedPane tabbedPanel = (JTabbedPane) peoplePanel
						.getComponent(3);
				if (tabbedPanel.getComponentCount() == 0)
					return;
				tabbedPanel.remove(tabbedPanel.getSelectedIndex());
			}
		});

		JButton muteButton = new JButton("Mute");
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton callButton = (JButton) e.getSource();
				JPanel peoplePanel = (JPanel) callButton.getParent();
				JTabbedPane tabbedPanel = (JTabbedPane) peoplePanel
						.getComponent(3);

				int selectedTab = tabbedPanel.getSelectedIndex();
				JPanel selectedPanelTab = (JPanel) tabbedPanel
						.getComponent(selectedTab);
				JLabel text = (JLabel) selectedPanelTab.getComponent(0);

				for (selectedPersons x : listPersons) {
					if (selectedPanelTab.getName().equals(x.name)) {
						if (x.isPaused == 0) {
							x.isPaused = 1;
							text.setText("<html><p align=center style=\"width:100px\">You talk with "
									+ x.name + " Paused" + "</p></html>");
						} else if (x.isPaused == 1) {
							x.isPaused = 0;

							text.setText("<html><p align=center style=\"width:100px\">You talk with "
									+ x.name + " Active" + "</p></html>");
						}
						break;
					}
				}
			}

		});

		JSlider volumeSlider = new JSlider();
		volumeSlider.setBackground(SystemColor.inactiveCaption);

		JLabel volumeLabel = new JLabel("Volume");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.inactiveCaption);

		JLabel chatLabel = new JLabel("Convorbiri");
		chatLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_peoplePanel = new GroupLayout(peoplePanel);
		gl_peoplePanel
				.setHorizontalGroup(gl_peoplePanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_peoplePanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_peoplePanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_peoplePanel
																		.createSequentialGroup()
																		.addComponent(
																				chatLabel,
																				GroupLayout.PREFERRED_SIZE,
																				118,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(35))
														.addGroup(
																gl_peoplePanel
																		.createSequentialGroup()
																		.addGroup(
																				gl_peoplePanel
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								gl_peoplePanel
																										.createSequentialGroup()
																										.addComponent(
																												callButton,
																												GroupLayout.PREFERRED_SIZE,
																												75,
																												GroupLayout.PREFERRED_SIZE)
																										.addGap(38)
																										.addComponent(
																												rejectButton,
																												GroupLayout.DEFAULT_SIZE,
																												83,
																												Short.MAX_VALUE))
																						.addComponent(
																								tabbedPane,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								196,
																								Short.MAX_VALUE)
																						.addComponent(
																								muteButton,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								196,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_peoplePanel
																										.createSequentialGroup()
																										.addComponent(
																												volumeLabel,
																												GroupLayout.PREFERRED_SIZE,
																												48,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												volumeSlider,
																												GroupLayout.PREFERRED_SIZE,
																												141,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(19)))));
		gl_peoplePanel
				.setVerticalGroup(gl_peoplePanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_peoplePanel
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(chatLabel,
												GroupLayout.PREFERRED_SIZE, 30,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED, 11,
												Short.MAX_VALUE)
										.addComponent(tabbedPane,
												GroupLayout.PREFERRED_SIZE,
												131, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												gl_peoplePanel
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																volumeSlider,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																volumeLabel,
																GroupLayout.PREFERRED_SIZE,
																25,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addComponent(muteButton)
										.addGap(26)
										.addGroup(
												gl_peoplePanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																callButton,
																GroupLayout.PREFERRED_SIZE,
																35,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																rejectButton,
																GroupLayout.PREFERRED_SIZE,
																35,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		peoplePanel.setLayout(gl_peoplePanel);
		contentPane.setLayout(gl_contentPane);

	}
}
