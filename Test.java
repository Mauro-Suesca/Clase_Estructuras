public class Test{
    public static void main(String[] args){
        Lista_array_orden<Integer> list = new Lista_array_orden<>(Integer.class, 5);
        list.insert(5);
        list.insert(4);
        list.insert(2);
        list.insert(7);
        list.insert(3);
        list.insert(1);
        list.insert(6);
        list.print();
    }
}