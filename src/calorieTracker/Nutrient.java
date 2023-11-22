package calorieTracker;

public class Nutrient {
	
	public Integer grams;
	public Integer percentageOfDailyValue;
	
	public Nutrient() {
		grams = 0;
		percentageOfDailyValue = 0;
	}
	
	public Nutrient(Integer grams, Integer percentage) {
		this.grams = grams;
		this.percentageOfDailyValue = percentage;
	}
	
	@Override
	public String toString() {
		return grams + "   " + percentageOfDailyValue;
	}
	
	public Integer getGrams() {
		return this.grams;
	}
	
	public Integer getPercentageOfDailyValue() {
		return this.percentageOfDailyValue;
	}
	
	public void setGrams(Integer grams) {
		this.grams = grams;
	}
	
	public void setPercentageOfDailyValue(Integer percentage) {
		this.percentageOfDailyValue = percentage;
	}
	
}
