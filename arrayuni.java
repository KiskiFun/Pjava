import java.util.Scanner;

 class arrayuni {


    public static int[] darNotas() {

        int gNumNotas;
        Scanner teclado = new Scanner (System.in);

        System.out.println("¿Cuantas notas quiere ingresar? ");
        gNumNotas=teclado.nextInt();

        int[] notasTemp = new int[gNumNotas]; //Tengo esta array y le doy un tamaño a gNumNotas por teclado para luego.
                                              //Recorrer por el for de i hasta gNumNotas y luego las relleno con random numbers.
        for (int i = 0; i < gNumNotas; i++) {
            notasTemp[i]= (int) (Math.random() * 1000) / 100;
        }

        return notasTemp; //devuelvo las notas por la variable de la array creada en este método.
    }

    public static void mostrarNotas(int[] mNotas) { //Aquí cojo el valor del main de int[] notas; y lo utilizo en le método.

        for (int i = 0; i < mNotas.length; i++) {
            System.out.print(mNotas[i]+" "); //Con este for mostramos todas las notas recorremos toda la array.

        }
        System.out.println("\n");
    }

    public static void ordenarIntercambio(int[] numeros) {
        int can=0;
        for(int p=0;p<=numeros.length-2;p++){
            for(int h=numeros.length-2;h>=p;h--){
              can=numeros[h];
                if(numeros[h]>numeros[h+1]){
                  numeros[h]=numeros[h+1];
                  numeros[h+1]=can;
                }
            }
        }
        System.out.println("Sus notas han sido modificadas con el Metodo de Intercambio");
        mostrarNotas(numeros);
    }



    public static void ordenarBurbuja(int[] numeros) {
        boolean ok=false;
        do{
            ok=true;
            for(int p=0;p<numeros.length-1;p++){

              if(numeros[p]>numeros[p+1]){
                int aux = numeros[p];
                numeros[p]=numeros[p+1];
                numeros[p+1]=aux;
                ok=false;
              }
            }
        }while(ok==false);
        System.out.println("Sus notas han sido modificadas con el Metodo de Burbuja");
        mostrarNotas(numeros);
    }

    public static void ordenarShell(int[] numeros) {
        int fin=0, ini=0, l1=0, l2=0;
        int can=0;
        boolean co;
        fin=numeros.length-1;

        do{
          co=true;
          ini=numeros.length-1;
            while(ini>1){
              ini=ini/2;
              for(l1=0;l1<=fin-ini;l1++){
                l2=l1+ini;
                  if(numeros[l2]<numeros[l1]){
                    can=numeros[l2];
                    numeros[l2]=numeros[l1];
                    numeros[l1]=can;
                    co=false;
                  }
              }
            }
        }while(co==false);
        System.out.println("Sus notas han sido modificadas con el Metodo utilizado Shell");
        mostrarNotas(numeros);
    }



    public static void main(String[] args) {
        // TODO code application logic here
        int[] notas;
        System.out.println("Hola,¿como estas? vamos a ordenar numeros guay : ");
        System.out.println("Introduce las notas.");

        notas = darNotas();

        System.out.println("Estiamdo usuario, tenemos las siguientes " +notas.length+" notas : ");


        mostrarNotas(notas);
        ordenarIntercambio(notas);
        ordenarBurbuja(notas);
        ordenarShell(notas);


    }

}
