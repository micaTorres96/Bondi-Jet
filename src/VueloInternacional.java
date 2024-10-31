import java.util.Arrays;

public class VueloInternacional extends VueloPublico {
	private int cantRefrigerios;
	private String[] escalas;
	
	public VueloInternacional(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros, double valorRefrigerio,int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		super(aeropuertoSalida, aeropuertoLlegada,fecha, pasajeros,valorRefrigerio,precios, cantAsientos);
		this.cantRefrigerios = cantRefrigerios;
		this.escalas = escalas;
	}
	
	public void valorPasaje() {}
	
	public double costoTotalVuelo() {
		return 0;
	}
	
	public double totalRecaudadoVuelo() {
		return 0;
	}
	public int asientosDisponibles() {
		return 0;
	}
	
	public void reprogramarVuelo() {
		
	}
	
	public String[] destinoEscalas() {
		return escalas;
	}
	
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nCantidad de Refrigerios: ").append(cantRefrigerios);
		sb.append("\nEscalas: ").append(escalas).append("\n");
		return sb.toString();
	}
}
