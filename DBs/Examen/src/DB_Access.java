import java.sql.*;
import javax.swing.JOptionPane;

public class DB_Access {
	
	private Connection con;
	private Statement st;
	private  String controlador;
	private  String nombre_bd;
	private  String usuarioBD;
	private  String passwordBD;

	public DB_Access(){
		this.controlador="sun.jdbc.odbc.JdbcOdbcDriver";
        this.nombre_bd="src\\empleado.accdb";
        this.usuarioBD="";
        this.passwordBD="";

		/*try { // carga el controlador
			Class contr = Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Controlador cargado");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("com.mysql.jdbc.Driver");
		}*/
		
		try {
			// String direcci�n. Hace referencia a una URL
			Connection micon = DriverManager.getConnection("jdbc:ucanaccess://"+this.nombre_bd,this.usuarioBD,this.passwordBD);
			Statement st = micon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			this.con = micon;
			this.st = st;
		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(null, "No se creo la conexion");
		}
				
	}
	
	// Ejecuta una consulta de Actualizaci�n de la base de datos
		// (inserci�n, actualizaci�n o borrado de datos).
		public void actualiza(String datos) {
			try {
				System.out.println(datos);
				st.executeUpdate(datos);
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, sqle.toString());
			}
		}
		
		// Ejecuta una consulta de selecci�n, devolviendo el objeto
		// ResultSet con los datos obtenidos en la consulta.
		public ResultSet selecciona(String consulta) throws SQLException {
			return st.executeQuery(consulta);
		}
		
		public ResultSet getResultSet() throws SQLException {
			return st.getResultSet();
		}

		public Connection getConex() {
			return con;
		}

}
