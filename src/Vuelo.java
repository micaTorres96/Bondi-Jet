
public abstract class Vuelo {
	private String aeropuertoSalida;
	private String aeropuertoLlegada;
	private String fecha;
	private Integer pasajeros;
	
	private static int codigoDeVuelo = 1;
	
	public Vuelo(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros) {
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.fecha = fecha;
		this.pasajeros = pasajeros;

	}
	public String aeropuertoLlegada() {
		return aeropuertoLlegada;
	}	
	
	public String aeropuertoSalida() {
		return aeropuertoSalida;
	}
	public String fecha() {
		return fecha;
	}
	
	public String generarTipoDeVuelo(String tipo) {
		return (codigoDeVuelo++) + "-" + tipo;
	}
		
	public int decrementarCodigoVuelo() {
		if(codigoDeVuelo <= 0) {
			throw new RuntimeException("No hay mas vuelos disponibles registrados");
		}
		return codigoDeVuelo--;
	}
	
	public abstract String obtenerTipoDeVuelo();
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" -Origen: ").append(aeropuertoSalida);
		sb.append(" -Destino: ").append(aeropuertoLlegada);
		sb.append(" -Fecha: ").append(fecha);
		sb.append(" -Pasajeros: ").append(pasajeros);
		return sb.toString();
	}	
}
