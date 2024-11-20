public class Stack<T> extends Lista_array<T>{
    Stack(){
        super();
    }

    Stack(Class<T> c, int initial_size){
        super(c, initial_size);
    }

    @SuppressWarnings("unchecked")
    Stack(Stack<T> otro){
        this.datos = (T[])(new Object[otro.datos.length]);
        for(int i=0; i<otro.posicion_actual; i++){
            this.datos[i] = otro.datos[i];
        }
        this.posicion_actual = otro.posicion_actual;
    }
    
    public T pop(){
        T respuesta = topBack();
        removeLast();
        return respuesta;
    }

    public void push(T elemento){
        addLast(elemento);
    }
}
