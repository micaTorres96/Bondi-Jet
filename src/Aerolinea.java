import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aerolinea implements IAerolinea {
	private String nombre;
	private String cuit;
	
	//String  = clave unica, Vuelo = valor que corresponde a esa clave
	private HashMap<String, Vuelo> vuelos;
	
	//String nombre = clave unica,  Aeropuerto = valor que corresponde a esa clave
	private HashMap<String, Aeropuerto> aeropuertos;
	
	//Integer dni = clave unica, Cliente = valor que corresponde a esa clave
	private HashMap<Integer, Cliente> pasajeros;     
	
/* --------------- Constructor de aerolineas ----------------------------------------------
 * */
	public Aerolinea(String nombre, String cuit) {	
		this.nombre = nombre;
		this.cuit = cuit;
		this.vuelos = new HashMap<String, Vuelo>();
		this.aeropuertos = new HashMap<String, Aeropuerto>();
		this.pasajeros = new HashMap<Integer, Cliente>();
	}

	/**
	* - 2
	* Se registran los clientes de la Aerolínea, compren o no pasaje. 
	* Cuando un cliente compre un pasaje es un Cliente (pasajero) y queda registrado en el vuelo correspondiente.
	*/
	public void registrarCliente(int dni, String nombre, String telefono) {
		if(pasajeros.containsKey(dni))
			throw new RuntimeException("El cliente ya esta registrado como pasajero");
				
		Cliente cliente = new Cliente(dni,nombre,telefono);
		pasajeros.put(dni, cliente);
	} 
	
	/**
	* - 3 
	* Se ingresa un aeropuerto con los datos que lo identifican. Estos aeropuertos son los que deberán corresponder
	* al origen y destino de los vuelos.
	* El nombre es único por aeropuerto en todo el mundo.
	*/
	
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if(aeropuertos.containsKey(nombre))
			throw new RuntimeException("El aeropuerto ya esta registrado");
		
		Aeropuerto aeropuerto = new Aeropuerto(nombre, pais, provincia, direccion);
		aeropuertos.put(nombre, aeropuerto);
	}
	
	/** - 4
	* El origen y destino deben ser aeropuertos con país=”Argentina” y ya registrados en la aerolinea. 
	* La fecha es la fecha de salida del vuelo.
	* Los asientos se considerarán numerados correlativamente empezando con clase Turista y terminando con la clase Ejecutivo.
	* Se cumple que precios.length == cantAsientos.length == 2
	* -  cantAsientos[0] = cantidad total de asientos en clase Turista.
	* -  cantAsientos[1] = cantidad total de asientos en clase Ejecutivo
	*   idem precios.
	* Tripulantes es la cantidad de tripulantes del vuelo.
	* valorRefrigerio es el valor del unico refrigerio que se sirve en el vuelo.
	* 
	* Devuelve el código del Vuelo con el formato: {Nro_vuelo_publico}-PUB. Por ejemplo--> 103-PUB
	* Si al validar los datos no se puede registrar, se deberá lanzar una excepción.*/
	
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, double[] precios, int[] cantAsientos) {
		return null;
	}
	
	
	@Override
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio,
			int dniComprador, int[] acompaniantes) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Map<Integer, String> asientosDisponibles(String codVuelo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void cancelarPasaje(int dni, int codPasaje) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<String> cancelarVuelo(String codVuelo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double totalRecaudado(String destino) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String detalleDeVuelo(String codVuelo) {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Aeropuerto: ").append(nombre);
		sb.append("\nCuit: ").append(cuit);
		sb.append("\nVuelos: ").append(vuelos);
		sb.append("\nAeropuertos: ").append(aeropuertos);
		sb.append("\nPasajeros: ").append(pasajeros);
		return sb.toString();
	}
}
