
public class VueloPrivado extends Vuelo{
	private double precio;
	private int dniComprador;
	private int[] acompaniantes;
	
	public VueloPrivado(String aeropuertoSalida, String aeropuertoLlegada, String fecha, Integer pasajeros, double precio, int dniComprador, int[] acompaniantes) {
		super(aeropuertoSalida, aeropuertoLlegada, fecha, pasajeros);
		this.precio = precio;
		this.dniComprador = dniComprador;
		this.acompaniantes = acompaniantes;
	}

	public void valorPasaje() {		
	}

	public double costoTotalVuelo() {
		return 0;
	}

	public double totalRecaudadoVuelo() {
		return 0;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nPrecio: ").append(precio);
		sb.append("\nDNI comprador: ").append(dniComprador);
		sb.append("\nAcompañantes: ").append(acompaniantes).append("\n");
		return sb.toString();
	}

}
