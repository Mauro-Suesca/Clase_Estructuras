import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TiempoSet{
    private static Disjoint_set set;
    private static ChronoUnit unidad_tiempo;
    private static int casos;
    private static final int INCREMENTO = 10, MIN_PRUEBAS = 10000;

    public static void main(String[] args){
        long[] time;
        int n;

        set_casos(true);

        time = new long[casos];

        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){

            //Union
            time[i] = probar_check(n,1);

            n *= INCREMENTO;
        }

        mostrar_tiempo(time, "Union");

        set_casos(true);

        time = new long[casos];

        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){

            //Union by rank
            time[i] = probar_check(n, 2);

            n *= INCREMENTO;
        }

        mostrar_tiempo(time, "Union by rank");
    }

    private static void mostrar_tiempo(long[] tiempos, String metodo){
        int n = MIN_PRUEBAS;
        for(int j=0; j<casos; j++){
            System.out.println("Método " + metodo + " iterado " + n + " veces duró: " + tiempos[j] + " " + tiempo_en_palabras(unidad_tiempo));
            n *= INCREMENTO;
        }
    }

    /**
     * Prueba de tiempo para unión, crea el disjoint set por su cuenta
     * @param iteraciones El número de iteraciones que se van a realizar para la prueba
     * @param tipo 1 si es union, 2 si es union by rank
     * @return El tiempo que tardó la prueba
     */
    private static long probar_check(int iteraciones, int tipo){
        if(tipo == 1){
            set = new Disjoint_set(iteraciones);
        }else{
            set = new Disjoint_set_rank(iteraciones);
        }

        for(int i=1; i<=iteraciones; i++){
            set.make_set(i);
        }

        Instant inicio;

        inicio = Instant.now();
        
        for(int i=1; i<iteraciones; i++){
            set.union(i, i+1);
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