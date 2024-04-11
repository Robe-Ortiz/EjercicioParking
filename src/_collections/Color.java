package _collections;

public enum Color {
	RO("Rojo"), NA("Naranja"), AM("Amarillo"), VE("Verde"), AZ("Azul"), IN("Índigo"), VI("Violeta"),;

	private String nombreCompletoColor;

	private Color(String nombreCompletoColor) {
		this.nombreCompletoColor = nombreCompletoColor;
	}

	public String getNombreCompletoColor() {
		return nombreCompletoColor;
	}
}
