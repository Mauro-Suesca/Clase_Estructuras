import java.math.BigInteger;
import java.util.Random;

public class Hash_String extends Hash{
    private Lista_array_dinamico<String>[] table;
    private int x;

    @SuppressWarnings("unchecked")
    Hash_String(int m, double load_factor, int max){
        this.m = m;
        this.load_factor = load_factor;
        this.table = new Lista_array_dinamico[m];
        BigInteger aux = BigInteger.valueOf(max);
        this.p = aux.nextProbablePrime().intValue();
        new_hash();
    }

    /**
     * Elimina el elemento que tiene el valor indicado.
     * @param valor El valor a buscar para eliminar.
     * @return Si el elemento se encontró en la lista y se pudo eliminar.
     */
    public boolean delete(String valor){
        int posicion = hash(valor);
        boolean respuesta = false;

        if(table[posicion] != null){
            respuesta = table[posicion].erase(valor);
        }

        if(respuesta){
            n--;
        }

        return respuesta;
    }

    /**
     * La función hash actual de la tabla hash
     * @param valor El valor que se quiere introducir en la tabla hash
     * @return El índice que debería ocupar el elemento en la tabla hash
     */
    private int hash(String valor){
        int respuesta = 0;

        for(int i=valor.length(); i>=0; i++){
            respuesta = (valor.charAt(i) + respuesta*x)%p;
        }

        return respuesta;
    }

    public void insert(String elemento){
        int posicion = hash(elemento);
        if(table[posicion] == null){
            table[posicion] = new Lista_array_dinamico<>(String.class, 10);
        }

        table[posicion].addLast(elemento);
        n++;

        rehash();
    }

    @Override protected void new_hash(){
        Random generador = new Random();
        this.x = generador.nextInt(1, p);
    }

    @SuppressWarnings("unchecked")
    @Override public void rehash(){
        if(n/m > load_factor){
            m = growth_factor*m;
            Lista_array_dinamico<String>[] old_table = this.table;
            this.table = new Lista_array_dinamico[m];
            n = 0;
            new_hash();
            for(int i=0; i<(m/growth_factor); i++){
                if(old_table[i] != null){
                    for(int j=0; j<old_table[i].size(); j++){
                        this.insert(old_table[i].get(j));
                    }
                }
            }
        }
    }
}