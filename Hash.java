public abstract class Hash{
    protected short growth_factor = 2;
    protected double load_factor;
    protected int m;
    protected int n;
    protected int p;

    /**
     * Genera una nueva función hash teniendo en cuenta la información actual del hash
     */
    protected abstract void new_hash();

    /**
     * Incrementa el tamaño de la tabla hash y le asigna una nueva función hash para mantener el load_factor
     */
    public abstract void rehash();

    /**
     * Indica un nuevo factor de crecimiento para el tamaño del array que representa a la tabla hash cuando se supera el load_factor
     * @param growth_factor El nuevo factor de crecimiento
     */
    public void set_growth_factor(short growth_factor){
        this.growth_factor = growth_factor;
    }
}