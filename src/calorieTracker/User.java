package calorieTracker;

import java.sql.*;

public class User {
	
	String username;
	String email;
	private Boolean adminKey;
	private Boolean allowedToRequest;
	
	public User() {
		username = "nobody";
		email = "nobody";
		adminKey = false;
		allowedToRequest = true;
	}
	
	public void loginWithUsername(String username, String password) {
		try {
			Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			ResultSet set = conn.createStatement().executeQuery("select * from users where username = \'" + username + "\'");

			set.next();
			
			String dbPassword = set.getString("password");
			
			if(!password.equals(dbPassword))
				throw new Exception("PAROLA NU ESTE CORECTA PENTRU UTILIZATORUL: \'" + username + "\'");
			
			this.adminKey = set.getBoolean("admin");
			this.username = username;
			this.email = set.getString("email");
			this.allowedToRequest = set.getBoolean("allowedToRequest");
			
			set.close();
            conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginWithEmail(String email, String password) {
		try {
			Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			ResultSet set = conn.createStatement().executeQuery("select * from users where email = \'" + email + "\'");
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
	
	public void signUp(String email, String username, String password) {
		try {
			Connection conn = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
            
			PreparedStatement prepStat 
                = conn.prepareStatement("INSERT INTO USERS (username, email, password, admin) VALUES (?, ?, ?, ?)");
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
			PreparedStatement prepStat = sqlConnection.prepareStatement(
					"UPDATE users SET allowedToRequest=? WHERE username=?"
					);
			prepStat.setBoolean(1, !this.allowedToRequest);
			prepStat.setString(2, username);
			prepStat.executeUpdate();
			
			prepStat.close();
			sqlConnection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void requestNewIngredient(String nameOfNewIngredient) throws Exception {
		System.out.println(this.allowedToRequest);
		if(!this.allowedToRequest) 
			throw new Exception("UTILIZATORULUI NU II ESTE PERMIS SA FACA REQUEST-URI");
		try {
			Connection sqlConnection = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/calorietracker",
	                "root", "ALEX731321");
			PreparedStatement prepStat = sqlConnection.prepareStatement(
					"INSERT INTO requests (nameOfIngredient, usernameOfRequester, acceptedRejectedPending)" +
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
	
	@Override
	public String toString() {
		return "Username: " + username + "; Email: " + email + "; AdminKey: " + adminKey + ";";
	}
	
}
