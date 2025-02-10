public class Disjoint_set_rank extends Disjoint_set{
    private int[] rank;

    Disjoint_set_rank(int initial_size){
        super(initial_size);
        this.rank = new int[initial_size];
    }

    @Override public void make_set(int x) throws IndexOutOfBoundsException{
        super.make_set(x);
        rank[x-1] = 0;
    }

    @Override public void union(int x, int y) throws IndexOutOfBoundsException{
        int padre_x = find(x);
        int padre_y = find(y);

        if(padre_x != padre_y){
            if(rank[padre_x-1] >= rank[padre_y-1]){
                parent[padre_y-1] = padre_x;

                if(rank[padre_x-1] == rank[padre_y-1]){
                    rank[padre_x-1]++;
                }
            }else{
                parent[padre_x-1] = padre_y;
            }
        }
    }
}