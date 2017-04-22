
// Program used the functionality of the Garage class 

public class GarageTest
{
	public static void main (String [] args) 
   {
		Garage g1 = new Garage();
		System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB445"));
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA345"));
      System.out.println(g1);
      System.out.println("WEB445 departs after " + g1.departure("WEB445") + " car(s) moved");
      
		g1 = new Garage("parking.txt");
      System.out.println("Number parked: " + g1.numberParked());
      System.out.println("Number waiting: " + g1.numberWaiting());
      System.out.println("Parking WEB445 ... " + g1.arrival("WEB445"));
      System.out.println("Parking BEA345 ... " + g1.arrival("BEA345"));
      System.out.println(g1);
      System.out.println("Z23YTU departs after " + g1.departure("Z23YTU") + " car(s) moved");
      System.out.println(g1);

	}
}