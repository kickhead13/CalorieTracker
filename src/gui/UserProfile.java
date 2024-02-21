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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;

import javax.swing.JButton;

public class UserProfile extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfile frame = new UserProfile(new User());
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
	public UserProfile(User user) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 734, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel_3 = new JLabel("LogOut");
		lblNewLabel_3.setForeground(new Color(245, 252, 251));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(660, 20, 46, 14);
		lblNewLabel_3.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				Main frame = new Main();
				dispose();
				frame.setVisible(true);
			}
			public void mouseReleased(MouseEvent me) {}
			public void mouseEntered(MouseEvent me) {
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			public void mouseExited(MouseEvent me) {lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));}
			public void mousePressed(MouseEvent me) {}
		});
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Home");
		lblNewLabel_4.setForeground(new Color(245, 252, 251));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(600, 20, 46, 14);
		lblNewLabel_4.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				App frame = new App(user);
				dispose();
				frame.setVisible(true);
			}
			public void mouseReleased(MouseEvent me) {}
			public void mouseEntered(MouseEvent me) {
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			public void mouseExited(MouseEvent me) {lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));}
			public void mousePressed(MouseEvent me) {}
		});
		panel.add(lblNewLabel_4);
		
		
		JLabel lblNewLabel = new JLabel(user.username + "'s Profile.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(new Color(245, 252, 251));
		lblNewLabel.setBounds(20, 0, 221, 51);
		lblNewLabel.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				System.out.print("profile");
			}
			public void mouseReleased(MouseEvent me) {}
			public void mouseEntered(MouseEvent me) {lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));}
			public void mouseExited(MouseEvent me) {lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));}
			public void mousePressed(MouseEvent me) {}
		});
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CalorieTracker");
		lblNewLabel_1.setForeground(new Color(245, 252, 251));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(300, 0, 147, 51);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username: " + user.username);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(339, 123, 385, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("E-mail: " + user.email);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(339, 177, 365, 23);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Password: ******* ");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_2.setBounds(339, 222, 175, 14);
		contentPane.add(lblNewLabel_2_2);
		
		
		try {
			
			ResultSet set = user.conn.createStatement().executeQuery(
					"select COUNT(id) as NUM from meals where owner=\'" + user.username + "\';"
					);
			
			if(!set.next()) throw new Exception("");
			JLabel lblNewLabel_2_3 = new JLabel("No. Of Meals: " + set.getInt("NUM") );
			lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_2_3.setBounds(339, 264, 385, 14);
			contentPane.add(lblNewLabel_2_3);
		} catch(Exception e) {
			JLabel lblNewLabel_2_3 = new JLabel("No. Of Meals: EROARE");
			lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_2_3.setBounds(339, 264, 385, 14);
			contentPane.add(lblNewLabel_2_3);
		}
		JLabel lblNewLabel_2_4 = new JLabel("Role: " + (user.adminKey ? "ADMIN" : "USER"));
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2_4.setBounds(339, 300, 175, 26);
		contentPane.add(lblNewLabel_2_4);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(240, 219, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ConfirmChangeEmail frame = new ConfirmChangeEmail(user.email);
				dispose();
				frame.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Change");
		btnNewButton_1.setBackground(new Color(237, 239, 243));
		btnNewButton_1.setBounds(240, 177, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
