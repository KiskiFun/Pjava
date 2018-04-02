import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class tablasEmpleados extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTable tabla;
	DefaultTableModel dtm;
	public String selector="";
	public String valor ="";
	obIns ob;
	final static String BDEmp="lib/DBEmple.yap";
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			tablasEmpleados dialog = new tablasEmpleados();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	
	//Paso por referencia el String selex que es el string por el que obtendré los datos del comboSelec.
	public tablasEmpleados(JFrame jf,boolean b, String selex, String buscar, String dni) {
		super(jf,b);
		selector=selector + selex;
		String comparar = selector;
			
		setBounds(100, 100, 679, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		String[]datos={"Dni","Nombre","Apellidos", "Ciudad", "Sueldo"};
		dtm=new DefaultTableModel();
		tabla=new JTable(dtm);
		
		for(int i=0;i<5;i++) //Indicamos que contaremos hasta 5, por que tenemos 5 datos (Sería la array "datos").
			dtm.addColumn(datos[i]);
		tabla.setPreferredScrollableViewportSize(new Dimension(350,70));
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 36,604,277);
		contentPanel.add(scrollPane);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 11, 128, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel labConexion = new JLabel("");
		labConexion.setBounds(125, 11, 104, 14);
		contentPanel.add(labConexion);
		
		
		
		String busqueda =null;
//		if (buscar.equals("buscarDni") || comparar.equals("MySQL")) {
		
		if (buscar.equals("No")) { //Si  es igual a NO, al apretar el boton de ver consultas. me salta el metodo comprobacion. 
			verDatos(comparar);
			JOptionPane.showMessageDialog(null, "No buscaré por Dni, Mostraré los datos.");
			setTitle("Consulta por: " + comparar);
			
			lblNewLabel.setText("Consulta Mediante : ");
			labConexion.setText(comparar);
		}else { //Si no apreto buscar por DNI, me salta el dni. 
			JOptionPane.showMessageDialog(null, "Buscaré por DNI.");
			setTitle("Mostrando Empleado por DNI buscado!");
			lblNewLabel.setText("DNI introducido: ");
			labConexion.setText(dni);
			//Metodo para buscar DNI.
			mostrarDNI(dni);
		}
	}
	
	public void verDatos(String comparar) {
		
		if(comparar.equals("MySQL")) {	
			verMysql();
		}else if(comparar.equals("Access")) {
			accessVer();
			JOptionPane.showMessageDialog(null, "Consulta por access");
		}else if(comparar.equals("DB40")) {
			db40Ver();
		}else if(comparar.equals("Derby")) {
			derbyVer();
		}
		
	}
	
	//Metodo para ver datos por MySQL
	public void verMysql() {
		
		//Instanciamos la coneccion a la base de datos.
		BD_Mysql bd=new BD_Mysql();
		String sql="select * from empleado";
		Object[]data=new Object[5]; //Importante poner cuantas columnas queremos conseguir
		
		try {
			ResultSet rs=bd.selecciona(sql);
			while (rs.next()) {
				String dni=rs.getString("dni");
				String nombre=rs.getString("nombre");
				String ape=rs.getString("apellidos");
				String ciudad=rs.getString("ciudad");
				String sueldo=rs.getString("Sueldo");
				
				//int ev=rs.getInt("apellidos"); ejemplo int por si el profe lo pidiera.
				
				 data[0]=dni;
				 data[1]=nombre;
				 data[2]=ape;
				 data[3]=ciudad;
				 data[4]=sueldo;
				 dtm.addRow(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//Método para ver datos por Access
	public void accessVer() {
		ResultSet rs;
		DB_Access db_acs;
		db_acs = new DB_Access();
		
		String sentencia="Select * from empleado";
		Object[]data=new Object[5];
		int m=dtm.getRowCount();
		//vaciamos la tabla si esta cargada
		if (m>0)
			for(int i=0;i<m;i++)
				dtm.removeRow(0);
		try{
		  rs=db_acs.selecciona(sentencia);
		  
		  while (rs.next()) {
			  	 String dni = rs.getString("dni"); 
				 String nomb = rs.getString("nombre"); 
				 String ape = rs.getString("apellidos");
				 String ciu =rs.getString("ciudad");
				 String sueld = rs.getString("sueldo"); 
				 //int s= rs.getInt("sueldo"); 
				 
				 data[0]=dni;
				 data[1]=nomb;
				 data[2]=ape;
				 data[3]=ciu;
				 data[4]=sueld;
				 
				 /*
				 data[0]=(Integer)c;
				 data[1]=(String)nomb;
				 data[2]=(String)a;
				 data[3]=(String)d;
				 data[4]=(Integer)s;
				 */
				 dtm.addRow(data);
		  }
		}
		catch(SQLException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	//Metodo para ver datos por DB40
	public void db40Ver() {
		
		Object[]data=new Object[5];
		int m=dtm.getRowCount();
		//vaciamos la tabla si esta cargada
		if (m>0)
			for(int i=0;i<m;i++)
				dtm.removeRow(0);
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDEmp);
		Objeto emp=new Objeto(null,null,null,null,null);
		ObjectSet<Objeto>result=db.queryByExample(emp);
		if(result.size()==0)System.out.println("No existen Registros de Empleados");
		else{
			
			while(result.hasNext()){
				Objeto p=result.next();
				 data[0]=p.getDni();
				 data[1]=p.getNombre();
				 data[2]=p.getApellidos();
				 data[3]=p.getCiudad();
				 data[4]=p.getSueldo();
				 dtm.addRow(data);
				}
		}
		db.close();
		
	}
	//Método para ver datos por Derby
	public void derbyVer() {
		
	}
	
	public void mostrarDNI(String dni){
		
	}
}
