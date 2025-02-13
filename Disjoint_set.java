public class Disjoint_set{
    protected int[] parent;

    Disjoint_set(int initial_size){
        this.parent = new int[initial_size];
    }

    /**
     * Retorna el identificador del conjunto que contiene el valor dado
     * @param x El valor cuyo conjunto se quiere identificar
     * @return El identificador del conjunto al que pertenece 'x', 0 si el valor no existe en ninguno de los conjuntos
     */
    public int find(int x){
        if(x >= 1 && x<=parent.length){
            return find_recursive(x);
        }else{
            return 0;
        }
    }

    private int find_recursive(int x){
        if(parent[x-1] != x){
            parent[x-1] = find_recursive(parent[x-1]);
        }

        return parent[x-1];
    }

    /**
     * Crea un conjunto unitario que contiene a x
     * @param x El valor del cual se quiere hacer un conjunto unitario
     * @throws IndexOutOfBoundsException Si se intenta hacer un conjunto a partir de un valor mayor al tamaño inicial especificado de la estructura, o de un valor menor o igual a 0
     */
    public void make_set(int x) throws IndexOutOfBoundsException{
        parent[x-1] = x;
    }
    
    /**
     * Une los conjuntos que contienen a los dos valores dados
     * @param x El primer valor cuyo conjunto se quiere unir
     * @param y El segundo valor cuyo conjunto se quiere unir
     * @throws IndexOutOfBoundsException Si se intentan unir conjuntos a partir de por lo menos un valor mayor al tamaño inicial especificado de la estructura, o de un valor menor o igual a 0
     */
    public void union(int x, int y) throws IndexOutOfBoundsException{
        parent[x-1] = find(y);
    }
}