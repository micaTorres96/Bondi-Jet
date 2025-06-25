import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aerolinea implements IAerolinea {
	private String nombre;
	private String cuit;
/* ---------------------------- Colecciones -------------------------------------------------------- */
	//String = codigo de vuelo, clave unica, Vuelo = valor que corresponde a esa clave
	public HashMap<String, Vuelo> vuelos;
	
	//String nombre de aeropuerto = clave unica, Aeropuerto= valor que corresponde a esa clave
	private HashMap<String, Aeropuerto> aeropuertos;	
	
	//Integer dni = clave unica, Cliente = valor que corresponde a esa clave
	private HashMap<Integer, Cliente> pasajeros;     
/* ---------------------------- Aerolinea -------------------------------------------------------- 
 * -1) Constructor de Aerolinea*/
	public Aerolinea(String nombre, String cuit) {	
		this.nombre = nombre;
		this.cuit = cuit;
		vuelos = new HashMap<String,Vuelo>();
		aeropuertos = new HashMap<String, Aeropuerto>();
		pasajeros = new HashMap<Integer, Cliente>();
	}
	
/* -2) Se registran los clientes de la Aerolínea, compren o no pasaje. 
* Cuando un cliente compre un pasaje es un Cliente (pasajero) y queda registrado en el vuelo correspondiente.*/
	
	public void registrarCliente(int dni, String nombre, String telefono) {
		if(pasajeros.containsKey(dni)) {
			throw new RuntimeException("El cliente con DNI:"+ dni + "ya esta registrado como pasajero");
		}
		if(nombre.isEmpty() || telefono.isEmpty()) {
			throw new RuntimeException("Ingrese un nombre: " + nombre + "o telefono: "+ telefono + "valido");
		}	
		//Registro al cliente y lo guardo en pasajeros(puede ser un acompañante de un vuelo privado pero se valida al realizar la compra del pasaje)
		Cliente cliente = new Cliente(dni,nombre,telefono);
		pasajeros.put(dni, cliente);
	} 
	
/* -3) Se ingresa un aeropuerto con los datos que lo identifican. Estos aeropuertos son los que deberán corresponder
* al origen y destino de los vuelos.
* El nombre es único por aeropuerto en todo el mundo.*/
	
	public void registrarAeropuerto(String nombre, String pais, String provincia, String direccion) {
		if(aeropuertos.containsKey(nombre)) {
			throw new RuntimeException("El aeropuerto: "+ nombre + "ya esta registrado");
		}
		if(pais.isEmpty() || provincia.isEmpty() || direccion.isEmpty()) {
			throw new RuntimeException("Todos los datos del aeropuerto deben ser validos");
		}
		Aeropuerto aeropuerto = new Aeropuerto(nombre, pais, provincia, direccion);
		aeropuertos.put(nombre, aeropuerto);
	}
	
/* - 4) El origen y destino deben ser aeropuertos con país=”Argentina” y ya registrados en la aerolinea. 
* La fecha es la fecha de salida del vuelo.
* Los asientos se considerarán numerados correlativamente empezando con clase Turista y terminando con la clase Ejecutivo.
* Se cumple que precios.length == cantAsientos.length == 2
* -  cantAsientos[0] = cantidad total de asientos en clase Turista.
* -  cantAsientos[1] = cantidad total de asientos en clase Ejecutivo
*   idem precios.
* Tripulantes es la cantidad de tripulantes del vuelo.
* valorRefrigerio es el valor del unico refrigerio que se sirve en el vuelo.
* Devuelve el código del Vuelo con el formato: {Nro_vuelo_publico}-PUB. Por ejemplo--> 103-PUB
* Si al validar los datos no se puede registrar, se deberá lanzar una excepción.*/
	
	public String registrarVueloPublicoNacional(String origen, String destino, String fecha, int tripulantes, double valorRefrigerio, double[] precios, int[] cantAsientos) {	
		if(!aeropuertos.containsKey(origen) || !aeropuertos.containsKey(destino))
				throw new RuntimeException("Error al registrar aeropuerto, origen: " + origen + "destino: " + destino + "invalido");
		//Utilizo metodos creados en la clase Fecha para poder comprobar si la fecha es valida
		FechaMetodosAux.comprobarFechaValida(fecha);
		if(cantAsientos.length != 2 || precios.length != 2 )
			throw new RuntimeException("Error de cantidad de precios/asientos");
		
		if(!aeropuertos.get(origen).pais().equals("Argentina") || !aeropuertos.get(destino).pais().equals("Argentina")){
			throw new RuntimeException("El origen o destino no pertenece a un vuelo nacional");
		}
		
		//Creo el tipo de vuelo y tambien genero su codigo de vuelo, luego se almacena en vuelos
		VueloNacional VueloPublicoNacional = new VueloNacional(origen, destino,fecha,tripulantes, valorRefrigerio, precios,cantAsientos);
		String codigo = VueloPublicoNacional.generarCodigoVuelo();
		vuelos.put(codigo, VueloPublicoNacional);
		
		return codigo;
	}
	
	/** - 5) Pueden ser vuelos con escalas o sin escalas. 
	* La fecha es la de salida y debe ser posterior a la actual.  
	* Los asientos se considerarán numerados correlativamente empezando con clase Turista, siguiendo por Ejecutiva 
	* y terminando con Primera clase.
	* 
	* precios.length == cantAsientos.llength == 3
	*    - cantAsientos[0]  = cantidad total de asientos en clase Turista.
	*    - cantAsientos[1]  = cantidad total de asientos en clase Ejecutiva.
	*    - cantAsientos[2]  = cantidad total de asientos en Primera clase.
	*	 idem precios.
	*	 - escalas = nombre del aeropuerto donde hace escala. Si no tiene escalas, esto es un arreglo vacío.
	* Tripulantes es la cantidad de tripulantes del vuelo. 
	* valorRefrigerio es el valor del refrigerio que se sirve en el vuelo.
	* cantRefrigerios es la cantidad de refrigerio que se sirven en el vuelo.
	*
	* Devuelve el código del vuelo.  Con el formato: {Nro_vuelo_publico}-PUB, por ejemplo--> 103-PUB
	* Si al validar los datos no se puede registrar, se deberá lanzar una excepción.
	*/
	
	public String registrarVueloPublicoInternacional(String origen, String destino, String fecha, int tripulantes,
			double valorRefrigerio, int cantRefrigerios, double[] precios, int[] cantAsientos, String[] escalas) {	
		//Utilizo metodos creados en la clase Fecha para poder comprobar si la fecha es valida
		FechaMetodosAux.comprobarFechaValida(fecha);
		if(cantAsientos.length != 3 || precios.length != 3)
			throw new RuntimeException("Error! cantidad de precios/asientos incorrectos");
		
		//Creo el tipo de vuelo y tambien genero su codigo de vuelo, luego se almacena en vuelos
		VueloInternacional VueloPublicoInternacional= new VueloInternacional(origen, destino,fecha, tripulantes, valorRefrigerio,cantRefrigerios,precios,cantAsientos,escalas);
		String codigo = VueloPublicoInternacional.generarCodigoVuelo();
		vuelos.put(codigo, VueloPublicoInternacional);
		
		return codigo;
	}
	
	/** 6 y 10 **** Se reune en esta firma ambos puntos de la especificación.
	* Origen y destino son los Aeropuertos de donde parte y al que llega el jet. 
	* Fecha es la fecha de salida y debe ser posterior a la fecha actual.
	* Tripulantes es la cantidad de tripulantes del vuelo. 
	* Precio es el de un(1) jet. 
	* Se supone que se cuenta con todos los jets necesarios para trasladar todos los acompañantes. 
	* Se usara la cantidad de jets (necesarios) para el calculo del costo total del Vuelo.
	* IMPORTANTE; Se toma un sólo código para la compra aunque se necesiten mas de un jet. 
	* No se sirven refrigerios
	* Devuelve el código del vuelo. Con el formato: {Nro_vuelo_privado}-PRI, por ejemplo: 103-PRI*/
	
	public String VenderVueloPrivado(String origen, String destino, String fecha, int tripulantes, double precio, int dniComprador, int[] acompaniantes) {	
	
		//Utilizo metodos creados en la clase Fecha para poder comprobar si la fecha es valida
		FechaMetodosAux.comprobarFechaValida(fecha);
		VueloPrivado vueloPrivado = new VueloPrivado(origen, destino, fecha, tripulantes, precio,dniComprador, acompaniantes);
		
		Aeropuerto aeropuertoDestino = aeropuertos.get(destino);
		if(aeropuertoDestino == null) {
			throw new RuntimeException("El aeropuerto de destino no existe");
		}
		//Con registrarVuelo se calcula el monto y se genera el codigo de vuelo privado
		String codigoVueloPrivado = vueloPrivado.registrarVuelo(aeropuertoDestino);
		vuelos.put(codigoVueloPrivado, vueloPrivado);
		
		return codigoVueloPrivado; 
	}
	
/*- 7 
*  Dado el código del vuelo, devuelve un diccionario con los asientos aún disponibles para la venta 
*  --> clave:  el número de asiento
*  --> valor:  la sección a la que pertenecen los asientos.
*/	
	public Map<Integer, String> asientosDisponibles(String codVuelo) {			
		if (!vuelos.containsKey(codVuelo)) {
	        throw new RuntimeException("El vuelo con código " + codVuelo + " no existe");}

	    Vuelo vuelo = vuelos.get(codVuelo);

	    if (vuelo instanceof VueloPublico) {
	        VueloPublico vueloPublico = (VueloPublico) vuelo;
	        return vueloPublico.listaAsientosDisponibles();
	    }
	    else {
	        throw new RuntimeException("El vuelo con código " + codVuelo + " no es un vuelo público");
	    }
	}

/*- 8 y 9 devuelve el codigo del pasaje comprado.
* Los pasajeros que compran pasajes deben estar registrados como clientes, con todos sus datos, antes de realizar la compra.
* Devuelve el codigo del pasaje y lanza una excepción si no puede venderlo.
* aOcupar indica si el asiento que será ocupado por el cliente, o si solo lo compro para viajar más cómodo.
* Devuelve un código de pasaje único que se genera incrementalmente sin distinguir entre tipos de vuelos.
*/	
	public int venderPasaje(int dni, String codVuelo, int nroAsiento, boolean aOcupar) {
		Cliente cliente = pasajeros.get(dni);
		if(cliente == null) {
			throw new RuntimeException("El cliente con dni: "+ dni + "no se encuentra registrado");
		}
		
		Vuelo vuelo = vuelos.get(codVuelo);
		if(!(vuelo instanceof VueloPublico)) {
			throw new RuntimeException("El vuelo no es publico");
		}
		
		VueloPublico vueloPublico = (VueloPublico) vuelo;
		Aeropuerto aeropuertoDestino = aeropuertos.get(vuelo.aeropuertoLlegada());
		
		int codigoPasaje = vueloPublico.registrarPasaje(cliente, nroAsiento, aOcupar, aeropuertoDestino);
		
		return codigoPasaje;
	}
	
	/** - 11. 
	 * devuelve una lista de códigos de vuelos. que estén entre fecha dada y hasta una semana despues. 
	 * La lista estará vacía si no se encuentran vuelos similares. La Fecha es la fecha de salida.*/
	
	public List<String> consultarVuelosSimilares(String origen, String destino, String Fecha) {
		List<String> consultarVuelosSimilares = new ArrayList<String>();
		
		LocalDate fechaConvertida = FechaMetodosAux.conversionFecha(Fecha);
		LocalDate fechaUnaSemanaDespues = fechaConvertida.plusDays(7);

		for(Vuelo vuelo : vuelos.values()) {
			LocalDate fechaVuelo = FechaMetodosAux.conversionFecha(vuelo.fecha());
			
			if(vuelo.aeropuertoLlegada().equals(destino) && vuelo.aeropuertoSalida().equals(origen)
					&& fechaVuelo.isEqual(fechaConvertida) || (fechaVuelo.isAfter(fechaConvertida) && fechaVuelo.isBefore(fechaUnaSemanaDespues))) 
				consultarVuelosSimilares.add(vuelo.fecha()); 
		}
		return consultarVuelosSimilares;
	}
	
/** - 12 
 * Hay 2 posibles firmas para implementar esto: 12A  Y  12B
 */

/** - 12-A 
* Se borra el pasaje y se libera el lugar para que pueda comprarlo otro cliente.
* IMPORTANTE: Se debe resolver en O(1).
*/	
	public void cancelarPasaje(int dni, String codVuelo, int nroAsiento) {
		if(!pasajeros.containsKey(dni)) {
			throw new RuntimeException("No hay pasajero registrado con el dni: " + dni);
		}
		else{
			Cliente cliente = pasajeros.get(dni);
			cliente.cancelarPasajeCliente();
			//esto tambien fue agregado, ya que al cancelar el pasaje deja de ser un pasajero.
			//no se habia eliminado de pasajeros
			pasajeros.remove(dni);
			}
		
		Vuelo vuelo = vuelos.get(codVuelo);
		if(vuelo instanceof VueloPublico) {
			((VueloPublico) vuelo).liberarAsiento(nroAsiento);
			
			double valorPasaje = ((VueloPublico) vuelo).valorPasaje(nroAsiento); //obtengo el valor de pasaje
			String destino = ((VueloPublico) vuelo).aeropuertoLlegada(); //destino de vuelo
			
			Aeropuerto aeropuerto = aeropuertos.get(destino);
			if(aeropuerto != null) {
				aeropuerto.eliminarRecaudacionPorCancelacion(valorPasaje);
			}
			else {
				throw new RuntimeException("Destino no encontrado");
			}
				
		}
	}
	
/** 12-B
* Se cancela un pasaje dado el codigo de pasaje. 
* NO es necesario que se resuelva en O(1).*/	
	
	public void cancelarPasaje(int dni, int codPasaje) {
		//Convierto codigo de pasaje que se ingresa por parametro
		String codVueloPrivado = convertirCodigoVueloAFormato(codPasaje,"PRI");
		String codVueloPublico = convertirCodigoVueloAFormato(codPasaje, "PUB");
		
		//obtengo el pasajero
		Cliente cliente = pasajeros.get(dni);
		if(!pasajeros.containsKey(dni)) {
			throw new RuntimeException("El pasajero no esta registrado");
		}
		
		//busco el vuelo asociado
		Vuelo vueloAsociado = null;
		if(vuelos.containsKey(codVueloPrivado)) {
			vueloAsociado = vuelos.get(codVueloPrivado);
		}
		else if (vuelos.containsKey(codVueloPublico)) {
			vueloAsociado = vuelos.get(codVueloPublico);
		}
		else {
			throw new RuntimeException("No se encontro un vuelo asociado");
		}
		//Cuando lo encuentro identifico el tipo de vuelo 
		//Si es publico, obtengo la lista de asientos disponibles y consulto si el lugar esta "OCUPADO"
		if(vueloAsociado instanceof VueloPublico) {
			Map<Integer, String> asientos = ((VueloPublico) vueloAsociado).listaAsientosDisponibles();
			for(Map.Entry<Integer, String> entry : asientos.entrySet()) {
				int nroAsiento = entry.getKey();
				String estado = entry.getValue();
				
				if(estado.equals("OCUPADO")) {
					((VueloPublico) vueloAsociado).liberarAsiento(nroAsiento);
					cliente.cancelarPasajeCliente();
					pasajeros.remove(dni);
					
					double valorPasaje = ((VueloPublico) vueloAsociado).valorPasaje(nroAsiento);
					Aeropuerto aeropuerto = aeropuertos.get(vueloAsociado.aeropuertoLlegada());
					aeropuerto.eliminarRecaudacionPorCancelacion(valorPasaje);
				}
			}
			
		}
		//Si el vuelo es privado
		else if(vueloAsociado instanceof VueloPrivado) {
			vuelos.remove(codVueloPrivado);
			cliente.cancelarPasajeCliente();
			
			double valorPasaje = ((VueloPrivado) vueloAsociado).valorPasaje();
			Aeropuerto aeropuerto = aeropuertos.get(vueloAsociado.aeropuertoLlegada());
			aeropuerto.eliminarRecaudacionPorCancelacion(valorPasaje);
			pasajeros.remove(dni);
		}
	}

	
/** - 13
* Cancela un vuelo completo conociendo su codigo.
* Los pasajes se reprograman a vuelos con igual destino, no importa el numero del asiento pero 
* si a igual seccion o a una mejor, y no importan las escalas.
* Devuelve los codigos de los pasajes que no se pudieron reprogramar.
* Los pasajes no reprogramados se eliminan. Y se devuelven los datos de la cancelación, indicando 
* los pasajeros que se reprogramaron y a qué vuelo,  y los que se cancelaron por no tener lugar.
* Devuelve una lista de Strings con este formato : “dni - nombre - telefono - [Codigo nuevo vuelo|CANCELADO]”
* --> Ejemplo: 
*   . 11111111 - Juan - 33333333 - CANCELADO
*   . 11234126 - Jonathan - 33333311 - 545-PUB*/
	
	public List<String> cancelarVuelo(String codVuelo) {
	    // Verificar que el vuelo existe
	    if (!vuelos.containsKey(codVuelo)) {
	        throw new RuntimeException("El vuelo no existe: " + codVuelo);}
	    
	    // Obtener el vuelo a cancelar
	    Vuelo vueloCancelado = vuelos.get(codVuelo);
	    if (!(vueloCancelado instanceof VueloPublico)) {
	        throw new RuntimeException("El vuelo a cancelar no es público");}

	    VueloPublico vueloPublicoCancelado = (VueloPublico) vueloCancelado; //obtengo el vuelo cancelado
	    List<Integer> pasajerosDelvueloCancelado = vueloPublicoCancelado.pasajerosDni(); //obtengo pasajeros del vuelo cancelado
	    String destinoVueloCancelado = vueloPublicoCancelado.aeropuertoLlegada(); //obtengo destino del vuelo cancelado
	    vueloPublicoCancelado.decrementarCodigoVuelo(); //si el vuelo se cancela, se elimina del contador de generador de codigo de vuelos
	    
	    Aeropuerto aeropuertoDestinoCancelado = aeropuertos.get(destinoVueloCancelado); //obtengo el aeropuerto del vuelo cancelado
	    
	    // Buscar un vuelo alternativo
	    VueloPublico vueloAlternativo = null;
	    for (Vuelo vuelo : vuelos.values()) {
	        if (vuelo instanceof VueloPublico && vuelo.aeropuertoLlegada().equals(destinoVueloCancelado)) {
	            vueloAlternativo = (VueloPublico) vuelo;
	            break; // Encontramos un vuelo alternativo con el mismo destino
	        }
	    }

	    if (vueloAlternativo == null) {
	        throw new RuntimeException("No hay vuelo alternativo disponible");
	    }

	    // Verificar que el vuelo alternativo tiene asientos disponibles
	    if (vueloAlternativo.listaAsientosDisponibles().isEmpty()) {
	        throw new RuntimeException("El vuelo alternativo no tiene asientos disponibles");
	    }

	    vuelos.remove(codVuelo);
	    return vueloAlternativo.reubicarPasajeros(pasajerosDelvueloCancelado, pasajeros,aeropuertoDestinoCancelado);
	}
	
/** - 14
* devuelve el total recaudado por todos los viajes al destino pasado por parámetro. 
* IMPORTANTE: Se debe resolver en O(1).*/	
	
	public double totalRecaudado(String destino) {
		if(!aeropuertos.containsKey(destino)) {
			throw new RuntimeException("El aeropuerto no esta registrado");
		}
		
		Aeropuerto aeropuerto = aeropuertos.get(destino);
		double recaudacionTotal = aeropuerto.recaudacionTotal();

		return recaudacionTotal;
	}
	
/** - 15 
* Detalle de un vuelo
* devuelve un texto con el detalle un vuelo en particular.
* Formato del String: CodigoVuelo - Nombre Aeropuerto de salida - Nombre Aeropuerto de llegada - 
*                     fecha de salida - [NACIONAL /INTERNACIONAL / PRIVADO + cantidad de jets necesarios].
* --> Ejemplo:
*   . 545-PUB - Bariloche - Jujuy - 10/11/2024 - NACIONAL
*   . 103-PUB - Ezeiza  - Madrid -  15/11/2024 - INTERNACIONAL
*   . 222-PRI - Ezeiza - Tierra del Fuego - 3/12/2024 - PRIVADO (3)*/	
	public String detalleDeVuelo(String codVuelo) {
	Vuelo vuelo = vuelos.get(codVuelo);
	StringBuilder detallesVuelo = new StringBuilder();
	detallesVuelo.append(codVuelo).append(" - ").append(vuelo.aeropuertoSalida()).append(" - ");	
	detallesVuelo.append(vuelo.aeropuertoLlegada()).append(" - ").append(vuelo.fecha()).append(" - ");
	detallesVuelo.append(vuelo.obtenerTipoDeVuelo());
	
	return detallesVuelo.toString();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n-Aeropuerto: ").append(nombre);
		sb.append("\n-Cuit: ").append(cuit);
		sb.append("\n-Vuelos: ").append(vuelos);
		sb.append("\n-Aeropuertos: ").append(aeropuertos);
		sb.append("\nPasajeros: ").append(pasajeros);
		return sb.toString();
	}

/* ------------------------ Metodo auxiliar ----------------------------------------------*/
	private String convertirCodigoVueloAFormato(int codigo, String tipoDeVuelo) {
		StringBuilder cod = new StringBuilder();
		cod.append(codigo).append("-").append(tipoDeVuelo);
		return cod.toString();
	}
}