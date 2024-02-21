package gui;

import java.awt.EventQueue;
import calorieTracker.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.JButton;

public class RequestIngredient extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestIngredient frame = new RequestIngredient(new User());
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
	public RequestIngredient(User user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblNewLabel_1 = new JLabel("Request new Ingredient");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(91, 30, 161, 29);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(77, 126, 184, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name the Ingredient (max. 30)");
		lblNewLabel_2.setBounds(77, 112, 175, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Request");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(121, 170, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					PreparedStatement stat = user.conn.prepareStatement(
							"INSERT INTO requests (nameOfIngredient, usernameOfRequester, acceptedRejectedPending)"+
							"VALUES (?,?,3);"
						);
					stat.setString(1, textField.getText());
					stat.setString(2, user.username);
					stat.executeUpdate();
					stat.close();
					dispose();
				}catch(Exception e) {e.printStackTrace();}
			}
		});
		contentPane.add(btnNewButton);
	}

}
