package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calorieTracker.User;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.util.Random;
import emailer.EmailSender;
import java.awt.Color;

public class EmailConfirmation extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	String email;
	String randomint;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmailConfirmation frame = new EmailConfirmation("idkwhatthismeans12@gmail.com", "", "");
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
	public EmailConfirmation(String email, String username, String password) {
		
		this.email = email;
		
		Random rand = new Random();
		Integer test = rand.nextInt(1000, 9999);
		randomint = test.toString();
		EmailSender.send("nfel bmac mobj ujxi", email, randomint);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 352, 404);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CalorieTracker");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel.setBounds(95, 10, 145, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail Confirmation");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(105, 30, 154, 29);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(95, 108, 145, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Code");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(95, 93, 145, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(95, 139, 145, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("A verifcation code has");
		lblNewLabel_3.setBounds(95, 160, 145, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("been sent to your e-mail");
		lblNewLabel_4.setBounds(95, 178, 146, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Please enter it above!");
		lblNewLabel_5.setBounds(95, 195, 122, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setBounds(116, 220, 135, 14);
		contentPane.add(lblNewLabel_6);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String usercode = textField.getText().strip();
				if(usercode.equals(randomint)) {
					User user = new User();
					user.signUp(email, username, password);
					Main frame = new Main();
					dispose();
					frame.setVisible(true);
				}
				else {
					lblNewLabel_6.setText("Code is incorrect!");
				}
			}
		});
	}

}
