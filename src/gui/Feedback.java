package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.*;
import java.sql.PreparedStatement;

import calorieTracker.*;

import javax.swing.JTextArea;
import javax.swing.JButton;

public class Feedback extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feedback frame = new Feedback(new User());
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
	public Feedback(User user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 740, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 724, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("FeedBack");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(245, 252, 251));
		lblNewLabel.setBounds(0, 0, 724, 51);
		panel.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(163, 159, 395, 173);
		contentPane.add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Write your Report/Feedback in the box below.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(163, 140, 395, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(316, 350, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					PreparedStatement stat = user.conn.prepareStatement(
							"INSERT INTO feedback (username, feedbackMessage)" +
							" VALUES (?,?);"
							);
					stat.setString(1, user.username);
					stat.setString(2, textArea.getText());
					stat.executeUpdate();
					stat.close();
					dispose();
				} catch(Exception e) {e.printStackTrace();}
			}
		});
		contentPane.add(btnNewButton);
	}
}
