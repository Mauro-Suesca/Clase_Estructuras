public class Lista_array_circ_din<T> extends Lista_array_circ<T>{
    Lista_array_circ_din(){
        super();
    }

    Lista_array_circ_din(Class<T> c, int initial_size){
        super(c, initial_size);
    }

    @Override public void addLast(T element){
        if(!full()){
            addLast_no_check(element);
        }else{
            expandir_array(false, back, element);
        }
    }

    @Override public void addFirst(T element){
        if(!full()){
            addFirst_no_check(element);
        }else{
            expandir_array(true, front, element);
        }
    }

    @Override public void addBefore(int posicion, T element){
        if(!full()){
            addBefore_no_check(posicion, element);
        }else{
            expandir_array(posicion == front, posicion, element);
        }
    }

    @Override public void addAfter(int posicion, T element){
        if(!full()){
            addAfter_no_check(posicion, element);
        }else{
            expandir_array(false, change_position(posicion, true), element);
        }
    }

    /**
     * Esta función crea un nuevo array con la longitud del doble de la actual, copia los elementos existentes e introduce un nuevo elemento en el array resultante
     * @param es_front Indica si se quiere añadir el elemento en el inicio de la lista.
     * @param posicion La posición absoluta del array original en la cual se quería ingresar el nuevo elemento.
     * @param element El nuevo elemento a ingresar.
     */    
    @SuppressWarnings("unchecked")
    private void expandir_array(boolean es_front, int posicion, T element){
        T[] nuevo_datos = (T[])new Object[datos.length*2];
        int posicion_actual_nuevo = 0;
        int posicion_actual_datos = front;

        if(es_front){
            nuevo_datos[posicion_actual_nuevo++] = element;
        }
        //Estas líneas se ponen, porque si se quiere añadir al principio, si no se tienen, no se va a añadir ningún elemento del array original al nuevo
        nuevo_datos[posicion_actual_nuevo++] = datos[posicion_actual_datos];
        posicion_actual_datos = change_position(posicion_actual_datos, true);

        while(posicion_actual_datos != posicion){
            nuevo_datos[posicion_actual_nuevo++] = datos[posicion_actual_datos];
            posicion_actual_datos = change_position(posicion_actual_datos, true);
        }

        if(!es_front){
            nuevo_datos[posicion_actual_nuevo++] = element;
        }

        while(posicion_actual_datos != back){
            nuevo_datos[posicion_actual_nuevo++] = datos[posicion_actual_datos];
            posicion_actual_datos = change_position(posicion_actual_datos, true);
        }

        datos = nuevo_datos;
        front = 0;
        size = posicion_actual_nuevo;
        back = !full() ? posicion_actual_nuevo : 0;  //Si después de añadir todo, la lista está llena, back puede estar apuntando fuera del array
    }
}