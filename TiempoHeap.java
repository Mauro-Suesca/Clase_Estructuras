import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class TiempoHeap{
    private static Heap<Integer> heap;
    private static ChronoUnit unidad_tiempo;
    private static int casos;
    private static final int INCREMENTO = 5, MIN_PRUEBAS = 10000;

    public static void main(String[] args){
        long[] add_time, remove_time, extra_time;
        int n;

        n = MIN_PRUEBAS;
        heap = new Quaternary_heap<>(n);

        set_casos(true);

        add_time = new long[casos];
        extra_time = new long[casos];
        remove_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){

            //Add
            add_time[i] = probar_add(n);

            //Sort
            extra_time[i] = probar_check(n);

            //Remove
            remove_time[i] = probar_remove(n);

            n *= INCREMENTO;
        }

        mostrar_tiempo(add_time, "Insert");
        mostrar_tiempo(extra_time, "heap_sort");
        mostrar_tiempo(remove_time, "Extract_max");
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
            heap.insert(i);
        }
        
        return inicio.until(Instant.now(), unidad_tiempo);
    }

    private static long probar_check(int iteraciones){
        Instant inicio;
        Integer[] array = new Integer[iteraciones];
        Random generador = new Random();
        for(int i=0; i<iteraciones; i++){
            array[i] = generador.nextInt();
        }

        inicio = Instant.now();
        heap.heap_sort(array);

        return inicio.until(Instant.now(), unidad_tiempo);
    }

    private static long probar_remove(int iteraciones) throws Invalid_size_operation{
        Instant inicio;
        inicio = Instant.now(); 
        for(int i=0; i<iteraciones; i++){
            heap.extract_max();
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