package UserInterface;

package proiect_UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JTextPane;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import javax.swing.JDesktopPane;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Silviu
 *
 */
public class mainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 371);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel listPanel = new JPanel();
		listPanel.setBackground(SystemColor.inactiveCaption);

		JPanel peoplePanel = new JPanel();
		peoplePanel.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(listPanel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(peoplePanel, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(listPanel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(peoplePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		JList list = new JList();
		list.setForeground(SystemColor.activeCaptionBorder);

		JButton addContactButton = new JButton("Add");

		JButton removeContactButton = new JButton("Remove");
		
		JLabel listLabel = new JLabel("Lista de persoane");
		listLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JPanel addPanel = new JPanel();
		addPanel.setBackground(SystemColor.inactiveCaption);
		GroupLayout gl_listPanel = new GroupLayout(listPanel);
		gl_listPanel.setHorizontalGroup(
			gl_listPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_listPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_listPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_listPanel.createSequentialGroup()
							.addComponent(listLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addGap(49))
						.addGroup(Alignment.TRAILING, gl_listPanel.createSequentialGroup()
							.addGroup(gl_listPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(addPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addComponent(list, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
								.addGroup(gl_listPanel.createSequentialGroup()
									.addComponent(addContactButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
									.addComponent(removeContactButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap())))
		);
		gl_listPanel.setVerticalGroup(
			gl_listPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_listPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(listLabel, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(list, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(addPanel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_listPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(removeContactButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(addContactButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JTextPane nameTextPane = new JTextPane();
		
		JTextPane ipTextPane = new JTextPane();
		
		JLabel nameLabel = new JLabel("Name:");
		
		JLabel ipLabel = new JLabel("IP:");
		GroupLayout gl_addPanel = new GroupLayout(addPanel);
		gl_addPanel.setHorizontalGroup(
			gl_addPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_addPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_addPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(ipLabel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_addPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(ipTextPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
						.addComponent(nameTextPane, Alignment.LEADING))
					.addContainerGap())
		);
		gl_addPanel.setVerticalGroup(
			gl_addPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_addPanel.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_addPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nameTextPane))
					.addGroup(gl_addPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_addPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ipTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_addPanel.createSequentialGroup()
							.addGap(5)
							.addComponent(ipLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
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
		gl_peoplePanel.setHorizontalGroup(
			gl_peoplePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_peoplePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_peoplePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_peoplePanel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_peoplePanel.createSequentialGroup()
								.addGroup(gl_peoplePanel.createParallelGroup(Alignment.LEADING)
									.addGroup(Alignment.TRAILING, gl_peoplePanel.createSequentialGroup()
										.addComponent(callButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
										.addGap(55)
										.addComponent(rejectButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
									.addComponent(muteButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
									.addComponent(tabbedPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
								.addContainerGap())
							.addGroup(Alignment.TRAILING, gl_peoplePanel.createSequentialGroup()
								.addComponent(chatLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
								.addGap(35)))
						.addGroup(Alignment.TRAILING, gl_peoplePanel.createSequentialGroup()
							.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_peoplePanel.setVerticalGroup(
			gl_peoplePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_peoplePanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(chatLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_peoplePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(volumeLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(volumeSlider, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(muteButton)
					.addGap(26)
					.addGroup(gl_peoplePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(callButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(rejectButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		peoplePanel.setLayout(gl_peoplePanel);
		contentPane.setLayout(gl_contentPane);

	}
}
