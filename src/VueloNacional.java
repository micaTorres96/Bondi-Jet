
public class VueloNacional extends VueloPublico {

	public VueloNacional(String aeropuertoSalida, String aeropuertoLlegada,String fecha, Integer pasajeros,
			double valorRefrigerio,double[] precios, int[] cantAsientos) {
		super(aeropuertoSalida, aeropuertoLlegada,fecha,  pasajeros,  valorRefrigerio,  precios,  cantAsientos);
	}
	

	@Override
	public String obtenerTipoDeVuelo() {
		return "NACIONAL";
	}
	
/*Para imprimir por pantalla se utiliza StringBuilder y se van concatenando los datos con los heredados incluidos*/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\n");
		return sb.toString();
	}

}
