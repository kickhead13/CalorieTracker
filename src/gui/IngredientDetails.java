package gui;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

public class IngredientDetails extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngredientDetails frame = new IngredientDetails(2,
							DriverManager.getConnection(
				               "jdbc:mysql://localhost:3306/calorietracker",
				              "root", "ALEX731321"));
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
	public IngredientDetails(Integer id, Connection conn) throws Exception {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 406, 514);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ResultSet set = conn.createStatement().executeQuery(
				"select * from ingredients where id=" + id + ";"
				);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		set.next();
		
		JLabel lblNewLabel = new JLabel(set.getString("name").toUpperCase());
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblNewLabel.setBounds(28, 28, 508, 22);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(28, 61, 325, 384);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Amount per 100g");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 11, 135, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Calories");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_2.setBounds(10, 32, 135, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(new Integer(set.getInt("calories")).toString());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_3.setBounds(253, 32, 62, 28);
		panel.add(lblNewLabel_3);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 71, 305, 2);
		panel.add(separator);
		
		JLabel lblNewLabel_4 = new JLabel("Total fat " + set.getInt("totalFat") + "g");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4.setBounds(10, 84, 176, 20);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Saturated Fat " + set.getInt("saturatedFat") + "g");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(31, 104, 155, 20);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("Trans Fat " + set.getInt("transFat") + "g");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_1.setBounds(31, 120, 155, 20);
		panel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Cholesterol " + set.getInt("cholesterol") + "g");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_1.setBounds(10, 144, 176, 20);
		panel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Sodium "+ set.getInt("sodium") +"g");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_2.setBounds(10, 165, 176, 20);
		panel.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Total Carbohydrates "+ set.getInt("totalCarbs") +"g");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_3.setBounds(10, 186, 305, 20);
		panel.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_5_2 = new JLabel("Dietary Fiber "+ set.getInt("dietaryFiber") +"g");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2.setBounds(31, 208, 155, 20);
		panel.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("Sugar "+ set.getInt("totalSugars") +"g");
		lblNewLabel_5_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_3.setBounds(31, 224, 155, 20);
		panel.add(lblNewLabel_5_3);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("Protein "+ set.getInt("protein")+"g");
		lblNewLabel_4_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_4_3_1.setBounds(10, 247, 305, 20);
		panel.add(lblNewLabel_4_3_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 278, 305, 2);
		panel.add(separator_1);
		
		JLabel lblNewLabel_5_2_1 = new JLabel("Vitamin D "+set.getInt("vitaminD")+"mcg");
		lblNewLabel_5_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_1.setBounds(10, 291, 155, 20);
		panel.add(lblNewLabel_5_2_1);
		
		JLabel lblNewLabel_5_2_2 = new JLabel("Calcium "+set.getInt("calcium")+"mg");
		lblNewLabel_5_2_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_2.setBounds(10, 310, 155, 20);
		panel.add(lblNewLabel_5_2_2);
		
		JLabel lblNewLabel_5_2_3 = new JLabel("Iron "+set.getInt("iron")+"mg");
		lblNewLabel_5_2_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_3.setBounds(10, 329, 155, 20);
		panel.add(lblNewLabel_5_2_3);
		
		JLabel lblNewLabel_5_2_4 = new JLabel("Potassium " + set.getInt("potassium") + "mg");
		lblNewLabel_5_2_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5_2_4.setBounds(10, 348, 155, 20);
		panel.add(lblNewLabel_5_2_4);
	}
}
