package examen;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Interfaz extends JFrame {

	private JPanel contentPane;
	private JTextField CajaDni;
	private JTextField CajaNombre;
	private JTextField CajaApellidos;
	private JTextField CajaCiudad;
	private JTextField CajaSueldo;
	private JTable tabla;

	private DefaultTableModel dtm;//tabla
	private int opcion;//opcion de la base datos
	final static String BDEmpleado = "lib/DB_Empleado.yap";//base datos

	private JComboBox comboBox;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interfaz() {
		
		//rs=null;
		//bd = new BD(0);
		opcion = 0;//opcion a cero
		
		setTitle("Empleados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("DNI");
		label.setBounds(41, 17, 56, 16);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nombre");
		label_1.setBounds(41, 59, 56, 16);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Apellidos");
		label_2.setBounds(41, 111, 56, 16);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Ciudad");
		label_3.setBounds(41, 165, 56, 16);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Sueldo");
		label_4.setBounds(41, 213, 56, 16);
		contentPane.add(label_4);
		
		CajaDni = new JTextField();
		CajaDni.setColumns(10);
		CajaDni.setBounds(109, 14, 116, 22);
		contentPane.add(CajaDni);
		
		CajaNombre = new JTextField();
		CajaNombre.setColumns(10);
		CajaNombre.setBounds(109, 56, 144, 22);
		contentPane.add(CajaNombre);
		
		CajaApellidos = new JTextField();
		CajaApellidos.setColumns(10);
		CajaApellidos.setBounds(109, 108, 236, 22);
		contentPane.add(CajaApellidos);
		
		CajaCiudad = new JTextField();
		CajaCiudad.setColumns(10);
		CajaCiudad.setBounds(109, 162, 144, 22);
		contentPane.add(CajaCiudad);
		
		CajaSueldo = new JTextField();
		CajaSueldo.setColumns(10);
		CajaSueldo.setBounds(109, 210, 67, 22);
		contentPane.add(CajaSueldo);
		
		JButton button = new JButton("Alta");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alta();
				limpiar(true);
			}
		});
		button.setBounds(470, 13, 97, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Baja");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baja();
				limpiar(true);
			}
		});
		button_1.setBounds(470, 55, 97, 25);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Modificar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificar();
				limpiar(true);
			}
		});
		button_2.setBounds(470, 93, 97, 25);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Consulta por Dni");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
				limpiar(true);
			}
		});
		button_3.setBounds(435, 131, 161, 25);
		contentPane.add(button_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				opcion = comboBox.getSelectedIndex();
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"MySQL", "ApacheDerby", "Access", "DB4O"}));
		comboBox.setBounds(141, 261, 144, 22);
		contentPane.add(comboBox);
		
		
		JLabel label_5 = new JLabel("Base de datos");
		label_5.setBounds(41, 264, 105, 16);
		contentPane.add(label_5);
		
		JButton button_4 = new JButton("Ver todos los registros");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ver();
				limpiar(true);
			}
		});
		button_4.setBounds(435, 177, 161, 25);
		contentPane.add(button_4);
		
		//meter los datos en la tabala
		
		String[]datos={"Dni","Nombre","Apellidos","Ciudad","Sueldo"};
		dtm=new DefaultTableModel();
		tabla=new JTable(dtm);
		
		for(int i=0;i<5;i++)
		dtm.addColumn(datos[i]);
		tabla.setPreferredScrollableViewportSize(new Dimension(350,70));
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(90, 334,465,132);
		contentPane.add(scrollPane);

		
		JButton button_5 = new JButton("Guardar fichero de objeto");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		button_5.setBounds(406, 215, 222, 25);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("Guardar XML");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					crearDOM();
				} catch (IOException | ParserConfigurationException | TransformerFactoryConfigurationError
						| TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_6.setBounds(406, 260, 222, 25);
		contentPane.add(button_6);
	}
	
	public void limpiar(boolean indice) {
		if(indice)
		CajaDni.setText("");
		CajaNombre.setText("");
		CajaApellidos.setText("");
		CajaSueldo.setText("");
		CajaCiudad.setText("");
		CajaDni.requestFocus(true);
	}
	
	public void ver() {
		String[]datos = new String[5];
		while (dtm.getRowCount() > 0)
			dtm.removeRow(0);
		
		if(opcion < 3) {
			BD bd = new BD(opcion);
			ResultSet rs;
			String sentencia = "select * from empleados";
			try {
				rs = bd.selecciona(sentencia);
				while (rs.next()) { 
					int dni = rs.getInt("dni"); 
					String nombre = rs.getString("nombre"); 
					String apellidos = rs.getString("apellidos"); 
					String ciudad = rs.getString("ciudad");
					int sueldo = rs.getInt("sueldo"); 
					datos[0] = String.valueOf(dni);
					datos[1] = nombre;
					datos[2] = apellidos;
					datos[3] = ciudad;
					datos[4] = String.valueOf(sueldo);
					dtm.addRow(datos);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		else {
			ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmpleado);	
			Empleado empleado = new Empleado (0, null, null, 0,null);
			ObjectSet<Empleado> result = db.queryByExample(empleado);
			if (result.size() == 0)
				JOptionPane.showInputDialog(this, "No existe el registro");
			else {
				while(result.hasNext()){
					Empleado cont = result.next();
					datos[0] = String.valueOf(cont.getDni());
					datos[1] = cont.getNombre();
					datos[2] = cont.getApellidos();
					datos[3] = cont.getCiudad();
					datos[4] = String.valueOf(cont.getSueldo());
					dtm.addRow(datos);
				}
			}	 
			db.close();
		}

}
	public void alta() {
		int dni = Integer.parseInt(CajaDni.getText());
		String nombre = CajaNombre.getText();
		String apellidos = CajaApellidos.getText();
		String ciudad = CajaCiudad.getText();
		int sueldo = Integer.parseInt(CajaSueldo.getText());	
		if(opcion < 3) {
			BD bd = new BD(opcion);
			
			String sentencia = "insert into empleados values(?,?,?,?,?)";
			try {
				Connection conexion = bd.getConex();
				PreparedStatement ps = conexion.prepareStatement(sentencia);
				ps.setInt(1, dni);
				ps.setString(2, nombre);
				ps.setString(3, apellidos);
				ps.setString(4, ciudad);
				ps.setInt(5, sueldo);
				ps.executeUpdate();
				ps.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} else {
			Empleado ejemploempleado = new Empleado (0, null, null, 0, null);
			ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmpleado);
			ObjectSet<Empleado> result = db.queryByExample(ejemploempleado);
			Empleado cont = new Empleado(dni, nombre, apellidos, sueldo,ciudad);
			db.store(cont);
			db.close();			
		}
	}
	public void baja() {
		int dni = Integer.parseInt(CajaDni.getText());
		if (opcion < 3) {
			BD bd = new BD(opcion);
			
			
			String sentencia = "delete from empleados where dni=?";
			try {
				Connection conexion = bd.getConex();
				PreparedStatement ps = conexion.prepareStatement(sentencia);
				ps.setInt(1, dni);
				ps.executeUpdate();
				ps.close();
				conexion.close();
				limpiar(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}				
		} else {
			ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmpleado);
			Empleado empleado = new Empleado (dni, null, null, 0,null);
			ObjectSet<Empleado> result = db.queryByExample(empleado);
			if (result.size() == 0)
				JOptionPane.showInputDialog(this, "No existe el registro");
			else {
				Empleado existe = (Empleado)result.next();
				db.delete(existe);
				limpiar(true);
			}
			db.close();
		}

	}
	public void modificar() {
		int dni = Integer.parseInt(CajaDni.getText());
		String nombre = CajaNombre.getText();
		String apellidos = CajaApellidos.getText();
		String ciudad = CajaCiudad.getText();
		int sueldo = Integer.parseInt(CajaSueldo.getText());	
		
		if(opcion < 3) {
			BD bd = new BD(opcion);
					
			String sentencia = "update empleados set nombre=?, apellidos=?, ciudad=?, sueldo=?  where dni=?";
			try {
				Connection conexion = bd.getConex();
				PreparedStatement ps = conexion.prepareStatement(sentencia);
				ps.setString(1, nombre);
				ps.setString(2, apellidos);
				ps.setString(3, ciudad);
				ps.setInt(4, sueldo);
				ps.setInt(5, dni);
				ps.executeUpdate();
				ps.close();
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		} else {
			ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmpleado);
			Empleado empleado = new Empleado (dni, null, null, 0,null);
			ObjectSet<Empleado> result = db.queryByExample(empleado);
			if (result.size() == 0)
				JOptionPane.showInputDialog(this, "No existe el registro");
			else {
				Empleado existe = (Empleado)result.next();
				existe.setNombre(nombre);
				existe.setApellidos(apellidos);
				existe.setCiudad(ciudad);
				existe.setSueldo(sueldo);
				db.store(existe);
			}
			db.close();
		}
	}
	public void consultar() {		
		limpiar(true);
		int dni = Integer.parseInt(CajaDni.getText());
		if(opcion < 3) {
			BD bd = new BD(opcion);
			try {
				
				String sentencia = "select * from empleados where dni=?";				
				Connection conexion = bd.getConex();
				PreparedStatement ps = conexion.prepareStatement(sentencia);
				ps.setInt(1, dni);
				ResultSet rs = ps.executeQuery();
				
				if (rs.next()) {
					CajaNombre.setText(rs.getString("nombre"));
					CajaApellidos.setText(rs.getString("apellidos"));
					CajaCiudad.setText(rs.getString("ciudad"));
					CajaSueldo.setText(rs.getString("Sueldo"));

					
				} else {
					JOptionPane.showInputDialog(this, "No existe el registro");
				}
				ps.close();
				conexion.close();				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), BDEmpleado);
			Empleado empleado = new Empleado (dni, null, null, 0,null);
			ObjectSet<Empleado> result = db.queryByExample(empleado);
			if (result.size() == 0)
				JOptionPane.showInputDialog(this, "No existe el registro");
			else {
				Empleado existe = (Empleado)result.next();
				CajaNombre.setText(existe.getNombre());
				CajaApellidos.setText(existe.getApellidos());
				CajaCiudad.setText(existe.getCiudad());
				CajaSueldo.setText(String.valueOf(existe.getSueldo()));
				
			
				
			}
			db.close();
		}
	}
	public void guardar() {
		//Guardar en un fichero binario
		try {
			File fichero=new File("empleados.dat");
			FileOutputStream fileout;
			DataOutputStream dataOS;
			if(fichero.exists()==false) {
				fileout=new FileOutputStream(fichero);
				dataOS=new DataOutputStream(fileout);
			}else {
				fileout=new FileOutputStream(fichero,true);
				dataOS=new DataOutputStream(fileout);
			}
			String dni=CajaDni.getText();
			String nombre=CajaNombre.getText();
			String apellidos=CajaApellidos.getText();
			String ciudad=CajaCiudad.getText();
			String sueldo=CajaSueldo.getText();
			dataOS.writeUTF(dni);
			dataOS.writeUTF(nombre);
			dataOS.writeUTF(apellidos);
			dataOS.writeUTF(ciudad);
			dataOS.writeUTF(sueldo);
			dataOS.close();
		}catch(IOException e) {}
	}
	
	static void CrearElemento(String datoEmpleado,String valor,Element raiz,Document document){
		Element elem=document.createElement(datoEmpleado);//creamos hijo
		Text text=document.createTextNode(valor);//damos valor
		raiz.appendChild(elem);//pegamos el elemento hijo a la raiz
		elem.appendChild(text);//pegamos el valor
	}

public void crearDOM() throws IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
File fichero;
FileInputStream filein;
DataInputStream dataIS=null;
DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();

fichero=new File("empleados.xml");
filein=new FileInputStream(fichero);
dataIS=new DataInputStream(filein);
DocumentBuilder builder=factory.newDocumentBuilder();
DOMImplementation implementation=builder.getDOMImplementation();
Document document=implementation.createDocument(null, "Empleados", null);
document.setXmlVersion("1.0");//asignamos la version de nuestro XML
 
try { 
 
while(true) {
	String dni=dataIS.readUTF();
	String nombre=dataIS.readUTF();
	String apellidos=dataIS.readUTF();
	String ciudad=dataIS.readUTF();
	String sueldo=dataIS.readUTF();
	
	 Element raiz = document.createElement("empleado");
     document.getDocumentElement().appendChild(raiz);
     CrearElemento("dni", dni.trim(), raiz, document);
     CrearElemento("nombre", nombre.trim(), raiz, document);
     CrearElemento("apellidos", apellidos.trim(), raiz, document);
     CrearElemento("ciudad", ciudad.trim(), raiz,document);
     CrearElemento("sueldo", sueldo.trim(), raiz,document);
     
    
}

}catch(EOFException e) {}
Source s=new DOMSource(document);
 Result result = new StreamResult(new java.io.File("empleadoDOM.xml"));
 Transformer transformer = TransformerFactory.newInstance().newTransformer();
 transformer.transform(s, result);

dataIS.close();
}
}
