package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import calorieTracker.*;
import javax.swing.JPasswordField;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("CalorieTracker");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 404);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CalorieTracker");
		lblNewLabel.setBounds(95, 10, 145, 29);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("LogIn");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(148, 30, 46, 29);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(95, 85, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(95, 70, 61, 17);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(95, 112, 61, 20);
		contentPane.add(lblNewLabel_3);
		
		User user = new User();
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(95, 154, 145, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Don't have an account?");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(106, 227, 131, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.setBackground(new Color(237, 239, 243));
		btnNewButton_1.setBounds(95, 247, 145, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(59, 188, 224, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setBounds(132, 202, 69, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Forgot your password?");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_7.setBounds(106, 281, 134, 14);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton_2 = new JButton("Change Password");
		btnNewButton_2.setBackground(new Color(237, 239, 243));
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(95, 300, 145, 23);
		contentPane.add(btnNewButton_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(95, 131, 145, 20);
		contentPane.add(passwordField);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String password = new String(passwordField.getPassword()).strip();
					String username = textField.getText().strip();
					user.loginWithUsername(username, password);
					lblNewLabel_5.setText("");
					lblNewLabel_6.setText("");
				} catch(Exception e) {
					lblNewLabel_5.setText("Username and password combination");
					lblNewLabel_6.setText("is not valid");
				}
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				SignUp frame = new SignUp();
				dispose();
				frame.setVisible(true);
			}
		});
		
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ChangePassword frame = new ChangePassword();
				dispose();
				frame.setVisible(true);
			}
		});
	}
}
