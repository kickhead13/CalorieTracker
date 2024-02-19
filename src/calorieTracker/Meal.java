package calorieTracker;

import java.util.ArrayList;
import java.util.List;

public class Meal {
	
	public List<Ingredient> ingredients;
	public String name;
	public Integer id;
	public  String ownerUsername;
	
	public Meal() {
		ingredients = new ArrayList<>();
		name = "nothing";
		ownerUsername = "nobody";
	}
	
	public Ingredient total() {
		Ingredient total = new Ingredient();
		for(Ingredient el : ingredients) {
			total.add(el);
		}
		return total;
	}
	
	@Override
	public String toString() {
		return "mealName:" + name + "; mealId: " + id + "; " + ownerUsername + ": \n" + ingredients + "\n" + this.total().fullPrint();
	}
	
}
