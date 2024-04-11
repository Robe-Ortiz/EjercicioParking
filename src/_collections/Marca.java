package _collections;

public enum Marca {
	ME("Mercedes"), VO("Volvo"), SE("Seat"), BM("BMW"), WV("Volkswagen"),;

	private String nombreCompletoMarca;

	private Marca(String nombreCompletoMarca) {
		this.nombreCompletoMarca = nombreCompletoMarca;
	}

	public String getNombreCompletoMarca() {
		return nombreCompletoMarca;
	}
}
