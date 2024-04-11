package _collections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Parking {

	private String nombre;
	private int totalPlazas;
	private List<Coche> listaDeCoches = new LinkedList<>();
	private Map<Color, Integer> mapaDeColores = new HashMap<>();
	private Map<Marca, Integer> mapaDeMarcas = new HashMap<>();
	private Map<Coche, Integer> mapaDeCochesIguales = new HashMap<>();
	private Set<Coche> setDeCoches = new HashSet<>();
	private static Random random = new Random();

	public Parking(String nombre, int totalPlazas) {
		this.nombre = nombre;
		this.totalPlazas = totalPlazas;
	}

	private boolean estaElParkingLleno() {
		return listaDeCoches.size() == totalPlazas;	
	}

	private boolean estaElParkingVacio() {
		return listaDeCoches.isEmpty();	
	}

	// INCLUIR COCHE
	private void sumarColorAlParking(Coche coche) {	
		 mapaDeColores.compute(coche.getColor(), (color, cantidad) -> cantidad == null ? 1 : cantidad + 1);
	}

	private void sumarMarcaAlParking(Coche coche) {
		mapaDeMarcas.compute(coche.getMarca(),(marca, valor)-> valor ==null? 1 : valor + 1);
	}

	private void sumarCochesIguales(Coche coche) {
		mapaDeCochesIguales.compute(coche, (key,value)->value==null?1:value+1);
	}

	private void sumarAlSetDeCoches(Coche coche) {
		for (Coche cocheAlmacenado : setDeCoches) {
			if (cocheAlmacenado == coche) {
				return;
			}
		}
		setDeCoches.add(coche);
	}

	public boolean entraCoche(Coche coche) {
		if (estaElParkingLleno()) {
			return false;
		}

		for (Coche cocheEnElParking : listaDeCoches) {
			if (cocheEnElParking == coche) {
				return false;
			}
		}
		sumarColorAlParking(coche);
		sumarMarcaAlParking(coche);
		sumarCochesIguales(coche);
		sumarAlSetDeCoches(coche);
		listaDeCoches.add(coche);
		return true;
	}

	// SACAR COCHE
	private void restarColorDelParking(Coche coche) {
		mapaDeColores.computeIfPresent(coche.getColor(), (key, value) -> {
			int nuevaCantidad = value -1;
			return nuevaCantidad == 0 ? null : nuevaCantidad;
		});
	}

	private void restarMarcaDelParking(Coche coche) {
		mapaDeMarcas.computeIfPresent(coche.getMarca(), (key,value)->{
			int nuevaCantidad = value - 1;
			return nuevaCantidad == 0 ? null : nuevaCantidad;
		});
	}

	private void restarCochesIguales(Coche coche) {
		mapaDeCochesIguales.computeIfPresent(coche, (key,value)->{
			int nuevaCantidad = value -1;
			return nuevaCantidad == 0 ? null : nuevaCantidad;
		});
	}

	private void restarAlSetDeCoches(Coche coche) {
		setDeCoches.remove(coche);
	}

	public boolean saleCoche(Coche coche) {
		if (!listaDeCoches.contains(coche))
			return false;

		restarColorDelParking(coche);
		restarMarcaDelParking(coche);
		restarCochesIguales(coche);
		restarAlSetDeCoches(coche);
		listaDeCoches.remove(coche);
		return true;
	}

	public boolean saleCocheAleatorio() {
		if (estaElParkingVacio()) {
			return false;
		}
		Coche cocheHaBorrar = listaDeCoches.remove(random.nextInt(listaDeCoches.size()));
		listaDeCoches.remove(cocheHaBorrar);
		restarColorDelParking(cocheHaBorrar);
		restarMarcaDelParking(cocheHaBorrar);
		restarCochesIguales(cocheHaBorrar);
		restarAlSetDeCoches(cocheHaBorrar);
		return true;
	}

	public boolean vaciaParking() {
		if (estaElParkingVacio())
			return false;
		for (int i = listaDeCoches.size(); i > 0; i--) {
			listaDeCoches.remove(i - 1);
		}
		mapaDeColores.clear();
		mapaDeMarcas.clear();
		mapaDeCochesIguales.clear();
		setDeCoches.clear();
		return true;
	}

	// IMPRIMIR REPORTS
	private void imprimirCabecera(String texto) {
		String cabecera = texto;
		System.out.println(cabecera);
		for (int i = 0; i < cabecera.length(); i++) {
			System.out.print("-");
		}
		System.out.printf("\n%s: %s\n", getClass().getSimpleName(),nombre);
	}

	public void reportParking() {
		imprimirCabecera("LISTADO COCHES");

		for (Coche coche : listaDeCoches) {
			System.out.printf("Coche: %s %s.\n", coche.getMarca(), coche.getColor());
		}
		System.out.printf("\nTotal de coches: %d, plazas libres: %d.", listaDeCoches.size(),
				totalPlazas - listaDeCoches.size());
	}

    public void reportColores() {
        imprimirCabecera("REPORT DE COLORES");
        int totalCoches = mapaDeColores.values().stream().mapToInt(Integer::intValue).sum();       
        mapaDeColores.forEach((color, cantidad) -> System.out.printf("El coche de color %s se repite %d ve%s.\n",
                color.getNombreCompletoColor(), cantidad, cantidad == 1 ? "z" : "ces"));
        System.out.printf("Total coches: %d", totalCoches);
    }

    public void reportMarcar() {
        imprimirCabecera("REPORT DE MARCAS");
        int totalCoches = mapaDeMarcas.values().stream().mapToInt(Integer::intValue).sum();
        mapaDeMarcas.forEach((marca, cantidad) -> System.out.printf("El coche de marca %s se repite %d ve%s.\n",
                marca.getNombreCompletoMarca(), cantidad, cantidad == 1 ? "z" : "ces"));
        System.out.printf("Total coches: %d", totalCoches);
    }
    
    public void reportCochesIguales() {
        imprimirCabecera("REPORT DE COCHES IGUALES");
        int totalCoches = mapaDeCochesIguales.values().stream().mapToInt(Integer::intValue).sum();
        mapaDeCochesIguales.forEach((coche, cantidad) -> System.out.printf("Coche: %s %s se repite %d ve%s.\n",
                coche.getMarca().getNombreCompletoMarca(), coche.getColor().getNombreCompletoColor(),cantidad, cantidad == 1 ? "z" : "ces"));
        System.out.printf("Total coches: %d", totalCoches);
    }

    public void reportSetCoches() {
        imprimirCabecera("REPORT SET DE COCHES");
        setDeCoches.forEach(coche -> System.out.printf("Coche: %s %s\n", coche.getMarca(), coche.getColor()));
        System.out.printf("Total items: %d", setDeCoches.size());
    }
}
