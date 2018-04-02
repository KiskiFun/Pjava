
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class visualizarSAX extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tabla;
	DefaultTableModel dtm;

	/**
	 * 
	 * public static void main(String[] args) { try { visualizarSAX dialog = new
	 * visualizarSAX(); dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 */
	public visualizarSAX(JFrame jf, boolean b) throws FileNotFoundException, IOException, SAXException {
		super(jf, b);

		setTitle("Visualizar SAX");

		setBounds(100, 100, 679, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		String[] datos = { "Dni", "Nombre", "Apellidos", "Ciudad", "Sueldo" };
		dtm = new DefaultTableModel();
		tabla = new JTable(dtm);

		for (int i = 0; i < 5; i++) // Indicamos que contaremos hasta 5, por que tenemos 5 datos (Sería la array
									// "datos").
			dtm.addColumn(datos[i]);
		tabla.setPreferredScrollableViewportSize(new Dimension(350, 70));
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(20, 36, 604, 277);
		contentPanel.add(scrollPane);

		JLabel labConexion = new JLabel("");
		labConexion.setBounds(125, 11, 104, 14);
		contentPanel.add(labConexion);

		/*
		 * //Instanciamos la coneccion a la base de datos. String
		 * sql="select * from empleado"; Object[]data=new Object[5]; //Importante poner
		 * cuantas columnas queremos conseguir try { ResultSet rs=bd.selecciona(sql);
		 * while (rs.next()) { String dni=rs.getString("dni"); String
		 * nombre=rs.getString("nombre"); String ape=rs.getString("apellidos"); String
		 * ciudad=rs.getString("ciudad"); String sueldo=rs.getString("Sueldo");
		 * 
		 * //int ev=rs.getInt("apellidos"); ejemplo int por si el profe lo pidiera.
		 * 
		 * data[0]=dni; data[1]=nombre; data[2]=ape; data[3]=ciudad; data[4]=sueldo;
		 * dtm.addRow(data); } } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		//verTabla(String car);
		try {
			leerSAX();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void leerSAX() throws ParserConfigurationException, SAXException, IOException {
		
	       SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser saxParser = factory.newSAXParser();
	        GestionContenido gestor = new GestionContenido();
	        saxParser.parse("empleados.xml", gestor);
	        List<ObjetoSAX> list = gestor.getlSax();
	        
			Object[]data=new Object[5];
			int m=dtm.getRowCount();
			
			if (m>0)
				for(int i=0;i<m;i++)
					dtm.removeRow(0);
	        
      for (ObjetoSAX objSAX: list) {
        	/*System.out.println("DNI: " + objSAX.getDni());
            System.out.println("Nombre: " + objSAX.getNombre());
            System.out.println("Apellidos: " + objSAX.getApellidos());
            System.out.println("Ciudad: " + objSAX.getCiudad());
            System.out.println("Sueldo: " + objSAX.getSueldo());
            System.out.println("---------------------------------------------------------------------------------------------");
            */
			 data[0]=objSAX.getDni();
			 data[1]=objSAX.getNombre();
			 data[2]=objSAX.getApellidos();
			 data[3]=objSAX.getCiudad();
			 data[4]=objSAX.getSueldo();
			 dtm.addRow(data);
      }
	}

	public static class GestionContenido extends DefaultHandler {
		
		private boolean dni,nombre,apellidos,ciudad,sueldo;
		
		private ObjetoSAX verSAX = new ObjetoSAX();
		private List<ObjetoSAX> lSax = new ArrayList<>();


	/*	public void startDocument() {
			System.out.println("Comienzo del documento XML");
		}

		public void endDocument() {
			System.out.println("Fin del documento XML");
		}*/
		@Override
		public void startElement(String uri, String nombreX, String nombreC, Attributes atts) throws SAXException {
			 if (nombreC.equals("Dni")) {
		            dni = true;
		        }
		        
			 if (nombreC.equals("Nombre")) {
		           	nombre = true;
		        }
			 if (nombreC.equals("Apellidos")) {
		            apellidos = true;
		        }
			 if (nombreC.equals("Ciudad")) {
		            ciudad = true;
		        }
			 if (nombreC.equals("Sueldo")) {
		            sueldo = true;
		        }		
			//System.out.println("\tInicio ElementoURI: " + nombreC);
		}

		@Override
		public void characters(char[] ch, int inicio, int longitud) throws SAXException {
			/*String car = new String(ch, inicio, longitud);
			car = car.replaceAll("[\t\n]", ""); // quitar saltos de línea
			System.out.println("\tCaracteres: " + car);*/
			
			if(dni) {
				verSAX.setDni(new String(ch, inicio, longitud));
				dni=false;
			}
			if(nombre) {
				verSAX.setNombre(new String(ch, inicio, longitud));
				nombre=false;
			}
			if(apellidos) {
				verSAX.setApellidos(new String(ch, inicio, longitud));
				apellidos=false;
			}
			if(ciudad) {
				verSAX.setCiudad(new String(ch, inicio, longitud));
				ciudad=false;
			}
			if(sueldo) {
				verSAX.setSueldo(new String(ch, inicio, longitud));
				sueldo=false;
			}
			
	       /*if (calories) {
	            currentFood.setCalories(Integer.parseInt(new String(ch, start, length)));
	            calories = false;
	        }*/
			
	        }
		
		@Override		
		public void endElement(String uri, String nombre, String nombreC) {
			// String nom = nombre;
			//System.out.println("\tFin Elemento: " + nombre);
			if(nombreC.equals("Empleado")) {
				lSax.add(verSAX);
				verSAX = new ObjetoSAX();
			}
		}
		
		public List<ObjetoSAX> getlSax(){
			return lSax;
		}

	}
	
	
	/*public enum obtenerElementos {
	    EMPLEADO("empleado"), DNI("dni"), NOMBRE("nombre"), APELLIDOS("apellidos"), CIUDAD("ciudad"), SUELDO("sueldo");
		
	    private String obt;
	 
	    private obtenerElementos(String obt) {
	        this.obt = obt;
	    }
	 
	    public String getObt() {
	        return obt;
	    }*/

	public void verTabla(String car) {
       /* String dos [] = new String[6];
		int e=0;
		
        do {
        	dos[e]=car;
        e++;
        }while(e>6);
		
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        for (int f =0; f <dos.length; f++) {
        	System.out.print(dos[f]+", ");
        }
        
		Object[] data = new Object[5];
		int m = dtm.getRowCount();

		// vaciamos la tabla si esta cargada
		if (m > 0)
			for (int i = 0; i < m; i++)
				dtm.removeRow(0);
		dtm.addRow(data);
		*/
	}
	
}
