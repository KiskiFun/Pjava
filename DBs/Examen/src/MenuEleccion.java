import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;


public class MenuEleccion extends JFrame {

	private JPanel contentPane;
	private JTextField eNombre;
	private JTextField eApellidos;
	private JTextField eCiudad;
	private JTextField eSueldo;
	private JTextField eDni;
	private JComboBox comboEleccion;
	private JComboBox comboSelec;
	final static String BDEmp="lib/DBEmple.yap";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MenuEleccion frame = new MenuEleccion();
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
	

	
	public MenuEleccion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 521);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDni = new JLabel("Dni:");
		lblDni.setBounds(50, 57, 108, 14);
		contentPane.add(lblDni);
		
		eDni = new JTextField();
		eDni.setColumns(10);
		eDni.setBounds(168, 52, 147, 20);
		contentPane.add(eDni);
		
		JLabel label = new JLabel("Nombre:");
		label.setBounds(50, 99, 108, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Apellidos:");
		label_1.setBounds(50, 146, 108, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Direccion:");
		label_2.setBounds(50, 190, 108, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Sueldo:");
		label_3.setBounds(50, 236, 108, 14);
		contentPane.add(label_3);
		
		eNombre = new JTextField();
		eNombre.setColumns(10);
		eNombre.setBounds(168, 96, 147, 20);
		contentPane.add(eNombre);
		
		eApellidos = new JTextField();
		eApellidos.setColumns(10);
		eApellidos.setBounds(168, 143, 147, 20);
		contentPane.add(eApellidos);
		
		eCiudad = new JTextField();
		eCiudad.setColumns(10);
		eCiudad.setBounds(168, 187, 247, 20);
		contentPane.add(eCiudad);
		
		eSueldo = new JTextField();
		eSueldo.setColumns(10);
		eSueldo.setBounds(168, 233, 99, 20);
		contentPane.add(eSueldo);

		
		comboSelec = new JComboBox();
		comboSelec.setModel(new DefaultComboBoxModel(new String[] {"MySQL", "Access", "DB40", "DERBY"}));
		comboSelec.setBounds(182, 283, 221, 20);
		contentPane.add(comboSelec);
		
		JButton bModificar = new JButton("Modificar");
		bModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String loc =(String) comboSelec.getSelectedItem();
				if(loc=="MySQL") {	
					mysqlModificar();
				}else if(loc=="Access") {
					accessModificar();
				}else if(loc=="DB40") {
					db40Modificar();
				}else if(loc=="Derby") {
					derbyModificar();
				}
			}
		});
		bModificar.setBounds(326, 349, 89, 23);
		contentPane.add(bModificar);
		
		JButton bBaja = new JButton("Baja");
		bBaja.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String loc =(String) comboSelec.getSelectedItem();
				if(loc=="MySQL") {	
					mysqlBaja();
				}else if(loc=="Access") {
					accessBaja();
				}else if(loc=="DB40") {
					db40Baja();
				}else if(loc=="Derby") {
					derbyBaja();
				}
				
			}
		});
		bBaja.setBounds(182, 348, 97, 25);
		contentPane.add(bBaja);
		
		JButton bConsultasPorDni = new JButton("Ver Registros");
		bConsultasPorDni.addActionListener(new ActionListener() {
			
		
			public void actionPerformed(ActionEvent arg0) {
				//VER EL EJEMPLO DEL COLEGIO, EJECUTARLO Y MIRAR SI TIENE TABLAS, SI TIENE VEREMOS COMO FUNCION Y LO AÑADIREMOS AQUI.
				//LA PARTE A B Y C ES OTRA PARTE DEL PROGRAMA. (Ver Ficheros).
				//No olvidarse a la hora de crear el JDialog nuevo en el content poner la posicion en absolute, para que podamos poner los widgets den la posicion que queramos.
				
				String elegir = comboSelec.getSelectedItem().toString();
				tablasEmpleados tablas=new tablasEmpleados(null, true,elegir,"No", "");
				tablas.setVisible(true);
			}
		});
		bConsultasPorDni.setBounds(250, 436, 165, 25);
		contentPane.add(bConsultasPorDni);
		
		JLabel lblGuardarEn = new JLabel("Guardar en:");
		lblGuardarEn.setBounds(50, 286, 108, 14);
		contentPane.add(lblGuardarEn);
		
		JButton bAlta = new JButton("Alta");
		bAlta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccionAlta();
			} 
		});
		bAlta.setBounds(35, 348, 97, 25);
		contentPane.add(bAlta);
		
		JLabel lblinsertarSloDni = new JLabel("(Insertar s\u00F3lo Dni para dar de Baja)");
		lblinsertarSloDni.setBounds(148, 32, 220, 14);
		contentPane.add(lblinsertarSloDni);
		
		JButton bBusqueda = new JButton("Buscar por DNI");
		bBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Si quiero pasar el valor de la cadena por parametro tengo que hacerle una referencia en el metodo del destino. Entonces ahí la recojo como hice con Listar todas las tablas.
				//String cad= JOptionPane.showInputDialog(null, "Introducir DNI", "Búsqueda por DNI", JOptionPane.INFORMATION_MESSAGE); //Null o This.
				//Como obtener el dato del option por input, declaramos una variable aaaa y la variable cad como en el ejercicio anterior, obtendrá los datos que le he ingresado por pantalla.
				String cad= JOptionPane.showInputDialog(null, "Introducir DNI", "Búsqueda por DNI", JOptionPane.INFORMATION_MESSAGE);
				String dniBuscar="buscarDNI";
				tablasEmpleados tablas=new tablasEmpleados(null, true,null, dniBuscar, cad);
				tablas.setVisible(true);
			}
		});
		bBusqueda.setBounds(35, 437, 170, 23);
		contentPane.add(bBusqueda);
		
		JButton btnNewButton = new JButton("Guardar Fichero Obj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				escritura();
			}
		});
		btnNewButton.setBounds(33, 403, 147, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Guardar DOM XML Y Ver SAX");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//DOM XML
						guardarDOM();
			
			}
		});
		btnNewButton_1.setBounds(208, 403, 234, 23);
		contentPane.add(btnNewButton_1);
	}
	
	public void eleccionAlta() {
		
		String seleccion = (String) comboSelec.getSelectedItem();
		
		if(seleccion=="MySQL") {	
			mysqlInsert();
		}else if(seleccion=="Access") {
			accessInsert();
		}else if(seleccion=="DB40") {
			db40Insert();
		}else if(seleccion=="Derby") {
			derbyInsert();
		}
	}
	
	//Metodo para Insertar un Dato de MySQL
	public void mysqlInsert() {
		
		BD_Mysql bd=new BD_Mysql();
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		String sql="INSERT INTO empleado (dni, nombre, apellidos, ciudad, sueldo) values (?, ?, ?, ?, ?)";
		
		Connection conexion=bd.getConex();
		try {
			CallableStatement llamada=conexion.prepareCall(sql);
			llamada.setString(1, dni);
			llamada.setString(2, nombre);
			llamada.setString(3, apellidos);
			llamada.setString(4, ciudad);
			llamada.setString(5, sueldo);
			llamada.executeUpdate();
			llamada.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		limpiar();
	}
	
	//Metodo para borrar un Dato de MySQL
	public void mysqlBaja() {
		BD_Mysql bd=new BD_Mysql();
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		String sql="DELETE From empleado WHERE dni = ?";
		Connection conexion=bd.getConex();
		try {
			PreparedStatement ps=conexion.prepareStatement(sql);
			ps.setString(1, dni);
			ps.executeUpdate();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		limpiar();	
	}
	
	public void mysqlModificar() {
		
		BD_Mysql bd=new BD_Mysql();
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		String sql="UPDATE empleado SET dni = ?, nombre = ?, apellidos = ?, ciudad = ?, sueldo = ?"
                + " WHERE dni = ?";
		Connection conexion=bd.getConex();
		try {
			CallableStatement llamada=conexion.prepareCall(sql);
			llamada.setString(1, dni);
			llamada.setString(2, nombre);
			llamada.setString(3, apellidos);
			llamada.setString(4, ciudad);
			llamada.setString(5, sueldo);
			
			llamada.setString(6, dni);
			llamada.executeUpdate();
			llamada.close();
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		limpiar();
	}
	
	public void accessInsert() {
		DB_Access db_acs;
		db_acs = new DB_Access();
		
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		String sentencia="INSERT INTO empleado(dni,nombre,apellidos,ciudad,sueldo) VALUES("+
		"'"+dni+"','"+nombre+"','"+apellidos+"','"+ciudad+"','"+sueldo+"') ";
		//Si no lee bien, ir a: Archivo> Opciones > Genearl : Criterio de ordenacion de nueva base de datos:
		db_acs.actualiza(sentencia); 
		limpiar();
	}
	
	public void accessBaja() {
		DB_Access db_acs;
		db_acs = new DB_Access();
		String dni=eDni.getText();
			
		//Si quisiera poner una ventana emergente donde introduzco datos y quiero borrar:
//alert.		String cod=JOptionPane.showInputDialog(null, "Codigo a borrar", "Borrado", 1);

		//Vigilar con las comillas si se ponen mal puede no ejecutarse bien la consulta.
		
		String sentencia="Delete * from empleado where dni="+ "'"+dni+"'";
		db_acs.actualiza(sentencia);
	}
	
	public void accessModificar() {
		DB_Access db_acs;
		db_acs = new DB_Access();
		//Si quisiera ejecutar la modificacion por cualquier campo necesitaria este codigo. OJO!!!!
		//String cod=JOptionPane.showInputDialog(null, "Codigo a modificar", "Modificar", 1);
		
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		String sentencia="Update empleado set dni='"+dni+"',nombre='"+nombre+"',apellidos='"+
				apellidos+"',ciudad='"+ciudad+"',sueldo="+sueldo+" where dni="+ "'"+dni+"'";
		db_acs.actualiza(sentencia);
		
	}
	
	public void db40Insert(){

		
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		//Crear Objeto luego para poder relacionarlo con esta clase.
		
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDEmp);
		//Objeto emp=new Objeto(0,null,null,null,0); Si el profesor quisiera la base de datos en integer. 
		Objeto alum=new Objeto(null,null,null,null,null);
		ObjectSet<Objeto>result=db.queryByExample(alum);
		
		Objeto al=new Objeto(dni,nombre,apellidos,ciudad,sueldo);
		
		db.store(al);
		db.close(); 
		
		limpiar();
		
	}
	
	public void db40Baja() {
		
		//String dni=JOptionPane.showInputDialog(null, "Codigo a borrar", "Borrado", 1);
		//Así se borra por DNI. Obtengo el campo del dni, y se lo concateno al objeto entero.
		String dni = eDni.getText();
		
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDEmp);
		
		Objeto emp=new Objeto(dni,null,null,null,null);
		ObjectSet<Objeto>result=db.queryByExample(emp);
		if (result.size()==0)System.out.println("No existe");
		else
		{
			Objeto existe=(Objeto)result.next();
			db.delete(existe);
		}
		db.close();
		
	}
	
	public void db40Modificar() {
		//Igual que en el método de dar la baja, como borramos por DNI, pues eso.
		//String cod=JOptionPane.showInputDialog(null, "Codigo a modificar", "Modificar", 1);
		
		
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		ObjectContainer db=Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),BDEmp);
		Objeto emp=new Objeto(dni,null,null,null,null);
		ObjectSet<Objeto>result=db.queryByExample(emp);
		if (result.size()==0)System.out.println("No existe");
		else
		{
			Objeto existe=(Objeto)result.next();
			
			existe.setDni(dni);
			existe.setNombre(nombre);
			existe.setApellidos(apellidos);
			existe.setCiudad(ciudad);
			existe.setSueldo(sueldo);
			
			db.store(existe);
		}
		db.close();
	}
	
	public void derbyInsert(){
		
	}
	
	public void derbyBaja() {
		
	}
	
	public void derbyModificar() {
		
	}
	
	public void guardarDOM(){
    	String nombre_archivo = "empleados";
    	
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();
		
		
		

        if(!(dni.equals("") || nombre.equals(""))){
        	try {
        		int suel = Integer.parseInt(eSueldo.getText());
        		String sueldoComparar= JOptionPane.showInputDialog(this, "Introducir Sueldo 500");
        		int sueldCompare = Integer.parseInt(sueldoComparar);
        		
        		if(suel>sueldCompare){
        			JOptionPane.showMessageDialog(this, "El sueldo es superior, se guardará, Mostrando datos por SAX!");
        			generarDOM(nombre_archivo, dni, nombre, apellidos, ciudad, sueldo);
        			visualizarSAX versax=new visualizarSAX(null, true);
        			versax.setVisible(true);
        		}else 
				JOptionPane.showMessageDialog(this, "El sueldo es inferior, no se guardará.");

			
        	}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else {
        	JOptionPane.showMessageDialog(this, "Los campos están vacios, ingrese Datos y un sueldo Superior a 500!");
            }
    }

	
    public static void generarDOM(String name, String dni, String nombre, String apellidos, String ciudad, String sueldo) throws Exception{
    	

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null, name, null);
            document.setXmlVersion("1.0");

            //Main Node
            Element raiz = document.getDocumentElement();
            
            //Por cada key creamos un item que contendrá la key y el value
            	
                //Empleado Node
                Element empleadoNode = document.createElement("Empleado"); 
                
                //DNI Node
                Element dniNode = document.createElement("Dni"); 
                Text nodeDniValue = document.createTextNode(dni);
                dniNode.appendChild(nodeDniValue);   
                
                //Nombre Node
                Element nombreNode = document.createElement("Nombre"); 
                Text nodeNombreValue = document.createTextNode(nombre);                
                nombreNode.appendChild(nodeNombreValue);
                
                //Apellido Node
                Element apeNode = document.createElement("Apellidos"); 
                Text nodeApeValue = document.createTextNode(apellidos);                
                apeNode.appendChild(nodeApeValue);
                
                //Ciudad Node
                Element ciudadNode = document.createElement("Ciudad"); 
                Text nodeCiudadValue = document.createTextNode(ciudad);                
                ciudadNode.appendChild(nodeCiudadValue);
                
                //Sueldo Node
                Element sueldoNode = document.createElement("Sueldo"); 
                Text nodeSueldoValue = document.createTextNode(sueldo);                
                sueldoNode.appendChild(nodeSueldoValue);
                
                //append keyNode and valueNode to itemNode
                empleadoNode.appendChild(dniNode);
                empleadoNode.appendChild(nombreNode);
                empleadoNode.appendChild(apeNode);
                empleadoNode.appendChild(ciudadNode);
                empleadoNode.appendChild(sueldoNode);
                
                //append itemNode to raiz
                raiz.appendChild(empleadoNode); //pegamos el elemento a la raiz "Documento"
                
            //Generate XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(name+".xml")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        

    }
    public void escritura() {
		String dni=eDni.getText();
		String nombre=eNombre.getText();
		String apellidos=eApellidos.getText();
		String ciudad=eCiudad.getText();
		String sueldo=eSueldo.getText();

		
    	objetoFichero ao = new objetoFichero();
    	ao.objetoFichero(new Objeto(dni,nombre,apellidos,ciudad,sueldo));
    	
    	limpiar();
    	
    	
    }

	
	public void asignarvalor() {
		String seleccion  = (String) comboSelec.getSelectedItem();
		obIns ob = new obIns(seleccion);
		}
	
	public void limpiar() {
		eDni.setText("");
		eNombre.setText("");
		eApellidos.setText("");
		eCiudad.setText("");
		eSueldo.setText("");
	}
}
