
public abstract class VueloPublico extends Vuelo {
	private double valorRefrigerio;
	private double[] precios;
	private int[] cantAsientos;
	
	public VueloPublico(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros, double valorRefrigerio, double[] precios, int[] cantAsientos) {
		super(aeropuertoSalida, aeropuertoLlegada, fecha, pasajeros);
		this.valorRefrigerio = valorRefrigerio;
		this.precios = precios;
		this.cantAsientos = cantAsientos;
	}
	
	public abstract void valorPasaje();

	public abstract double costoTotalVuelo();
		
	public abstract double totalRecaudadoVuelo();
	
	public abstract int asientosDisponibles();
	
	public abstract void reprogramarVuelo();	

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nValor Refrigerio: ").append(valorRefrigerio);
		sb.append("\nPrecios: ").append(precios);
		sb.append("\nCantidad De Asientos: ").append(cantAsientos).append("\n");
		return sb.toString();
	}
}
