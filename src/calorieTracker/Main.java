package calorieTracker;
import java.sql.*;

public class Main {
	public static void main(String[] args) {
		User test = new User();
		//test.signUp("alexandru.ana03@e-uvt.ro", "alexandruana", "test1234");
		test.loginWithUsername("kickhead13", "Tomi1993");
		//test.loginWithUsername("root", "ALEX731321");
		try {
			test.requestNewIngredient("Pui");
			//test.flipAllowedToRequestState("kickhead13");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//System.out.println(test);
		
		//test.loginWithUsername("kickhead13", "Tomi1993");
		//System.out.print(test);
		
		/* Connection connection = null;
		        try {
		            // below two lines are used for connectivity.
		            Class.forName("com.mysql.cj.jdbc.Driver");
		            connection = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/test",
		                "root", "ALEX731321");
		 
		            // mydb is database
		            // mydbuser is name of database
		            // mydbuser is password of database
		 
		            Statement statement;
		            statement = connection.createStatement();
		            ResultSet resultSet;
		            resultSet = statement.executeQuery(
		                "select * from test2");
		            int code;
		            String title;
		            while (resultSet.next()) {
		                code = resultSet.getInt("Id");
		                title = resultSet.getString("name").trim();
		                System.out.println("Code : " + code
		                                   + " Title : " + title);
		            }
		            resultSet.close();
		            statement.close();
		            connection.close();
		        }
		        catch (Exception exception) {
		            System.out.println(exception);
		        }*/
	}
}
