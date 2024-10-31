
public class VueloNacional extends VueloPublico {

	public VueloNacional(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros, double valorRefrigerio, double[] precios, int[] cantAsientos) {
		super(aeropuertoSalida, aeropuertoLlegada,fecha,  pasajeros,  valorRefrigerio,  precios,  cantAsientos);
	}
	
	public void valorPasaje() {
		
	}

	public double costoTotalVuelo() {
		return 0;
	}
		
	public double totalRecaudadoVuelo() {
		return 0;
	}
	
	public int asientosDisponibles() {
		return 0;
	}
	
	public void reprogramarVuelo() {}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\n");
		return sb.toString();
	}
}
