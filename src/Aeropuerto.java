
public class Aeropuerto {

	private String nombre;	
	private String pais;
	private String provincia;
	private String direccion;
	
	public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
		this.nombre = nombre;
		this.pais = pais;
		this.provincia = provincia;
		this.direccion = direccion;
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre: ").append(nombre);
		sb.append(" -Pais: ").append(pais);
		sb.append(" -Provincia: ").append(provincia);
		sb.append(" -Direccion: ").append(direccion).append("\n");
		return sb.toString();
	}
}
