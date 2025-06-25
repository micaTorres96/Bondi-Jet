
public class Cliente {
	private Integer dni;
	private String nombre;
	private String telefono;
	private boolean registrado;
	
	public Cliente (Integer dni, String nombre, String telefono) {
		this.dni = dni;
		this.nombre = nombre;
		this.telefono = telefono;
		this.registrado = false;
	}
	
	public void registrarClienteComoPasajero() {
		this.registrado = true;
	}
	
	public void cancelarPasajeCliente() {
		if(registrado == true)
			registrado = false;
	}
	
	public int documento(){
		return dni;
	}
	
	public String nombre() {
		return nombre;
	}
	public String telefono() {
		return telefono;
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append(" -DNI: ").append(dni);
		sb.append(" -Nombre: ").append(nombre);
		sb.append(" -Telefono: ").append(telefono).append("\n");
		return sb.toString();
	}
	
}
