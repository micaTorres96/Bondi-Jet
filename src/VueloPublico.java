import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class VueloPublico extends Vuelo {
	private double valorRefrigerio;
	private double[] precios;
	private int[] cantAsientos;	
	private Map<Integer, String> asientosDisponibles;
	
	//se agrego una lista de dni
	private List<Integer> pasajerosDni;
	
	private static int codigoPasaje = 1;
	
	public VueloPublico(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros, double valorRefrigerio, double[] precios, int[] cantAsientos) {
		super(aeropuertoSalida, aeropuertoLlegada, fecha, pasajeros);
		this.valorRefrigerio = valorRefrigerio;
		this.precios = precios;
		this.cantAsientos = cantAsientos;
		this.asientosDisponibles = new HashMap<>();
		this.pasajerosDni = new ArrayList<>();
		inicializarAsientos();
	}
	
	public List<String> reubicarPasajeros(List<Integer> pasajerosDelVuelo, Map<Integer, Cliente> pasajeros, Aeropuerto aeropuertoDestinoCancelado) {
	    List<String> datosPasajeros = new ArrayList<>();
	    
	    // Reubicar pasajeros
	    for (Integer dni : pasajerosDelVuelo) {
	        Cliente pasajero = pasajeros.get(dni);
	        boolean reubicado = false;

	        Iterator<Map.Entry<Integer, String>> iterAsientos = listaAsientosDisponibles().entrySet().iterator();
	        while(iterAsientos.hasNext()) {
	        	Map.Entry<Integer, String> asiento = iterAsientos.next();
	            int nroAsiento = asiento.getKey();
	            registrarPasaje(pasajero, nroAsiento, true, aeropuertoDestinoCancelado);
	            marcarAsientoOcupado(nroAsiento);

	            datosPasajeros.add(dni + " - " + pasajero.nombre() + " - " + pasajero.telefono() + " - " + generarCodigoVuelo());
	            reubicado = true;
	            break; // Terminamos de buscar asiento para este pasajero
	        }

	        // Si no hay asiento disponible
	        if (!reubicado) {
	            datosPasajeros.add(dni + " - " + pasajero.nombre() + " - " + pasajero.telefono() + " - CANCELADO");
	        	}
	        }
	    return datosPasajeros;
	}


	public int registrarPasaje(Cliente cliente, int nroAsiento, boolean aOcupar, Aeropuerto aeropuertoDestino) {
		if(!asientosDisponibles.containsKey(nroAsiento)) {
			throw new RuntimeException("Asiento no disponible");
		}
		//marcamos el asiento como ocupado
		if(aOcupar) {
			marcarAsientoOcupado(nroAsiento);
		}
		
		//calcular el costo
		double monto = valorPasaje(nroAsiento);
		aeropuertoDestino.agregarRecaudacion(monto);
		
		//se agregan los dni de los pasajeros
		pasajerosDni.add(cliente.documento());
		return generarCodigoPasaje(); //Se genera un codigo de pasaje al registrar uno 
	}
		
	
	public List<Integer> pasajerosDni(){
		return new ArrayList<>(pasajerosDni);
	}
	
	public int generarCodigoPasaje() {
		return codigoPasaje++;
	}
	
	public String generarCodigoVuelo() {
		return super.generarTipoDeVuelo("PUB");
	}
	
	public void inicializarAsientos(){
		 for (int asiento = 1; asiento <= cantAsientos[0]; asiento++) {
			 asientosDisponibles.put(asiento, "Turista");
	        }

	     // Asientos en clase Ejecutivo (desde cantAsientos[0] + 1 hasta cantAsientos[0] + cantAsientos[1])
	     for (int asiento = cantAsientos[0] + 1; asiento <= cantAsientos[0] + cantAsientos[1]; asiento++) {
	    	 asientosDisponibles.put(asiento, "Ejecutivo");
	    	 }
	     // Asientos de clase Primera (solo para vuelos internacionales)
	     if (cantAsientos.length > 2) { // Solo si hay una tercera clase (Primera)
	         for (int asiento = cantAsientos[0] + cantAsientos[1] + 1; asiento <= cantAsientos[0] + cantAsientos[1] + cantAsientos[2]; asiento++) {
	             asientosDisponibles.put(asiento, "Primera");
	         }
	     }
	}
	
	//cambio para no producir aliasing, comentario de profe
	//se crea una nueva coleccion, con los mismos datos y  se trabaja sobre la misma 
	public Map<Integer, String> listaAsientosDisponibles(){
		return new HashMap<>(asientosDisponibles);
	}
	
	public void marcarAsientoOcupado(int nroAsiento) {
		if (asientosDisponibles.containsKey(nroAsiento))
			asientosDisponibles.put(nroAsiento, "OCUPADO");
	}
	
	public void liberarAsiento(int nroAsiento) {
		String clase ="";
		if (asientosDisponibles.containsKey(nroAsiento))
			clase = determinarClaseAsiento(nroAsiento);
			asientosDisponibles.put(nroAsiento, clase);
	}
	
    private String determinarClaseAsiento(int nroAsiento) {
        if (nroAsiento <= cantAsientos[0]) {
            return "Turista";
        } else if (nroAsiento <= cantAsientos[0] + cantAsientos[1]) {
            return "Ejecutivo";
        } else {
            return "Primera";
        }
    }
    
	public double valorPasaje(int nroAsiento) {
		double precioBase = 0;
		
		if(nroAsiento <= cantAsientos[0]) {
			precioBase = precios[0];
		}
		else if(nroAsiento <= cantAsientos[0] + cantAsientos[1]) {
			precioBase = precios[1];
		}
		else {
			precioBase = precios[2];
		}
		
		double precioConRefrigerio = precioBase + valorRefrigerio;
		return precioConRefrigerio;  
	}
    
	public double valorRefrigerio() {
		return valorRefrigerio;
	}

	public String aeropuertoLlegada() {
		return super.aeropuertoLlegada();
	}
/*Metodos para poder visualizar en toString*/	
	
	public String mostrarPrecios() {
		StringBuilder pre = new StringBuilder();
		for(double precio :  precios)
			pre.append(" $").append(precio);
		return pre.toString();
	}

	public String mostrarAsientos() {
		StringBuilder as = new StringBuilder();
		for(int asiento :  cantAsientos)
			as.append(" |").append(asiento).append("|");
		return as.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" -Valor Refrigerio: ").append(valorRefrigerio);
		sb.append(" -Precios: ").append(mostrarPrecios());
		sb.append(" -Cantidad De Asientos: ").append(mostrarAsientos());
		return sb.toString(); 
	}
}
