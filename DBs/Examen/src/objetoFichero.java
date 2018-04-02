import java.io.*;

public class objetoFichero {
	
    public void objetoFichero(Objeto empleado){
	//Guardar en un fichero binario
	    try {
	        File fichero=new File("empleados.txt");
	        FileOutputStream fileout;
	        DataOutputStream dataOS;
	        if(fichero.exists()==false) {
	            fileout=new FileOutputStream(fichero);
	            dataOS=new DataOutputStream(fileout);
	        }else {
	            fileout=new FileOutputStream(fichero,true);
	            dataOS=new DataOutputStream(fileout);
	        }
	    
	        dataOS.writeUTF(empleado.getDni());
	        dataOS.writeUTF(empleado.getNombre());
	        dataOS.writeUTF(empleado.getApellidos());
	        dataOS.writeUTF(empleado.getCiudad());
	        dataOS.writeUTF(empleado.getSueldo());
	        dataOS.close();
	    }catch(IOException e) {}
    }
}
