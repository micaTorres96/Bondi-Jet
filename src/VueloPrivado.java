
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
	
	public String registrarVuelo(Aeropuerto aeropuertoDestino) {
		double monto = valorPasaje();
		aeropuertoDestino.agregarRecaudacion(monto);
		
		return generarCodigo();
	}
	
	public String generarCodigo() {
		return super.generarTipoDeVuelo("PRI");
	}
	
	@Override
	public String obtenerTipoDeVuelo() {
		return "PRIVADO (" + cantidadJetsNecesarios() + ")";
	}
	
/*Calcula la cantidad de jets necesarios para la cantidad de acompañantes
 * Hace una division entera y despues consulta si tiene residuo, si es asi se le suma 1 al contador de jets necesarios
 *, si no, no hace nada*/
	
	public int cantidadJetsNecesarios() {
		int jetsNecesarios = acompaniantes.length / 15;
		
	    if (acompaniantes.length == 0) /*significa que solo el comprador va en el vuelo*/
	        return 1; 
	    
	    if(acompaniantes.length % 15 != 0)
	    	jetsNecesarios++;
	    
		return jetsNecesarios;
	}

	//devuelve el valor del precio del vuelo con precio + 30% impuesto y la cantidad de jets para los acompañiantes.
    //polimorfismo

	public double valorPasaje() {
		double subtotal = precio * cantidadJetsNecesarios();
		double impuestos = subtotal * 0.3; //0.3 = 30%
		
		return subtotal + impuestos;	

	}

	public String mostrarAcompaniantes() {
		StringBuilder asiento = new StringBuilder();
		for(int acompaniante :  acompaniantes)
			asiento.append("|").append(acompaniante).append("|");
		return asiento.toString();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append(" -Precio: ").append(precio);
		sb.append(" -DNI comprador: ").append(dniComprador);
		sb.append(" -Acompañantes: ").append(mostrarAcompaniantes()).append("\n");
		return sb.toString();
	}

}
