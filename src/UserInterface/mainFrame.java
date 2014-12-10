/**
package proiect_UI;

import java.awt.EventQueue;

/**
 * @author IulianC
 *
 */
public class MainFrame extends JFrame {

	private JPanel contentPane;

	private int peopleSelected;
	
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

		JButton removeContactButton = new JButton("Remove");
		removeContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listModel.isEmpty())
					return;
				listModel.remove(peopleSelected);
			}
		});
		JButton addContactButton = new JButton("Add");
		addContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton addButton = (JButton) e.getSource();
				JPanel listPanel = (JPanel) addButton.getParent();
				JPanel addPanel = (JPanel) listPanel.getComponent(0);
				JList jList = (JList) listPanel.getComponent(1);

				JTextField name = (JTextField) addPanel.getComponent(2);
				JTextField ip = (JTextField) addPanel.getComponent(3);

				if (!name.getText().trim().equals("")
						&& !ip.getText().trim().equals(""))
					listModel.addElement(name.getText() + " " + ip.getText());
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
				
				peopleSelected=list.getSelectedIndex();
				
				//JList jList = (JList) e.getSource();
				//JPanel listPanel = (JPanel) jList.getParent();
				
				//JOptionPane.showMessageDialog(listPanel, peopleSelected);
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

		JButton rejectButton = new JButton("Reject");

		JButton muteButton = new JButton("Mute");

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
