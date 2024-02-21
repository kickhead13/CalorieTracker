package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import exceptionHandling.PasswordException;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class PasswordChanger extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordChanger frame = new PasswordChanger("idkwhatthismeans12@gmail.com");
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
	public PasswordChanger(String email) {
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
		
		JLabel lblNewLabel_1 = new JLabel("Change Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(107, 30, 154, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(95, 70, 125, 14);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(95, 85, 145, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(95, 141, 145, 20);
		contentPane.add(passwordField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Repeat Password");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(94, 127, 126, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(95, 223, 176, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(95, 238, 166, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Confirm");
		btnNewButton.setBounds(95, 189, 145, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String p1 = new String(passwordField.getPassword());
					String p2 = new String(passwordField_1.getPassword());
					if(!p1.equals(p2) || !p1.matches(".*[0-9].*") || p1.length() < 8) throw new PasswordException("different");
					lblNewLabel_4.setText("");
					lblNewLabel_5.setText("");
					Connection conn = DriverManager.getConnection(
					               "jdbc:mysql://localhost:3306/calorietracker",
					              "root", "ALEX731321");
					PreparedStatement stat = conn.prepareStatement("UPDATE users SET password=? WHERE email=?;");
					stat.setString(1, p1);
					stat.setString(2, email);
					stat.executeUpdate();
					stat.close();
					conn.close();
					Main frame = new Main();
					dispose();
					frame.setVisible(true);
				} catch(PasswordException p) {
					lblNewLabel_4.setText("both passwords must contain");
					lblNewLabel_5.setText("numbers and be longer than 8!");
				} catch(SQLException s) {
					lblNewLabel_4.setText("server failed to update!");
					lblNewLabel_5.setText("close and try again!");
					s.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		
	}
}
