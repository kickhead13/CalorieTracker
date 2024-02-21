package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import calorieTracker.*;
import javax.swing.JButton;

public class MealEditor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MealEditor frame = new MealEditor("Mic Dejun", 14, new User());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void genPanel(User user, JPanel panel_1, Integer mealId) throws Exception {
		
		panel_1.removeAll();
		
		ResultSet set = user.conn.createStatement().executeQuery(
				"SELECT * FROM ingredientsOfMeals WHERE mealId=" + mealId +";"
				);
		
		for(int i = 0; i<10; i++) {
			
			if(!set.next()) {
				break;
			}
			
			//System.out.println(set.getInt("ingredientId"));
			
			ResultSet set2 = user.conn.createStatement().executeQuery(
					"SELECT * FROM ingredients WHERE id=" + set.getInt("ingredientId") + ";"
					);
			
			set2.next();
			Ingredient ing = new Ingredient();
			
			ing.readIngredient(set2, set.getInt("quantity"));
			
			JLabel lblNewLabel_2 = new JLabel(ing.name + " - " + set.getInt("quantity") + "g");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblNewLabel_2.setBounds(305, 11 + i * 30, 120, 20);
			panel_1.add(lblNewLabel_2);
			
			JLabel lblNewLabel_4 = new JLabel("X");
			lblNewLabel_4.setForeground(new Color(0, 0, 0));
			lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblNewLabel_4.setBounds(420, 11 + i * 30, 46, 14);
			lblNewLabel_4.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent me) {
					try {
						PreparedStatement stat = user.conn.prepareStatement(
								"DELETE FROM ingredientsOfMeals WHERE ingredientId=" + ing.id + " AND mealId=" + mealId + ";"
								);
						stat.executeUpdate();
						stat.close();
						genPanel(user, panel_1, mealId);
						panel_1.validate();
						panel_1.repaint();
					} catch(Exception e) {e.printStackTrace();}
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
			panel_1.add(lblNewLabel_4);
			set2.close();
		}
		set.close();
	}
	
	/**
	 * Create the frame.
	 */
	public MealEditor(String mealName, Integer mealId, User user) {
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
		
		JLabel lblNewLabel = new JLabel("CalorieTracker", SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(245, 252, 251));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel.setBounds(0, 0, 734, 51);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\"" + mealName + "\"", SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(0, 62, 734, 25);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 252, 251));
		panel_1.setBounds(0, 98, 734, 311);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Ingredient");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(308, 440, 126, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				IngredientSearch frame = new IngredientSearch(user, mealId);
				frame.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		try {
			genPanel(user, panel_1, mealId);
			panel_1.revalidate();
			panel_1.repaint();
		}catch(Exception e) {e.printStackTrace();}
		
		JLabel lblNewLabel_6 = new JLabel("Refresh");
		lblNewLabel_6.setForeground(new Color(245, 252, 251));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent me) {
				try {
					List<Meal> meals = user.getMeals();
					genPanel(user, panel_1, mealId);
					panel_1.revalidate();
					panel_1.repaint();
				} catch(Exception e) {
					e.printStackTrace();
				}
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
		lblNewLabel_6.setBounds(650, 20, 57, 14);
		panel.add(lblNewLabel_6);
		
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
		
	}

}
