public class Objeto {
	
	private String dni,nombre,apellidos,ciudad,sueldo;
	//private int sueldo;
	
	public Objeto (String odni, String onombre, String oapellidos, String ociudad, String osueldo) {
		dni=odni;
		nombre=onombre;
		apellidos=oapellidos;
		ciudad=ociudad;
		sueldo=osueldo;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getSueldo() {
		return sueldo;
	}
	public void setSueldo(String sueldo) {
		this.sueldo = sueldo;
	}
	
}