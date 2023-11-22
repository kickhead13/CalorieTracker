package calorieTracker;

import java.sql.*;

public class Ingredient {

	public String name;
	public Integer per;
	public Integer totalFat;
	public Integer saturatedFat;
	public Integer calories;
	public Integer transFat;
	public Integer cholesterol;
	public Integer sodium;
	public Integer totalCarbs;
	public Integer dietaryFiber;
	public Integer totalSugars;
	public Integer protein;
	public Integer vitaminD;
	public Integer calcium;
	public Integer iron;
	public Integer potassium;
	
	public Ingredient() {
		
		this.name = "nothing";
		this.per = 100;
		this.calcium = 0;
		this.calories = 0;
		this.cholesterol= 0;
		this.dietaryFiber = 0;
		this.iron = 0;
		this.potassium = 0;
		this.protein = 0;
		this.saturatedFat = 0;
		this.sodium = 0;
		this.totalCarbs = 0;
		this.totalFat = 0;
		this.totalSugars = 0;
		this.transFat = 0;
		this.vitaminD = 0;
		
	}
	
	public void addNewIngredient(Connection sqlConnection) throws Exception {
		PreparedStatement prepStat = sqlConnection.prepareStatement(
				"INSERT INTO ingredients (name, calories, totalFat, saturatedFat, transFat, cholesterol, sodium, totalCarbs, dietaryFiber, totalSugars, protein, vitaminD, calcium, iron, potassium)" + 
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
				);
		prepStat.setString(1, name);
		prepStat.setInt(2, calories);
		prepStat.setInt(3, totalFat);
		prepStat.setInt(4, saturatedFat);
		prepStat.setInt(5, transFat);
		prepStat.setInt(6, cholesterol);
		prepStat.setInt(7, sodium);
		prepStat.setInt(8, totalCarbs);
		prepStat.setInt(9, dietaryFiber);
		prepStat.setInt(10, totalSugars);
		prepStat.setInt(11, protein);
		prepStat.setInt(12, vitaminD);
		prepStat.setInt(13, calcium);
		prepStat.setInt(14, iron);
		prepStat.setInt(15, potassium);
		//prepStat.setInt(0, 0);
		prepStat.executeUpdate();
		
		prepStat.close();
	}
	
}
