import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TiempoLista{
    private static final String ESC = "\u001B";
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Lista<Integer> list;
        Instant inicio, inicio_secundario, inicio_terciario;
        int n, casos, tipo, incremento;
        long[] add_time, remove_time, extra_time;
        ChronoUnit unidad_tiempo;

        //TODO Mover los métodos especiales a su propia serie de for

        incremento = 5;
        while(true){
            try{
                clear_screen();
                System.out.println("1. ArrayList");
                System.out.println("2. LinkedList (no tail)");
                System.out.println("3. LinkedList (with tail)");
                System.out.println("4. DoublyLinkedList");
                System.out.print("Ingresa el número de la lista que quieres probar: ");
                String opcion = input.nextLine().trim();
                if(opcion.equals("")){
                    break;
                }else{
                    tipo = Integer.valueOf(opcion);
                }
            }catch(NumberFormatException e){
                continue;
            }

            if(tipo == 1){
                casos = 5;
                unidad_tiempo = ChronoUnit.NANOS;
            }else if(tipo == 2){
                casos = 3;
                unidad_tiempo = ChronoUnit.SECONDS;
            }else if(tipo == 3){
                casos = 3;
                unidad_tiempo = ChronoUnit.SECONDS;
            }else{
                casos = 5;
                unidad_tiempo = ChronoUnit.NANOS;
            }
    
            //First
            add_time = new long[casos];
            extra_time = new long[casos];
            remove_time = new long[casos];
            n = 10000;
            for(int i=0; i<casos; i++){
    
                if(tipo == 1){
                    list = new Lista_array<>(Integer.class, n);
                }else if(tipo == 2){
                    list = new Lista_enlazada<>();
                }else if(tipo == 3){
                    list = new Lista_enlazada_cola<>();
                }else{
                    list = new Lista_doble_enlazada<>();
                }
    
                //Add
                inicio = Instant.now();
                for(int j=0; j<n; j++){
                    list.addLast(j);
                }
                add_time[i] = inicio.until(Instant.now(), unidad_tiempo);
    
                //TopBack
                inicio_secundario = Instant.now();
                for(int j=0; j<n; j++){
                    list.topBack();
                }
                extra_time[i] = inicio_secundario.until(Instant.now(), unidad_tiempo);
    
                //Remove
                inicio_terciario = Instant.now();
                for(int j=0; j<n; j++){
                    list.removeLast();
                }
                remove_time[i] = inicio_terciario.until(Instant.now(), unidad_tiempo);
                n *= incremento;
            }
    
            //Mostrar el tiempo
            n = 10000;
            for(int j=0; j<casos; j++){
                System.out.println("Método addLast con " + n + " elementos duró: " + add_time[j] + " " + tiempo_en_palabras(unidad_tiempo));
                n *= incremento;
            }
            n = 10000;
            for(int j=0; j<casos; j++){
                System.out.println("Método topBack iterado " + n + " veces con " + n + " elementos duró: " + extra_time[j] + " " + tiempo_en_palabras(unidad_tiempo));
                n *= incremento;
            }
            n = 10000;
            for(int j=0; j<casos; j++){
                System.out.println("Método removeLast con " + n + " elementos duró: " + remove_time[j] + " " + tiempo_en_palabras(unidad_tiempo));
                n *= incremento;
            }

            if(tipo == 1){
                casos = 3;
                unidad_tiempo = ChronoUnit.SECONDS;
            }else if(tipo == 2){
                casos = 5;
                unidad_tiempo = ChronoUnit.NANOS;
            }else if(tipo == 3){
                casos = 5;
                unidad_tiempo = ChronoUnit.NANOS;
            }else{
                casos = 5;
                unidad_tiempo = ChronoUnit.NANOS;
            }
    
            //Last
            add_time = new long[casos];
            extra_time = new long[casos];
            remove_time = new long[casos];
            n = 10000;
            for(int i=0; i<casos; i++){
                //Add
                if(tipo == 1){
                    list = new Lista_array<>(Integer.class, n);
                }else if(tipo == 2){
                    list = new Lista_enlazada<>();
                }else if(tipo == 3){
                    list = new Lista_enlazada_cola<>();
                }else{
                    list = new Lista_doble_enlazada<>();
                }
                inicio = Instant.now();
                for(int j=0; j<n; j++){
                    list.addFirst(j);
                }
                add_time[i] = inicio.until(Instant.now(), unidad_tiempo);
    
  
                //Find
                Lista_array<Integer> list_arr = ((Lista_array<Integer>)list);
                inicio_secundario = Instant.now();
                for(int j=0; j<n; j++){
                    list_arr.find(j);
                }
                extra_time[i] = inicio_secundario.until(Instant.now(), unidad_tiempo);

                //Remove
                inicio_terciario = Instant.now();
                for(int j=0; j<n; j++){
                    list.removeFirst();
                }
                remove_time[i] = inicio_terciario.until(Instant.now(), unidad_tiempo);
                

                n *= incremento;
            }
            //Mostrar el tiempo
            n = 10000;
            for(int j=0; j<casos; j++){
                System.out.println("Método addFirst con " + n + " elementos duró: " + add_time[j] + " " + tiempo_en_palabras(unidad_tiempo));
                n *= incremento;
            }
            n = 10000;
            for(int j=0; j<casos; j++){
                System.out.println("Método find iterado " + n + " veces con " + n + " elementos duró: " + extra_time[j] + " " + tiempo_en_palabras(unidad_tiempo));
                n *= incremento;
            }
            n = 10000;
            for(int j=0; j<casos; j++){
                System.out.println("Método removeFirst con " + n + " elementos duró: " + remove_time[j] + " " + tiempo_en_palabras(unidad_tiempo));
                n *= incremento;
            }

            System.out.println("Presiona 'ENTER' para continuar");
            input.nextLine();
        }
        input.close();
    }

    private static void clear_screen(){
        System.out.print(ESC + "[3J" + ESC + "[2J" + ESC + "[H" + ESC + "[0m");
        System.out.flush();
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
