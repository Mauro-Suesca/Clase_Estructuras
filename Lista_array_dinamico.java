public class Lista_array_dinamico<T> extends Lista_array_estatico<T>{
    Lista_array_dinamico(){
        super();
    }

    Lista_array_dinamico(Class<T> c, int initial_size){
        super(c, initial_size);
    }

    @Override protected void add(int posicion, T element){
        if(!full()){
            move(posicion, true);
            datos[posicion] = element;
        }else{
            expandir_array(posicion, element);
        }
        posicion_actual++;
    }

    @Override public void addLast(T element){
        add(posicion_actual, element);
    }

    @Override public void addFirst(T element){
        add(0, element);
    }

    @Override public void addBefore(int posicion, T element){
        add(posicion, element);
    }

    @Override public void addAfter(int posicion, T element){
        add(posicion + 1, element);
    }

    /**
     * Esta función crea un nuevo array con la longitud del doble de la actual, copia los elementos existentes e introduce un nuevo elemento en el array resultante
     * @param posicion La posición en la cual se va a ingresar el nuevo elemento.
     * @param element El nuevo elemento a ingresar.
     */
    @SuppressWarnings("unchecked")
    protected void expandir_array(int posicion, T element){
        T[] nuevo_datos = (T[])new Object[datos.length*2];
        for(int i=0; i<posicion; i++){
            nuevo_datos[i] = datos[i];
        }
        nuevo_datos[posicion] = element;
        for(int i=posicion; i<posicion_actual; i++){
            nuevo_datos[i+1] = datos[i];
        }
        datos = nuevo_datos;
    }
}