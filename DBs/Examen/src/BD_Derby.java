import java.sql.*;
import javax.swing.JOptionPane;

public class BD_Derby {
	private Connection con;
	private Statement st;

	public BD_Derby() {
		try { // carga el controlador
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			// String dirección. Hace referencia a una URL
			Connection micon = DriverManager.getConnection("jdbc:derby:lib/empleado");
			Statement st = micon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			this.con = micon;
			this.st = st;
		} catch (SQLException sqle) {
			System.out.println("Error al establecer la conexion");
		}
	}

	// Ejecuta una consulta de Actualización de la base de datos
	// (inserción, actualización o borrado de datos).
	public void actualiza(String datos) {
		try {
			System.out.println(datos);
			st.executeUpdate(datos);
		} catch (SQLException sqle) {
			System.out.println("Error al ejecutar la consulta");
		}
	}

	// Ejecuta una consulta de selección, devolviendo el objeto
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
