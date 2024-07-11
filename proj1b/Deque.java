public interface Deque<Item> {
    void addFirst(Item item);
    void addLast(Item item);
    boolean isEmpty();
    int size();
    void printDeque();
    Item removeFirst();
    Item removeLast();
    Item get(int index);
}
class LinkedListDeque<Item> implements Deque<Item>{
    private class Node{
        Item item;
        Node prev;
        Node next;
        Node(Item i, Node p, Node n){
            item = i;
            prev = p;
            next = n;
        }
    }
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;


    }
    public void addFirst(Item item){
        Node NewNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = NewNode;
        sentinel.next = NewNode;
        size ++;

    }
    public void addLast(Item item){
        Node NewNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = NewNode;
        sentinel.prev = NewNode;
        size ++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node current = sentinel.next;
        while (current != sentinel){
            System.out.print(current.item);
            current = current.next;
        }


    }
    public Item removeFirst(){
        if (isEmpty()){
            return null;
        }
        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size --;
        return firstNode.item;

    }
    public Item removeLast(){
        if (isEmpty()){
            return null;
        }
        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        size --;
        return lastNode.item;


    }
    public Item get(int index){
        if (index > size) {

            return null;

        }
        Node current = sentinel;
        for (int i=0;i <= index; i++){
            current = current.next;

        }
        return current.item;

    }
    private Item getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        } else {
            return getRecursiveHelper(current.next, index - 1);
        }
    }

    public Item getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }}

class ArrayDeque<Item> implements Deque<Item>{
    public Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (Item[]) new Object[8];
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
        Item[] newItems = (Item[]) new Object[capacity];
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
    public void addFirst(Item item){
        if (size == items.length){
            resize(size*2);

        }
        items[nextFirst]=item;
        nextFirst = oneMinus(nextFirst);
        size ++;
    }
    public void addLast(Item item){
        if (size == items.length){
            resize(size*2);
        }
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size ++;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public Item removeFirst(){
        if (isEmpty()){
            return null;
        }
        nextFirst = onePlus(nextFirst);
        Item item = items[nextFirst];
        items[nextFirst] = null;
        size --;
        return  item;
    }
    public Item removeLast(){
        if (isEmpty()){
            return null;
        }
        nextLast = oneMinus(nextLast);
        Item item = items[nextLast];
        items[nextLast] = null;
        size --;
        return item;
    }
    public void printDeque(){
        int current = onePlus(nextFirst);
        for (int i = 0; i < size; i ++){
            System.out.print(items[current]);
            current = onePlus(current);
        }
    }
    public Item get(int index){
        int startIndex = onePlus(nextFirst);
        int trueIndex = (startIndex+index)%items.length;
        return items[trueIndex];

    }
}

