import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TiempoLista{
    private static final String ESC = "\u001B";
    private static Lista<Integer> list;
    private static ChronoUnit unidad_tiempo;
    private static int casos;
    private static final short ARRAY_LIST = 1, LINKED_LIST = 2, LINKED_LIST_TAIL = 3, DOUBLY_LINKED_LIST = 4;
    private static final int INCREMENTO = 5, MIN_PRUEBAS = 10000;
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n, tipo;
        long[] add_time, remove_time, extra_time;

        while(true){
            try{
                clear_screen();
                System.out.println(ARRAY_LIST + ". ArrayList");
                System.out.println(LINKED_LIST + ". LinkedList (no tail)");
                System.out.println(LINKED_LIST_TAIL + ". LinkedList (with tail)");
                System.out.println(DOUBLY_LINKED_LIST + ". DoublyLinkedList");
                System.out.print("Ingresa el número de la lista que quieres probar: ");
                String opcion = input.nextLine().trim();
                tipo = Integer.valueOf(opcion);
                if(tipo <= 0 || tipo > 4){
                    throw new NumberFormatException();
                }
                break;
            }catch(NumberFormatException e){
                continue;
            }
        }

        /*
            Last
        */

        switch(tipo){
            case ARRAY_LIST:
            case DOUBLY_LINKED_LIST: 
                set_casos(true);
                break;
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
                set_casos(false);
                break;
        }

        add_time = new long[casos];
        remove_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            set_lista(tipo, n);

            //Add
            add_time[i] = probar_add(n, false);

            //Remove
            remove_time[i] = probar_remove(n, false);

            n *= INCREMENTO;
        }

        mostrar_tiempo(add_time, "addLast");
        mostrar_tiempo(remove_time, "removeLast");

        /*
            First
        */

        switch(tipo){
            case ARRAY_LIST:
                set_casos(false);
                break;
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
            case DOUBLY_LINKED_LIST: 
                set_casos(true);
                break;                    
        }
        
        add_time = new long[casos];
        remove_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            set_lista(tipo, n);

            //Add
            add_time[i] = probar_add(n,true);

            //Remove
            remove_time[i] = probar_remove(n,true);

            n *= INCREMENTO;
        }

        mostrar_tiempo(add_time, "addFirst");
        mostrar_tiempo(remove_time, "removeFirst");

        /*
        Otros
        */

        //TopFront
        switch(tipo){
            case ARRAY_LIST:
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
            case DOUBLY_LINKED_LIST: 
                set_casos(true);
                break;                    
        }

        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        set_lista(tipo, n);
        switch(tipo){
            case ARRAY_LIST:
            case DOUBLY_LINKED_LIST: 
                for(int j=0; j<n; j++){
                    list.addLast(j);
                }
                break;
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
                for(int j=0; j<n; j++){
                    list.addFirst(j);
                }
                break;                    
        }

        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,1);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "TopFront");

        //TopBack
        switch(tipo){
            case ARRAY_LIST:
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
            case DOUBLY_LINKED_LIST: 
                set_casos(true);
                break;                    
        }

        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,2);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "TopBack");

        //empty
        switch(tipo){
            case ARRAY_LIST:
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
            case DOUBLY_LINKED_LIST: 
                set_casos(true);
                break;                    
        }

        extra_time = new long[casos];
        n = MIN_PRUEBAS;
        for(int i=0; i<casos; i++){
            extra_time[i] = probar_check(n,3);
            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "empty");

        Instant inicio;

        //find
        switch(tipo){
            case ARRAY_LIST:
            case LINKED_LIST:
            case LINKED_LIST_TAIL:
            case DOUBLY_LINKED_LIST: 
                set_casos(true);
                break;                    
        }

        extra_time = new long[casos];
        n = MIN_PRUEBAS;

        for(int i=0; i<casos; i++){
            switch(tipo){
                case ARRAY_LIST:
                    Lista_Array_Inter<Integer> list_arr = ((Lista_array<Integer>)list);
                    inicio = Instant.now();
                    for(int j=0; j<n; j++){
                        list_arr.find(j);
                    }
                    extra_time[i] = inicio.until(Instant.now(), unidad_tiempo);
                    break;
                case LINKED_LIST:
                case LINKED_LIST_TAIL:
                case DOUBLY_LINKED_LIST: 
                    Lista_nodo<Integer> list_nodo = ((Lista_nodo<Integer>)list);
                    inicio = Instant.now();
                    for(int j=0; j<n; j++){
                        list_nodo.find(j);
                    }
                    extra_time[i] = inicio.until(Instant.now(), unidad_tiempo);
                    break;                    
            }

            n *= INCREMENTO;
        }

        mostrar_tiempo(extra_time, "find");
        input.close();
    }

    private static void clear_screen(){
        System.out.print(ESC + "[3J" + ESC + "[2J" + ESC + "[H" + ESC + "[0m");
        System.out.flush();
    }

    private static void mostrar_tiempo(long[] tiempos, String metodo){
        int n = MIN_PRUEBAS;
        for(int j=0; j<casos; j++){
            System.out.println("Método " + metodo + " iterado " + n + " veces duró: " + tiempos[j] + " " + tiempo_en_palabras(unidad_tiempo));
            n *= INCREMENTO;
        }
    }

    private static long probar_add(int iteraciones, boolean first){
        Instant inicio;
        if(first){
            inicio = Instant.now(); //Para la prueba se hace el Instant.now() dentro del if para que el tiempo que tarde en evaluar la condición (por más pequeño que sea) no afecte al resultado
            for(int i=0; i<iteraciones; i++){
                list.addFirst(i);
            }
        }else{
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                list.addLast(i);
            }
        }
        
        return inicio.until(Instant.now(), unidad_tiempo);
    }

    /**
     * @param iteraciones el número de veces a probar el método
     * @param tipo 1 si se quiere TopFront, 2 si se quiere TopBack, 3 si se quiere empty()
     * @return el tiempo en 'unidad_tiempo' que se demoró el método
     */
    private static long probar_check(int iteraciones, int tipo){
        Instant inicio;
        if(tipo == 1){
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                list.topFront();
            }
        }else if(tipo == 2){
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                list.topBack();
            }
        }else{
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                list.empty();
            }
        }
        return inicio.until(Instant.now(), unidad_tiempo);
    }

    private static long probar_remove(int iteraciones, boolean first){
        Instant inicio;
        if(first){
            inicio = Instant.now(); //Para la prueba se hace el Instant.now() dentro del if para que el tiempo que tarde en evaluar la condición (por más pequeño que sea) no afecte al resultado
            for(int i=0; i<iteraciones; i++){
                list.removeFirst();
            }
        }else{
            inicio = Instant.now();
            for(int i=0; i<iteraciones; i++){
                list.removeLast();
            }
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

    private static void set_lista(int tipo, int n){
        if(tipo == ARRAY_LIST){
            list = new Lista_array<>(Integer.class, n);
        }else if(tipo == LINKED_LIST){
            list = new Lista_enlazada<>();
        }else if(tipo == LINKED_LIST_TAIL){
            list = new Lista_enlazada_cola<>();
        }else{
            list = new Lista_doble_enlazada<>();
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
