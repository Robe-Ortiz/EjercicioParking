package _collections;

import java.util.Objects;
import java.util.Random;

public class Coche{

	private Marca marca;
	private Color color;

	public Coche(Marca marca, Color color) {
		this.marca = marca;
		this.color = color;
	}

	public Coche() {
		Random random = new Random();
		this.marca = Marca.values()[random.nextInt(Marca.values().length)];
		this.color = Color.values()[random.nextInt(Color.values().length)];
	}

	public Marca getMarca() {
		return marca;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return String.format("Coche: %s %s", marca, color);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(color, marca);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coche)) return false;
		
		Coche cocheCandidato = (Coche) obj;
		return color == cocheCandidato.color && marca == cocheCandidato.marca;
	}	
}
