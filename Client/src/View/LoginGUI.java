package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.Client;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernametf;
	private JPasswordField pwtf;

	/**
	 * Launch the application.
	 */
	static LoginGUI frame;
	public static void openGUI()
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LoginGUI();
					frame.setVisible(true);
					frame.setTitle("Login");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void closeGUI()
	{
		frame.setVisible(false);
	}

	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernamelb = new JLabel("Username");
		usernamelb.setBounds(43, 63, 100, 24);
		contentPane.add(usernamelb);
		
		usernametf = new JTextField();
		usernametf.setBounds(153, 65, 229, 20);
		contentPane.add(usernametf);
		usernametf.setColumns(10);
		
		JLabel pwlb = new JLabel("Password");
		pwlb.setBounds(43, 123, 73, 14);
		contentPane.add(pwlb);
		
		pwtf = new JPasswordField();
		pwtf.setBounds(153, 120, 229, 20);
		contentPane.add(pwtf);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client.SendAccount(usernametf.getText(), pwtf.getText());
			}
		});
		btnNewButton.setBounds(172, 180, 89, 23);
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);
	}
}
