# Examen Recuperar; Apuntes!!

Vamos a realizar una interfaz para guardar datos de un empleado. Los datos guardados son:
- dni
- nombre
- apellidos
- ciudad
- sueldo

Operaciones vamos a realizar: 
- Alta 
- Bajas 
- Modificaciones 
- consultas por dni
- Ver __TODOS__ los registros en una tabla!

Tendremos un Desplegable con 4 opciones:
- MySQL
- Access
- DB40
- Derby

Con este desplegable vamos a elegir el tipo de base de datos que vamos a utilizar.

- a) Guardar los datos en un fichero de objetos (no usar serializacioón).
- b) Guardar en un fichero xml utilizando tecnología DOM los datos de un empleado cuyo sueldo sea superior a uno pasado como parámetro y a la vez se visualizará en una tabla usando tecnología SAX.

NOTA EJEMPLO: String cad= JOptionPanel.showInputDialog(this, "Introducir Sueldo");

- c) Si elegimos MySQL la operación de Alta tenemos que realizarla utilizando CallableStatement (tendréis que crearos un procedimiento) y la operación de Baja la realizaremos utilizando PreparedStatement. El resto como queráis.
