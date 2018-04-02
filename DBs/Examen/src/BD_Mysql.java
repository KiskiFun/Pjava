import java.sql.*;

public class BD_Mysql {
	private Connection con;
	private Statement st;

	public BD_Mysql() {
		try { // carga el controlador
			Class contr = Class.forName("com.mysql.jdbc.Driver");
			System.out.println(contr.toString());
		} catch (ClassNotFoundException cnfe) {
			System.out.println("com.mysql.jdbc.Driver");
		}
		try {
			// String direcci�n. Hace referencia a una URL
			Connection micon = DriverManager.getConnection("jdbc:mysql://localhost/empleado", "root", "");
			Statement st = micon.createStatement();
			this.con = micon;
			this.st = st;
		} catch (SQLException sqle) {
			System.out.println("Error al establecer la conexion");
		}
	}

	// Ejecuta una consulta de Actualizaci�n de la base de datos
	// (inserci�n, actualizaci�n o borrado de datos).
	public void actualiza(String datos) {
		try {
			System.out.println(datos);
			st.executeUpdate(datos);
		} catch (SQLException sqle) {
			System.out.println("Error al ejecutar la consulta");
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