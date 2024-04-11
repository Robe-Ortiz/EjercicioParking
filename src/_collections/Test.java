package _collections;

public class Test {

	public static void main(String[] args) {
		
		
		Coche coche1 = new Coche(Marca.ME,Color.AZ);
		Coche coche2 = new Coche(Marca.ME,Color.RO);
		Coche coche3 = new Coche(Marca.ME,Color.RO);
		Coche coche4 = new Coche(Marca.BM,Color.AM);
		Coche coche5 = new Coche(Marca.BM,Color.AM);	
		Parking pk1 = new Parking("Parking Java",5);
		


		pk1.entraCoche(coche1);
		pk1.entraCoche(coche2);
		pk1.entraCoche(coche3);
		pk1.entraCoche(coche4);
		pk1.entraCoche(coche5);
		pk1.saleCoche(coche4);
		
		pk1.reportColores();
		
		
	

		
	

		
		
	}
}
