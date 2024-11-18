import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TiempoLista{
    public static void main(String[] args){
        Lista<Integer> list;
        Instant inicio, inicio_secundario, inicio_terciario;
        int n = 10000;
        long[] add_time, remove_time, extra_time;
        String[] nombre_metodos = {"addLast", "removeLast"};

        //First
        add_time = new long[5];
        remove_time = new long[5];
        for(int i=0; i<5; i++){
            //Add
            list = new Lista_array<Integer>(n);
            inicio = Instant.now();
            for(int j=0; j<n; j++){
                list.addLast(j);
            }
            add_time[i] = inicio.until(Instant.now(), ChronoUnit.NANOS);

            //Remove
            inicio_secundario = Instant.now();
            for(int j=0; j<n; j++){
                list.removeLast();
            }
            remove_time[i] = inicio_secundario.until(Instant.now(), ChronoUnit.NANOS);
            n *= 10;
        }
        //Mostrar el tiempo
        for(int i=0; i<nombre_metodos.length; i++){
            n = 10000;
            for(int j=0; j<5; j++){
                System.out.println("Método " + nombre_metodos[i] + " con " + n + " elementos duró: " + (i==0 ? add_time[j] : remove_time[j]) + " nanosegundos");
                n *= 10;
            }
        }

        //Last
        add_time = new long[3];
        extra_time = new long[3];
        remove_time = new long[3];
        n = 10000;
        for(int i=0; i<3; i++){
            //Add
            list = new Lista_array<Integer>(n);
            inicio = Instant.now();
            for(int j=0; j<n; j++){
                list.addFirst(j);
            }
            add_time[i] = inicio.until(Instant.now(), ChronoUnit.SECONDS);

            //Find
            inicio_secundario = Instant.now();
            for(int j=0; j<n; j++){
                ((Lista_Array_Inter<Integer>)list).find(j);
            }
            extra_time[i] = inicio_secundario.until(Instant.now(), ChronoUnit.SECONDS);
            
            //Remove
            inicio_terciario = Instant.now();
            for(int j=0; j<n; j++){
                list.removeFirst();
            }
            remove_time[i] = inicio_terciario.until(Instant.now(), ChronoUnit.SECONDS);
            n *= 10;
        }
        //Mostrar el tiempo
        n = 10000;
        for(int j=0; j<3; j++){
            System.out.println("Método addFirst con " + n + " elementos duró: " + add_time[j] + " segundos");
            n *= 10;
        }
        n = 10000;
        for(int j=0; j<3; j++){
            System.out.println("Método find iterado " + n + " veces con " + n + " elementos duró: " + extra_time[j] + " segundos");
            n *= 10;
        }
        n = 10000;
        for(int j=0; j<3; j++){
            System.out.println("Método removeFirst con " + n + " elementos duró: " + remove_time[j] + " segundos");
            n *= 10;
        }
    }
}
