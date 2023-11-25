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
	public Integer id;
	
	public Ingredient() {
		
		this.id = 1;
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
	
	
	/**
	 * constructor explicit
	 * @param name 
	 * @param calories
	 * @param totalFat
	 * @param saturatedFat
	 * @param transFat
	 * @param cholesterol
	 * @param sodium
	 * @param totalCarbs
	 * @param dietaryFiber
	 * @param totalSugars
	 * @param protein
	 * @param vitaminD
	 * @param calcium
	 * @param iron
	 * @param potassium
	 */
	public Ingredient(String name, Integer calories, Integer totalFat, Integer saturatedFat,
			Integer transFat, Integer cholesterol, Integer sodium, Integer totalCarbs,
			Integer dietaryFiber, Integer totalSugars, Integer protein, Integer vitaminD,
			Integer calcium, Integer iron, Integer potassium) {
		this.name = name;
		this.calcium = calcium;
		this.calories = calories;
		this.cholesterol= cholesterol;
		this.dietaryFiber = dietaryFiber;
		this.iron = iron;
		this.potassium = potassium;
		this.protein = protein;
		this.saturatedFat = saturatedFat;
		this.sodium = sodium ;
		this.totalCarbs = totalCarbs;
		this.totalFat = totalFat;
		this.totalSugars = totalSugars;
		this.transFat = transFat;
		this.vitaminD = vitaminD;
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
	
	public void readIngredient(ResultSet set, Integer quantity) throws Exception {
		this.name = set.getString("name");
		this.id = set.getInt("id");
		this.calcium = set.getInt("calcium") * quantity/100;
		this.calories = set.getInt("calories") * quantity/100;
		this.cholesterol= set.getInt("cholesterol") * quantity/100;
		this.dietaryFiber = set.getInt("dietaryFiber") * quantity/100;
		this.iron = set.getInt("iron") * quantity/100;
		this.potassium = set.getInt("potassium") * quantity/100;
		this.protein = set.getInt("protein") * quantity/100;
		this.saturatedFat = set.getInt("saturatedFat") * quantity/100;
		this.sodium = set.getInt("sodium") * quantity/100;
		this.totalCarbs = set.getInt("totalCarbs") * quantity/100;
		this.totalFat = set.getInt("totalFat") * quantity/100;
		this.totalSugars = set.getInt("totalSugars") * quantity/100;
		this.transFat = set.getInt("transFat") * quantity/100;
		this.vitaminD = set.getInt("vitaminD") * quantity/100;
	}
	
	@Override
	public String toString() {
		return name + " " + id + "; calories: " + calories + "; totalFat: " + totalFat+ "; saturatedFat: " + saturatedFat;
 	}
	
}
