package calorieTracker;

import java.sql.*;
import java.util.*;

public class User {
	
	public String username;
	public String email;
	public Connection conn;
	public Boolean adminKey;
	private Boolean allowedToRequest;
	
	/**
	 * logs user in as \'nobody\' account
	 */
	public User() {
		try {
		username = "nobody";
		email = "nobody";
		adminKey = false;
		allowedToRequest = true;
		conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calorietracker",
                "root", "ALEX731321");
		}catch(Exception e) {}
	}
	
	public Boolean getAdminKey() {
		return this.adminKey;
	}
	
	public void showAllAvailableIngredients() throws Exception {
		//Connection conn = DriverManager.getConnection(
        //        "jdbc:mysql://localhost:3306/calorietracker",
        //        "root", "ALEX731321");
		ResultSet set = conn.createStatement().executeQuery(
				"select * from ingredients;"
                );
		Ingredient toPrint = new Ingredient();
		while(set.next()) {
			toPrint.readIngredient(set, 100);
			System.out.println(toPrint);
		}
	}
	
	/**
	 * logs user in using an username and a password
	 * @param username the username of the user we want to log in as
	 * @param password the password that should match the password of the aforementioned user
	 */
	public void loginWithUsername(String username, String password) throws Exception  {
			//Connection conn = DriverManager.getConnection(
	        //        "jdbc:mysql://localhost:3306/calorietracker",
	        //        "root", "ALEX731321");
			ResultSet set = conn.createStatement().executeQuery("select * from users where username = \'"
	                + username + "\'"
	                );
			
			set.next();
			
			String dbPassword = set.getString("password");
			
			if(!password.equals(dbPassword))
				throw new Exception("PAROLA NU ESTE CORECTA PENTRU UTILIZATORUL: \'" + username + "\'");
			
			this.adminKey = set.getBoolean("admin");
			this.username = username;
			this.email = set.getString("email");
			this.allowedToRequest = set.getBoolean("allowedToRequest");
			
			set.close();
            //conn.close();
	}
	
	
	/**
	 * logs user in using an e-mail and a password
	 * @param email the email of the user we want to log in as
	 * @param password the password that should match the password of the aforementioned user
	 */
	public void loginWithEmail(String email, String password) {
		try {
			ResultSet set = conn.createStatement().executeQuery("select * from users where email = \'"
	                + email + "\'"
	                );
			set.next();
			
			String dbPassword = set.getString("password");
			
			if(!password.equals(dbPassword))
				throw new Exception("PAROLA NU ESTE CORECTA PENTRU UTILIZATORUL: \'" + username + "\'");
			
			this.adminKey = set.getBoolean("admin");
			this.username = set.getString("username");
			this.email = email;
			this.allowedToRequest = set.getBoolean("allowedToRequest");
			
			set.close();
            conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * creates a new user account with the following data
	 * @param email an e-mail for the new account 
	 * @param username an username for the new account
	 * @param password a password for the new account
	 */
	public void signUp(String email, String username, String password) {
		try {
			//Connection conn = DriverManager.getConnection(
	         //       "jdbc:mysql://localhost:3306/calorietracker",
	         //       "root", "ALEX731321");
            
			PreparedStatement prepStat 
                = conn.prepareStatement("INSERT INTO USERS (username, email, password, admin)"+
                		"VALUES (?, ?, ?, ?)"
                		);
			
            prepStat.setString(1, username);
            prepStat.setString(2, email);
            prepStat.setString(3, password);
            prepStat.setInt(4, 0);
            prepStat.executeUpdate();
            
            prepStat.close();
            conn.close();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * gives admin access to user account with username as username
	 * @param username username of the account to which we want to grant admin access
	 * @throws Exception if non-admin tries to grant admin access to other non-admin we throw an exception
	 */
	public void giveAdminAccesToUser(String username) throws Exception {
		if(!this.adminKey)
			throw new Exception("UTILIZATORUL NU ARE ACCES LA CHEIE DE ADMINISTRATOR");
		try {
			Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
            
			PreparedStatement prepStat 
                = conn.prepareStatement("UPDATE users SET admin = true WHERE username = ?");
            prepStat.setString(1, username);
            prepStat.executeUpdate();
            
            prepStat.close();
            conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * pulls granted admin access from a given user
	 * @param username the username of the account from which we want to pull admin access
	 * @throws Exception if non-admin tries to pull admin access from an admin, or if an admin tries to pull admin access from user \'root\' we throw and exception
	 */
	public void pullAdminAccessFromUser(String username) throws Exception {
		if(!this.adminKey)
			throw new Exception("UTILIZATORUL NU ARE ACCES LA CHEIA DE ADMINISTRATOR");
		if(username.equals("root"))
			throw new Exception("NU SE POATE CONFISCA CHEIA DE ADMINISTRATOR AL ROOT-ULUI");
		try {
			Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
            
			PreparedStatement prepStat 
                = conn.prepareStatement("UPDATE users SET admin = false WHERE username = ?");
            prepStat.setString(1, username);
            prepStat.executeUpdate();
            
            prepStat.close();
            conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * flips username's allowedToRequest boolean value.
	 * allowedToRequest determines whether a user can or not make ingredient requests
	 * @param username the user whose allowedToRequest value we are going to flip
	 * @throws Exception when non-admin attempts to flip allowedToRequest value
	 */
	public void flipAllowedToRequestState(String username) throws Exception {
		if(!this.adminKey)
			throw new Exception("UTILIZATORUL NU ESTE IN POSESIA CHEIEI DE ADMINISTRATOR");
		try {
			Connection sqlConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			
			ResultSet set = sqlConnection.createStatement().executeQuery(
					"select * from users where username=\'" + username + "\';"
					);
			set.next();
			Boolean request = set.getBoolean("allowedToRequest");
			set.close();
			
			PreparedStatement prepStat = sqlConnection.prepareStatement(
					"UPDATE users SET allowedToRequest=? WHERE username=?"
					);
			prepStat.setBoolean(1, (request ? false : true) );
			prepStat.setString(2, username);
			prepStat.executeUpdate();
			
			prepStat.close();
			sqlConnection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * adds new ingredients to calorietracker.ingredients table
	 * @param newIngredient new ingredient to be added
	 * @throws Exception if non-admin attempts to add new ingredient to the data base
	 */
	public void addNewIngredient(Ingredient newIngredient) throws Exception {
		if(!this.adminKey)
			throw new Exception("DOAR UTILIZATORUL ADMIN POATE ADAUGA NOI INGREDIENTE");
		try {
			Connection sqlConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			newIngredient.addNewIngredient(sqlConnection);
			sqlConnection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to request a new ingredient to be added to the ingredient table
	 * @param nameOfNewIngredient what new ingredient is desired
	 * @throws Exception if user is not allowed to request new ingredients it'll throw an Exception
	 */
	public void requestNewIngredient(String nameOfNewIngredient) throws Exception {
		//System.out.println(this.allowedToRequest);
		if(!this.allowedToRequest) 
			throw new Exception("UTILIZATORULUI NU II ESTE PERMIS SA FACA REQUEST-URI");
		try {
			Connection sqlConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			PreparedStatement prepStat = sqlConnection.prepareStatement(
					"INSERT INTO requests (nameOfIngredient, usernameOfRequester, acceptedRejectedPending)"
					+
					"VALUES (?, ?, ?)"
					);
			prepStat.setString(1, nameOfNewIngredient);
			prepStat.setString(2, this.username);
			prepStat.setInt(3, 3);
			
			//1 = ACCEPTED
			//2 = REJECTED
			//3 = PENDING
			
			prepStat.executeUpdate();
			prepStat.close();
			sqlConnection.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMealFromUser(Integer mealId) throws Exception {
		Connection sqlConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calorietracker",
                "root", "ALEX731321");
		PreparedStatement prepStat = sqlConnection.prepareStatement(
				"delete from ingredientsofmeals where mealId = ?;"
				);
		prepStat.setInt(1, mealId);
		prepStat.executeUpdate();
		
		prepStat.close();
		
		prepStat = sqlConnection.prepareStatement(
				"delete from meals where id = ?;"
				);
		prepStat.setInt(1, mealId);
		prepStat.executeUpdate();
		
		prepStat.close();
		sqlConnection.close();
	}
	
	public List<Meal> getMeals() throws Exception {
		
		List<Meal> toBeReturned = new ArrayList<>();

		Connection sqlConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calorietracker",
                "root", "ALEX731321");
		
		ResultSet mealSet = sqlConnection.createStatement().executeQuery(
				"select * from meals where owner = \'" + this.username + "\';" 
				);
		while(mealSet.next()) {
			//System.out.println("alex");
			Meal thisOne = new Meal();
			thisOne.id = mealSet.getInt("id");
			thisOne.name = mealSet.getString("mealName");
			thisOne.ownerUsername = this.username;
			thisOne.ingredients = new ArrayList<>();
			toBeReturned.add(thisOne);

		}
		
		mealSet.close();
		
		for(Meal element : toBeReturned) {
			
			ResultSet ingredients = sqlConnection.createStatement().executeQuery(
					"select * from ingredientsofmeals where mealId = " + element.id + ";"
					);
			List<Integer> quantities = new ArrayList<>();
			while(ingredients.next()) {
				Ingredient thisOne = new Ingredient();
				thisOne.id = ingredients.getInt("ingredientId");
				element.ingredients.add(thisOne);
				quantities.add(ingredients.getInt("quantity"));
			}
			
			ingredients.close();
			
			Integer iter = 0;
			for(Ingredient ingredient : element.ingredients) {
				ResultSet ingrediente = sqlConnection.createStatement().executeQuery(
						"select * from ingredients where id = " + ingredient.id + ";"
						);
				ingrediente.next();
				ingredient.readIngredient(ingrediente, quantities.get(iter));
				iter++;
				ingredients.close();
			}
			
		}
		sqlConnection.close();
		return toBeReturned;
	}
	
	/**
	 * creates new meal and adds it to the meals table
	 * @param mealName what name should be given to the meal
	 */
	public void createNewMeal(String mealName) {
		try {
			Connection sqlConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			PreparedStatement prepStat = sqlConnection.prepareStatement(
					"INSERT INTO meals (mealName, owner)" +
					"VALUES (?, ?)"
					);
			prepStat.setString(1, mealName);
			prepStat.setString(2, this.username);
			prepStat.executeUpdate();
			
			prepStat.close();
			sqlConnection.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * adds specified ingredient to specified meal
	 * @param mealId meal to which we want to add ingredient
	 * @param ingredientId ingredient to be added to the meal
	 * @param quantity quantity of the ingredient
	 */
	public void addIngredientToMeal(Integer mealId, Integer ingredientId, Integer quantity) {
		try {
			Connection sqlConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			ResultSet set = sqlConnection.createStatement().executeQuery(
					"select * from ingredients where id=" + ingredientId + ";"
					);
			if(!set.next()) {
				set.close();
				sqlConnection.close();
				return;
			}
			set.close();
			PreparedStatement prepStat = sqlConnection.prepareStatement(
					"INSERT INTO ingredientsOfMeals (ingredientId, mealId, quantity)" +
					"VALUES (?,?,?)"
					);
			prepStat.setInt(1, ingredientId);
			prepStat.setInt(2, mealId);
			prepStat.setInt(3, quantity);
			prepStat.executeUpdate();
			
			prepStat.close();
			sqlConnection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Username: " + username + "; Email: " + email + "; AdminKey: " + adminKey + ";";
	}
	
}
