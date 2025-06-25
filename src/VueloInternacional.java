
public class VueloInternacional extends VueloPublico {
	private int cantRefrigerios;
	private String[] escalas;
	
	public VueloInternacional(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros, double valorRefrigerio,int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		super(aeropuertoSalida, aeropuertoLlegada,fecha, pasajeros,valorRefrigerio,precios, cantAsientos);
		this.cantRefrigerios = cantRefrigerios;
		this.escalas = escalas;
	}
	
	@Override
	public String obtenerTipoDeVuelo() {
		return "INTERNACIONAL";
	}
	
	//calcula el valor de pasaje, se necesita ingresar numero de asiento para saber a que seccion pertenece
	public double valorPasaje(int nroAsiento) {
		double costoBase = super.valorPasaje(nroAsiento); //incluye 1 refrigerio $26.000
		
		double costoRefrigerio = (cantRefrigerios -1) * super.valorRefrigerio(); //OK devuelve $12.000
		
		double total = costoBase + costoRefrigerio;
		
		return total * 1.2;
	}
	
/*Metodo que recorre escalas y las imprime en el toString de Vuelo Internacional*/
	public String mostrarEscalas() {
		StringBuilder asiento = new StringBuilder();
		for (String escala :  escalas)
			asiento.append("|").append(escala).append("|");
		return asiento.toString();
	}

	/*toString de Vuelo Internacional con sus atributos heredados y propios
	 * Fue utilizado StringBuilder (Tecnologia Java)
	 * */
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" -Cantidad de Refrigerios: ").append(cantRefrigerios);
		sb.append(" -Escalas: ").append(mostrarEscalas()).append("\n");
		return sb.toString();
	}
}
