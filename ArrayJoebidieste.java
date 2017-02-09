import java.util.Scanner;

public class ArrayJoebidieste {

        public static int[][] darNotas() {

        int gNumNotas;
        int gNumAlumnos;
        Scanner teclado = new Scanner (System.in);

        System.out.println("¿Cuantas alumnos quiere ingresar? ");
        gNumAlumnos=teclado.nextInt();

        System.out.println("¿Cuantas notas quiere ingresar? ");
        gNumNotas=teclado.nextInt();


        int[][] notasTemp = new int[gNumAlumnos][gNumNotas+1]; //Tengo esta array y le doy un tamaño a gNumNotas por teclado para luego.
                                      //Recorrer por el for de i hasta gNumAlumnos y luego las relleno con random numbers.
        for (int i = 0; i < gNumAlumnos; i++) {

            for (int j = 0; j < gNumNotas; j++) { //hasta -1 que es el campo de las notas válido el siguiente será el campo vacío para meterle la media
                notasTemp[i][j]= (int) (Math.random() * 1000) / 100;
            }

        }
        return notasTemp; //devuelvo las notas por la variable de la array creada en este método.
    }



    public static void mostrarNotas(int[][] mNotas) { //Aquí cojo el valor del main de int[] notas; y lo utilizo en le método.

        for (int i = 0; i < mNotas.length; i++) {
            System.out.println("Alumno Nº "+i);
            for (int j = 0; j < mNotas[0].length; j++) {
                System.out.print(mNotas[i][j]+" "); //Con este for mostramos todas las notas recorremos toda la array.
            }
            System.out.println("");
        }
    }

    public static int[][] mediaNotas(int[][]mediNotas){
      int tNotas = 0;
      int cont = 0;
            System.out.println("");
          //  System.out.println("----------Notas media----------- : ");
      for (int i = 0; i < mediNotas.length; i++) {
          //esto es el alumno
          for (int j = 0; j < mediNotas[0].length-1; j++) {
              //tNotas = mediNotas[i][j];
              tNotas+= mediNotas[i][j]; //Sumo las notas en tNotas para luego hacer el calculo fuera
              cont+= 1; //Esto es para contar el bucle de las notas y lo sumo en cont.

          }
            tNotas=tNotas/cont;
            mediNotas[i][mediNotas[0].length-1]=tNotas;

            System.out.println("Alumno : "+i+" Notas medias :"+mediNotas[i][mediNotas[0].length-1]);
            cont = 0;
            tNotas = 0;
            System.out.println("");
      }
      return mediNotas;
    }

/*
    public static void ordenarInterBi(int[][] numeros) {
        //int[]notasaux;

        for (int i = 0; i < numeros.length; i++) {
            numeros[i]  = ordenarIntercambio(numeros[i]);
            //numeros[i]  = ordenarBurbuja(numeros[i]);
        }

        System.out.println("Mostrar Notas ordenadas Intercambio : ");
        mostrarNotas(numeros);
    }

    public static int[] ordenarIntercambio(int[] numeros) {
    int can;

    for(int p=0;p<=numeros.length-3;p++){
        for(int h=numeros.length-3;h>=p;h--){
          can=numeros[h];

            if(numeros[h]>numeros[h+1]){
              numeros[h]=numeros[h+1];
              numeros[h+1]=can;
            }
        }

    }
    return numeros;
    }*/

    public static int[][] ordenarAlumno(int[][]alumDev) {

        int ant[];
        int posUlti= alumDev[0].length-1;

        for (int i = 0; i < alumDev.length-2; i++) {
            for (int j = alumDev.length-2; j>=i; j--) {
                ant=alumDev[j]; //cogemos todas las notas del alumno i
                if (alumDev[j][posUlti]>alumDev[j+1][posUlti]){
                    alumDev[j]=alumDev[j+1];
                    alumDev[j+1]=ant;
                }
            }
          //  System.out.println("Alumno : "+i+ " nota : "+alumDev[i]);

        }
      //  System.out.println("--------Notas Medias Ordenadas:---------");
            return alumDev;
    }
    public static void menu() {

      System.out.println("\n\t\033[4mMenu\033[0m\n");
      System.out.println("  1. Entrar Alumnos y Notas");
      System.out.println("  2. Mostrar Notas");
      System.out.println("  g3. Mostrar la Media de Notas");
      System.out.println("  4. Ordenar Media de Notas");
      System.out.println("  5. Salir\n");
g
    }


    public static void main(String[] args) {
        // TODO code application logic here
        int [][] notas=null;
        Scanner teclado = new Scanner(System.in);
        int op;

        do{

          menu();
          op=teclado.nextInt();

          switch(op){

            case 1: notas = darNotas(); break;
            case 2: mostrarNotas(notas);break;
            case 3: notas = mediaNotas(notas);break;
            case 4: notas = ordenarAlumno(notas);mediaNotas(notas);break;
          }
      }while(op!=5);

    }

}
