import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaMetodosAux {

	public static LocalDate conversionFecha(String fecha) {
        DateTimeFormatter convertirString = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaVuelo = LocalDate.parse(fecha, convertirString);
		return fechaVuelo;
	}
	
	/* private void comprobarFechaValida(String fecha)
	 * Valida la fecha ingresada del vuelo. Compara la fecha actual con la ingresada
	 * LocalDate fechaActual devuelve la fecha actual del momento.
	 * En el if se compara si la fecha del vuelo es anterior a la actual, si es anterior se lanza la excepcion
	 * */

	public static void comprobarFechaValida(String fecha) {
		LocalDate fechaVuelo = conversionFecha(fecha);
        LocalDate fechaActual = LocalDate.now();
        
        if(fechaVuelo.isBefore(fechaActual))
        	throw new RuntimeException("La fecha ingresada no es valida, es anterior a la actual"); 
	}
}
