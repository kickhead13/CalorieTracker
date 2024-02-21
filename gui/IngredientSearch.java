package gui;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import calorieTracker.*;

public class IngredientSearch extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	ResultSet set;
	private JScrollPane panel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IngredientSearch frame = new IngredientSearch(new User(), 3);
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
	public IngredientSearch(User user, Integer mealId) {
		setTitle("Ingredient Search");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 252, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 21, 240, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		List<JLabel> lblList = new ArrayList<>();
		lblList.add(new JLabel(""));
		lblList.add(new JLabel(""));
		lblList.add(new JLabel(""));
		lblList.add(new JLabel(""));
		
		panel = new JScrollPane(new JPanel());
		panel.setBackground(new Color(245, 252, 251));
		panel.setVerticalScrollBarPolicy(panel.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setHorizontalScrollBarPolicy(panel.HORIZONTAL_SCROLLBAR_NEVER);
		panel.setBounds(0, 72, 458, 353);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(10, 436, 438, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(new Color(237, 239, 243));
		btnNewButton.setBounds(260, 20, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//System.out.println("test");
				try {
					
					panel.removeAll();
					panel.revalidate();
					panel.repaint();
					
					String text = textField.getText();
					Connection conn = DriverManager.getConnection(
				               "jdbc:mysql://localhost:3306/calorietracker",
				              "root", "ALEX731321");
					System.out.println(text);
					set = conn.createStatement().executeQuery(
							"select * from ingredients where name like \'%" + text +"%\';"
							);
					for(int i = 0; i<10; i++)	{
						if(!set.next()) {
							set.close();
							break;
						}

						JLabel lbl = new JLabel(set.getString("name") + " - " + set.getInt("calories") + " kcal - " + set.getInt("protein")+" proteins");
						lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
						lbl.setBounds(28, 18 + i * 27, 212, 14);
						
						Integer a = set.getInt("id");
						
						JButton btnNewButton_1 = new JButton("...");
						btnNewButton_1.setBounds(210, 15 + i*27, 63, 23);
						btnNewButton_1.setBackground(new Color(237, 239, 243));
						btnNewButton_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								try {
									IngredientDetails frame = new IngredientDetails(a,conn);
									frame.setVisible(true);
								}catch(Exception e) {e.printStackTrace();}
							}
						});
						panel.add(btnNewButton_1);
						
						
						JTextField textField_1 = new JTextField();
						textField_1.setBounds(370, 15+i*27, 63, 23);
						textField_1.setColumns(10);
						panel.add(textField_1);
						
						JButton btnNewButton_2 = new JButton("Add");
						btnNewButton_2.setBounds(290, 15 + i*27, 63, 23);
						btnNewButton_2.setBackground(new Color(237, 239, 243));
						btnNewButton_2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								try {
									PreparedStatement stat = conn.prepareStatement(
											"INSERT INTO ingredientsofmeals (ingredientId, mealId, quantity)" +
											"VALUES(?,?,?)"
											);
									stat.setInt(1, a);
									stat.setInt(2, mealId);
									try {
										
										stat.setInt(3, Integer.parseInt(textField_1.getText()));
										lblNewLabel_4.setText("");
									}catch(NumberFormatException e) {
										lblNewLabel_4.setText("Quantity must be number!");
										return;
									}
									stat.executeUpdate();
									stat.close();
								}catch(Exception e) {e.printStackTrace();}
							}
						});
						panel.add(btnNewButton_2);
						
						/*JButton btnNewButton_3 = new JButton("!");
						btnNewButton_3.setBounds(370, 15 + i*27, 63, 23);
						btnNewButton_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent ae) {
								try {
									System.out.println(a);
								}catch(Exception e) {e.printStackTrace();}
							}
						});
						panel.add(btnNewButton_3);*/
						
						panel.add(lbl);
						panel.revalidate();
						panel.repaint();
						
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Ingredient");
		lblNewLabel.setBounds(10, 52, 89, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("More Info.");
		lblNewLabel_1.setBounds(210, 52, 70, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Add Ingr.");
		lblNewLabel_2.setBounds(290, 52, 70, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Quantity");
		lblNewLabel_3.setBounds(370, 52, 88, 14);
		contentPane.add(lblNewLabel_3);
		
		separator = new JSeparator();
		separator.setBounds(0, 71, 458, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBounds(0, 71, 458, 2);
		contentPane.add(separator_1);
		
		lblNewLabel_5 = new JLabel("Can't find your ingredient?");
		lblNewLabel_5.setBounds(43, 455, 161, 14);
		contentPane.add(lblNewLabel_5);
		
		btnNewButton_3 = new JButton("Request Ingredient");
		btnNewButton_3.setBackground(new Color(237, 239, 243));
		btnNewButton_3.setBounds(53, 469, 125, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				RequestIngredient frame = new RequestIngredient(user);
				frame.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_3);
		
		lblNewLabel_6 = new JLabel("Is there somehting incorrect?");
		lblNewLabel_6.setBounds(248, 455, 185, 14);
		contentPane.add(lblNewLabel_6);
		
		btnNewButton_4 = new JButton("Report");
		btnNewButton_4.setBackground(new Color(237, 239, 243));
		btnNewButton_4.setBounds(275, 469, 116, 23);
		contentPane.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("Done");
		btnNewButton_5.setBackground(new Color(237, 239, 243));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});
		btnNewButton_5.setBounds(359, 20, 89, 23);
		contentPane.add(btnNewButton_5);
		
		

		
		/*JButton btnNewButton_1 = new JButton("Add");
		btnNewButton_1.setBounds(250, 75, 63, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(250, 103, 63, 23);
		contentPane.add(btnNewButton_2);*/
	}
}
