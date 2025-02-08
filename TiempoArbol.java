import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TiempoArbol{
    private static Binary_tree<Integer> arbol;
    private static Lista_array_dinamico<Integer> elementos;
    private static ChronoUnit unidad_tiempo;
    private static int casos;
    private static final int INCREMENTO = 5, MIN_PRUEBAS = 10000;
    public static void main(String[] args){
        long[] add_time, remove_time, extra_time;
        int n, max;

        n = MIN_PRUEBAS;
        max = (int)(n*Math.pow(INCREMENTO, 5));
        
        arbol = new Binary_tree<>();
        elementos = new Lista_array_dinamico<>(Integer.class, n);
        Random generador = new Random();
        for(int i=0; i<max; i++){
            elementos.addLast(generador.nextInt());
        }

        set_casos(true);

        add_time = new long[casos];
        remove_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){

            //Add
            add_time[i] = probar_add(n);

            //Remove
            remove_time[i] = probar_remove(n);

            n *= INCREMENTO;
        }

        mostrar_tiempo(add_time, "Insert");
        mostrar_tiempo(remove_time, "Delete");

        /*
            Otros
        */

        Instant inicio;
        llenar_arbol(max);

        //find
        set_casos(true);
        extra_time = new long[casos];
        n = MIN_PRUEBAS;

        for(int i=0; i<casos; i++){
            inicio = Instant.now();
            for(int j=0; j<n; j++){
                arbol.find(elementos.get(j));
            }
            extra_time[i] = inicio.until(Instant.now(), unidad_tiempo);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "find");

        //Next
        set_casos(true);
        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,3);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "next");

        /*
        //Height
        set_casos(false);
        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,1);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "height");
        */

        //Size
        set_casos(false);
        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,2);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "size");
        
        //Otro size
        set_casos(false);
        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,4);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "size sin check para hoja");
    }

    private static void llenar_arbol(int cantidad){
        arbol = new Binary_tree<>();
        
        for(int i=0; i<cantidad; i++){
            arbol.insert(elementos.get(i));
        }
    }

    private static void mostrar_tiempo(long[] tiempos, String metodo){
        int n = MIN_PRUEBAS;
        for(int j=0; j<casos; j++){
            System.out.println("Método " + metodo + " iterado " + n + " veces duró: " + tiempos[j] + " " + tiempo_en_palabras(unidad_tiempo));
            n *= INCREMENTO;
        }
    }

    private static long probar_add(int iteraciones){
        Instant inicio;
        inicio = Instant.now(); //Para la prueba se hace el Instant.now() dentro del if para que el tiempo que tarde en evaluar la condición (por más pequeño que sea) no afecte al resultado
        for(int i=0; i<iteraciones; i++){
            arbol.insert(elementos.get(i));
        }
        
        return inicio.until(Instant.now(), unidad_tiempo);
    }

    /**
     * @param iteraciones el número de veces a probar el método
     * @param tipo 1 si se quiere height, 2 si se quiere size, 3 si se quiere next, 4 si se quiere size sin check para hoja
     * @return el tiempo en 'unidad_tiempo' que se demoró el método
     */
    private static long probar_check(int iteraciones, int tipo){
        Instant inicio;
        if(tipo == 1){
            llenar_arbol(iteraciones);
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                arbol.height();
            }
        }else if(tipo == 2){
            llenar_arbol(iteraciones);
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                arbol.size();
            }
        }else if(tipo == 3){
            Node_tree<Integer> nodo = new Node_tree<Integer>(Integer.MIN_VALUE);
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                nodo = (Node_tree<Integer>)arbol.Next(nodo);
            }
        }else{
            llenar_arbol(iteraciones);
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                arbol.size_no_check();
            }
        }
        return inicio.until(Instant.now(), unidad_tiempo);
    }

    private static long probar_remove(int iteraciones) throws Invalid_size_operation{
        Instant inicio;
        inicio = Instant.now();
        for(int i=0; i<iteraciones; i++){
            arbol.delete(elementos.get(i));
        }
        return inicio.until(Instant.now(), unidad_tiempo);
    }

    private static void set_casos(boolean corto){
        if(corto){
            casos = 5;
            unidad_tiempo = ChronoUnit.NANOS;
        }else{
            casos = 3;
            unidad_tiempo = ChronoUnit.SECONDS;
        }
    }

    private static String tiempo_en_palabras(ChronoUnit tiempo){
        if(tiempo == ChronoUnit.SECONDS){
            return "segundos";
        }else if(tiempo == ChronoUnit.NANOS){
            return "nanosegundos";
        }else{
            return "";
        }
    }
}