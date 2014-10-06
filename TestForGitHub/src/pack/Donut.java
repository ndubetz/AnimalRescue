package pack;

public class Donut {
	private boolean hasSprinkles;
	private String frosting;
	
	public Donut(boolean hasSprinkles, String frosting){
		this.hasSprinkles = hasSprinkles;
		this.frosting = frosting;
	}
	
	public boolean hasSprinkles(){
		return this.hasSprinkles;
	}
	
	public String getFrosting(){
		return this.frosting;
	}
	
	public String toString(){
		return "This donut has " + this.frosting + " and is " + this.hasSprinkles + " for sprinkles.";
	}
}
