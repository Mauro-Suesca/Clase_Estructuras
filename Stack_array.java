public class Stack_array<T> extends Lista_array<T> implements Stack<T>{
    Stack_array(){
        super();
    }

    Stack_array(Class<T> c, int initial_size){
        super(c, initial_size);
    }

    @SuppressWarnings("unchecked")
    Stack_array(Stack_array<T> otro){
        this.datos = (T[])(new Object[otro.datos.length]);
        for(int i=0; i<otro.posicion_actual; i++){
            this.datos[i] = otro.datos[i];
        }
        this.posicion_actual = otro.posicion_actual;
    }
    
    @Override public T pop(){
        T respuesta = topBack();
        removeLast();
        return respuesta;
    }

    @Override public void push(T elemento){
        addLast(elemento);
    }
}
