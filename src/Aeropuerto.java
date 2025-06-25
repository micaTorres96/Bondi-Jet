
public class Aeropuerto {

	private String nombre;	
	private String pais;
	private String provincia;
	private String direccion;
	
	private double recaudacionTotal;
	
	public Aeropuerto(String nombre, String pais, String provincia, String direccion) {
		this.nombre = nombre;
		this.pais = pais;
		this.provincia = provincia;
		this.direccion = direccion;
		this.recaudacionTotal = 0;
	}
		
	public void agregarRecaudacion(double monto) {
		recaudacionTotal += monto;
	}
	
	public void eliminarRecaudacionPorCancelacion(double monto) {
		recaudacionTotal -= monto;
	}
	
	public double recaudacionTotal() {
		return recaudacionTotal;
	}
	
	public String pais() {
		return pais;
	}
	
	public String toString() { 
		StringBuilder sb = new StringBuilder();
		sb.append(" -Aeropuerto: ").append(nombre);
		sb.append(" -Pais: ").append(pais);
		sb.append(" -Provincia: ").append(provincia);
		sb.append(" -Direccion: ").append(direccion);
		sb.append(" -Recaudacion: $$  ").append(recaudacionTotal()).append("\n");
		return sb.toString();
	}
}
