package gui;

import java.awt.EventQueue;
import calorieTracker.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User kick = new User();
					kick.loginWithUsername("kickhead13", "Tomi1993");
					App frame = new App(kick);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void genPanel(JPanel panel, User user) throws Exception {
		panel.removeAll();
		
		ResultSet set = user.conn.createStatement().executeQuery(
				"select * from meals where owner=\'" + user.username +"\';"
				);
		
		for(int i = 0; i<10; i++) {
			
			if(!set.next()) {
				set.close();
				break;
			}
			
			Integer num = 30;
			Integer mealId = set.getInt("id");
			String owner = set.getString("owner");
			
			System.out.println(set.getString("mealName"));
			JLabel lblNewLabel_5 = new JLabel(set.getString("mealName"));
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_5.setBounds(220, 40 + i*num, 157, 14);
			panel.add(lblNewLabel_5);
			
			JButton btnNewButton = new JButton("View");
			btnNewButton.setBounds(396, 36 + i*num, 89, 23);
			btnNewButton.setBackground(new Color(237, 239, 243));
			panel.add(btnNewButton);
			
			JLabel lblNewLabel_4 = new JLabel("X");
			lblNewLabel_4.setForeground(new Color(0, 0, 0));
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_4.setBounds(511, 39 + i*num, 46, 14);
			lblNewLabel_4.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent me) {
					try {
						PreparedStatement stat = user.conn.prepareStatement(
								"DELETE FROM ingredientsOfMeals "
								+ "WHERE mealId = " + mealId + ";" 
								);
						stat.executeUpdate();
						stat.close();
						stat = user.conn.prepareStatement(
								"DELETE FROM meals WHERE id = " + mealId + ";"
								);
						stat.executeUpdate();
						stat.close();
						genPanel(panel, user);
						panel.revalidate();
						panel.repaint();
					}catch(Exception e) {e.printStackTrace();}
				}
				public void mouseReleased(MouseEvent me) {}
				public void mouseEntered(MouseEvent me) {
					
					lblNewLabel_4.setForeground(new Color(255, 0, 0));
				}
				public void mouseExited(MouseEvent me) {
					lblNewLabel_4.setForeground(new Color(0, 0, 0));
				}
				public void mousePressed(MouseEvent me) {}
			});
			panel.add(lblNewLabel_4);
			
			
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public App(User user) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 733, 51);
		contentPane.add(panel);
		panel.setLayout(null);
		


		
		JLabel lblNewLabel = new JLabel("CalorieTracker");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(245, 252, 251));
		lblNewLabel.setBounds(300, 0, 147, 51);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Hi, " + user.username + "!");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(245, 252, 251));
		lblNewLabel_1.setBounds(25, 18, 111, 14);
		lblNewLabel_1.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				UserProfile frame = new UserProfile(user);
				dispose();
				frame.setVisible(true);
			}
			public void mouseReleased(MouseEvent me) {}
			public void mouseEntered(MouseEvent me) {lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));}
			public void mouseExited(MouseEvent me) {lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));}
			public void mousePressed(MouseEvent me) {}
		});
		panel.add(lblNewLabel_1);
		
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
		
		JLabel lblNewLabel_6 = new JLabel("Refresh");
		lblNewLabel_6.setForeground(new Color(245, 252, 251));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				
			}
			public void mouseReleased(MouseEvent me) {}
			public void mouseEntered(MouseEvent me) {
				lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
			}
			public void mouseExited(MouseEvent me) {
				lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
			}
			public void mousePressed(MouseEvent me) {}
		});
		lblNewLabel_6.setBounds(578, 20, 57, 14);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_2 = new JLabel("Your Meals");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel_2.setBounds(299, 62, 203, 35);
		contentPane.add(lblNewLabel_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 252, 251));
		panel_1.setBounds(0, 108, 733, 302);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		
		try {
			genPanel(panel_1, user);
			panel_1.revalidate();
			panel_1.repaint();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
