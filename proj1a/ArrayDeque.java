public class ArrayDeque<T> {
    public T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;

    }
    private int oneMinus(int index){
        return (index - 1)%items.length;
    }

    private int onePlus(int index){
        return (index + 1)%items.length;
    }
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int oldIndex = onePlus(nextFirst);
        if (nextFirst < nextLast) {
            System.arraycopy(items, oldIndex, newItems, 0, size);
        } else {
            int firstPartLength = items.length - oldIndex;
            System.arraycopy(items, oldIndex, newItems, 0, firstPartLength);
            System.arraycopy(items, 0, newItems, firstPartLength, nextLast);
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }
    public void addFirst(T item){
        if (size == items.length){
            resize(size*2);

        }
        items[nextFirst]=item;
        nextFirst = oneMinus(nextFirst);
        size ++;
    }
    public void addLast(T item){
        if (size == items.length){
            resize(size*2);
        }
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size ++;
    }
    public Boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        nextFirst = onePlus(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return  item;
    }
    public T removeLast(){
        if (isEmpty()){
            return null
        }
        nextLast = oneMinus(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size --;
        return item;
    }
    public T get(int index){
        int startIndex = onePlus(nextFirst);
        int trueIndex = (startIndex+index)%items.length;
        return items[trueIndex];

    }
}
