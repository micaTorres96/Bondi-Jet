
public abstract class Vuelo {
	String aeropuertoSalida;
	String aeropuertoLlegada;
	String fecha;
	Integer pasajeros;

//	double recaudado; duda
//	double impuestos; duda
	
	public Vuelo(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros) {
		this.aeropuertoSalida = aeropuertoSalida;
		this.aeropuertoLlegada = aeropuertoLlegada;
		this.fecha = fecha;
		this.pasajeros = pasajeros;
	}
	
	public abstract void valorPasaje();

	public abstract double costoTotalVuelo();
		
	public abstract double totalRecaudadoVuelo();
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Origen: ").append(aeropuertoSalida);
		sb.append("\nDestino: ").append(aeropuertoLlegada);
		sb.append("\nfecha: ").append(fecha);
		sb.append("\nPasajeros: ").append(pasajeros).append("\n");
		return sb.toString();
	}
}
