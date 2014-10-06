package pack;

public class DonutFactory {
	public static Donut makeDonut(boolean hasSprinkles, String frosting){
		return new Donut(hasSprinkles, frosting);
	}
}
