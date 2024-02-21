package gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import calorieTracker.Ingredient;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class IngredientDetails2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngredientDetails2 frame = new IngredientDetails2(null, "banger");
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
	public IngredientDetails2(Ingredient i, String username) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(i.name.toUpperCase());
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(28, 22, 508, 22);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(28, 61, 325, 384);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Calories");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(10, 32, 135, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(i.calories.toString());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_3.setBounds(253, 32, 62, 28);
		panel.add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 305, 2);
		panel.add(separator);
		
		JLabel lblNewLabel_4 = new JLabel("Total fat " + i.totalFat + "g");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 84, 176, 20);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Saturated Fat " + i.saturatedFat + "g");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(31, 104, 155, 20);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Trans Fat " + i.transFat + "g");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(31, 120, 155, 20);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Cholesterol " + i.cholesterol + "g");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(10, 144, 176, 20);
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Sodium "+ i.sodium +"g");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_2.setBounds(10, 165, 176, 20);
		panel.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Total Carbohydrates "+ i.totalCarbs +"g");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_3.setBounds(10, 186, 305, 20);
		panel.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_5_2 = new JLabel("Dietary Fiber "+ i.dietaryFiber +"g");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2.setBounds(31, 208, 155, 20);
		panel.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Sugar "+ i.totalSugars +"g");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_3.setBounds(31, 224, 155, 20);
		panel.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("Protein "+ i.protein+"g");
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_3_1.setBounds(10, 247, 305, 20);
		panel.add(lblNewLabel_4_3_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 278, 305, 2);
		panel.add(separator_1);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Vitamin D "+i.vitaminD+"mcg");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_1.setBounds(10, 291, 155, 20);
		panel.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("Calcium "+i.calcium+"mg");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_2.setBounds(10, 310, 155, 20);
		panel.add(lblNewLabel_5_2_2);
		
		JLabel lblNewLabel_5_2_3 = new JLabel("Iron "+i.iron+"mg");
		lblNewLabel_5_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_3.setBounds(10, 329, 155, 20);
		panel.add(lblNewLabel_5_2_3);
		
		JLabel lblNewLabel_5_2_4 = new JLabel("Potassium " + i.potassium + "mg");
		lblNewLabel_5_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_4.setBounds(10, 348, 155, 20);
		panel.add(lblNewLabel_5_2_4);
		
		JLabel lblNewLabel_6 = new JLabel("by " + username);
		lblNewLabel_6.setBounds(28, 46, 141, 14);
		contentPane.add(lblNewLabel_6);

	}
}
