package pack;

import java.util.ArrayList;

public class Hello {
	public static void main(String args[]){
		for (int i = 0; i < 6; i++) {
			if(i == 2){
				System.out.println("Hwuh");
			}
			else{
				System.out.println("Beans");			
			}
		}
		System.out.println("Let's make some donuts!");
		
		ArrayList<Donut> donuts = new ArrayList<Donut>();
		
		donuts.add(DonutFactory.makeDonut(true, "pink"));
		donuts.add(DonutFactory.makeDonut(false, "chocolate"));
		donuts.add(DonutFactory.makeDonut(true, "white"));
		donuts.add(DonutFactory.makeDonut(false, "no"));
		donuts.add(DonutFactory.makeDonut(true, "chocolate"));
		
		ArrayList<Donut> eatenDonuts = new ArrayList<Donut>();
		
		for (Donut donut : donuts) {
			System.out.println(donut);
			if(donut.hasSprinkles()){
				eatenDonuts.add(donut);
			}
		}
		
		for (Donut donut : eatenDonuts) {
			donuts.remove(donut);
			System.out.println("I ate a donut with sprinkles and " + donut.getFrosting() + " frosting.");
		}
		
		for (Donut donut : donuts) {
			System.out.println("I did not eat a donut with no sprinkles and " + donut.getFrosting() + " frosting.");
		}
		
		System.out.println("Roody poo jabroni.");
	}
}
