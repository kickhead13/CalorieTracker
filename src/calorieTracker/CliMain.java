package calorieTracker;

import java.util.*;
import java.sql.*;

public class CliMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Username: ");
		String username = scanner.nextLine();
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		try {
			User user = new User();
			user.loginWithUsername(username, password);
			
			if(! user.getAdminKey()) {
			
				System.out.println(
					" [1] Show meals of user\n" +
					" [2] Create new meal\n" +
					" [3] Delete meal\n" +
					" [4] Make ingredient request\n" +
					" [0] Quit\n"
					);
				System.out.print(">");
				Integer option = scanner.nextInt();
				while(option!=0) {
					switch(option) {
						case 1:
							showMealsOfUser(user);
							break;
						case 2:
							createNewMeal(user);
							break;
						case 3:
							deleteMeal(user);
							break;
						case 4:
							makeIngredientRequest(user);
							break;
						default:
							break;
					}
					System.out.println(
							"\n [1] Show meals of user\n" +
							" [2] Create new meal\n" +
							" [3] Delete meal\n" +
							" [4] Make ingredient request\n" +
							" [0] Quit\n"
							);
					System.out.print(">");
					option = scanner.nextInt();
				}
			}
			else {
				
				System.out.println(
						user.username + "\n" +
						" [1] Show meals of user\n" +
						" [2] Create new meal\n" +
						" [3] Delete meal\n" +
						" [4] Make ingredient request\n" +
						" [5] Show pending ingredient requests\n" +
						" [6] Add new ingredient to data base\n" +
						" [7] User permissions prompt\n" +
						" [0] Quit\n"
						);
				System.out.print(">");
				Integer option = scanner.nextInt();
				while(option!=0) {
					switch(option) {
					case 1:
						showMealsOfUser(user);
						break;
					case 2:
						createNewMeal(user);
						break;
					case 3:
						deleteMeal(user);
						break;
					case 4:
						makeIngredientRequest(user);
						break;
					case 5:
						showPendingRequests(user);
						break;
					case 6:
						addNewIngredientToDataBase(user);
						break;
					case 7:
						permissionPrompt(user);
						break;
					default:
						option = 0;
						break;
				}
				System.out.println(
						user.username + "\n" +
						" [1] Show meals of user\n" +
						" [2] Create new meal\n" +
						" [3] Delete meal\n" +
						" [4] Make ingredient request\n" +
						" [5] Show pending ingredient requests\n" +
						" [6] Add new ingredient to data base\n" +
						" [7] User permissions prompt\n" +
						" [0] Quit\n"
						);
				System.out.print(">");
				option = scanner.nextInt();
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	static void showPendingRequests(User user) throws Exception {
		Connection sqlConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calorietracker",
                "root", "ALEX731321");
		ResultSet set = sqlConnection.createStatement().executeQuery(
				"select * from requests where acceptedRejectedPending = 3;"
				);
		System.out.println();
		while(set.next()) {
			System.out.println(set.getString("nameOfIngredient") + "  " + set.getString("usernameOfRequester"));
		}
	}
	
	static void permissionPrompt(User user) throws Exception {
		System.out.print("Give username of user: ");
		Scanner scanner = new Scanner(System.in);
		String username = scanner.nextLine();
		
		Connection sqlConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/calorietracker",
                "root", "ALEX731321");
		
		ResultSet set = sqlConnection.createStatement().executeQuery(
				"select * from users where username = \'" + username + "\' ;"
				);
		
		set.next();
		System.out.println(set.getString("username") + "  " + set.getString("admin") + "  " + set.getString("allowedToRequest"));
		
		System.out.println("Modify permission\n" +
				"Add admin +a; Remove admin: -a; Switch request permission: r"
				);
		System.out.print(">");
		String option = scanner.nextLine();
		if(option.equals("-a")) {
			user.pullAdminAccessFromUser(username);
		}
		else if(option.equals("+a")) {
			user.giveAdminAccesToUser(username);
		}
		else if(option.equals("r")) {
			user.flipAllowedToRequestState(username);
		}
		
		set.close();
		sqlConnection.close();
	}
	
	static void makeIngredientRequest(User user) throws Exception {
		System.out.println("Name the new ingredient you would like: ");
		Scanner scanner = new Scanner(System.in);
		
		String nameOfNewIngredient = scanner.nextLine();
		user.requestNewIngredient(nameOfNewIngredient);
	}
	
	static void addNewIngredientToDataBase(User user) throws Exception {
		Ingredient toAdd = new Ingredient();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Name of ingredient: ");
		toAdd.name = scanner.nextLine();
		System.out.print("Calories of ingredient: ");
		toAdd.calories = scanner.nextInt();
		System.out.print("Total fat of ingredient: ");
		toAdd.totalFat = scanner.nextInt();
		System.out.print("Saturated fat of ingredient: ");
		toAdd.saturatedFat = scanner.nextInt();
		System.out.print("Trans fat of ingredient: ");
		toAdd.transFat = scanner.nextInt();
		System.out.print("Cholesterol of ingredient: ");
		toAdd.cholesterol = scanner.nextInt();
		System.out.print("Sodium of ingredient: ");
		toAdd.sodium = scanner.nextInt();
		System.out.print("Total carbs of ingredient: ");
		toAdd.totalCarbs = scanner.nextInt();
		System.out.print("Dietary fiber of ingredient: ");
		toAdd.dietaryFiber = scanner.nextInt();
		System.out.print("Total sugars of ingredient: ");
		toAdd.totalSugars = scanner.nextInt();
		System.out.print("Protein of ingredient: ");
		toAdd.protein = scanner.nextInt();
		System.out.print("Vitamin D of ingredient: ");
		toAdd.vitaminD = scanner.nextInt();
		System.out.print("Calcium of ingredient: ");
		toAdd.calcium = scanner.nextInt();
		System.out.print("Iron of ingredient: ");
		toAdd.iron = scanner.nextInt();
		System.out.print("Potassium of ingredient: ");
		toAdd.potassium = scanner.nextInt();
		
		user.addNewIngredient(toAdd);
		
	}
	
	static void deleteMeal(User user) throws Exception {
		List<Meal> meals = user.getMeals();
		for(Meal el : meals) 
			System.out.println(el + "\n");
		System.out.println("\n [any of the ids above] Choose meal to delete (meal id)\n" +
				" [0] Exit\n\n>"
				);
		
		Scanner scanner = new Scanner(System.in);
		Integer option = scanner.nextInt();
		
		while(option != 0) {
			user.deleteMealFromUser(option);
			meals = user.getMeals();
			for(Meal el : meals) 
				System.out.println(el + "\n");
			System.out.print("\n [any of the ids above] Choose meal to delete (meal id)\n" +
					" [0] Exit\n\n>"
					);
			option = scanner.nextInt();
			
		}
		
	}
	
	static void showMealsOfUser(User user) throws Exception {
		List<Meal> meals = user.getMeals();
		for(Meal el : meals) 
			System.out.println(el + "\n");
		
		System.out.print("\n [any of the ids above] Choose meal to add an ingredient to (meal id)\n" +
				" [0] Exit\n\n>"
				);
		
		Scanner scanner = new Scanner(System.in);
		Integer option = scanner.nextInt();
		Integer zero = 0;
		while(!option.equals(zero)) {
			addIngredientToMeal(user, option);
			System.out.println();
			meals = user.getMeals();
			for(Meal el : meals) 
				System.out.println(el + "\n");
			
			System.out.print("\n [any of the ids above] Choose meal to add an ingredient to (meal id)\n" +
					" [0] Exit\n\n>"
					);
			option = scanner.nextInt();
		}
	}
	
	static void addIngredientToMeal(User user, Integer mealId) throws Exception {
		
		System.out.print("Show available ingredients? (y/n)");
		Scanner scanner = new Scanner(System.in);
		String option = scanner.nextLine();
		
		if(option.equals("y")) {
			showAllIngredients(user);
		}
		
		System.out.print("Give ingredient id >");
		Integer ingredientId = scanner.nextInt();
		System.out.print("Give quantity of ingredient >");
		Integer quantity = scanner.nextInt();
		user.addIngredientToMeal(mealId, ingredientId, quantity);
	}
	
	static void showAllIngredients(User user) throws Exception {
		user.showAllAvailableIngredients();
	}
	
	static void createNewMeal(User user) throws Exception {
		System.out.print("\nGive a name to your new meal :");
		Scanner scanner = new Scanner(System.in);
		String mealName = scanner.nextLine();
		user.createNewMeal(mealName);
	}
}
