public class LinkedListDeque<T> {
    private class Node{
        T item;
        Node prev;
        Node next;
        Node(T i, Node p, Node n){
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
    public void addFirst(T item){
        Node NewNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = NewNode;
        sentinel.next = NewNode;
        size ++;

    }
    public void addLast(T item){
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
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        Node firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        firstNode.next.prev = sentinel;
        size --;
        return firstNode.item;

    }
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        Node lastNode = sentinel.prev;
        sentinel.prev = lastNode.prev;
        lastNode.prev.next = sentinel;
        size --;
        return lastNode.item;


    }
    public T get(int index){
        if (index > size) {

            return null;

        }
        Node current = sentinel;
        for (int i=0;i <= index; i++){
            current = current.next;

        }
        return current.item;

    }
    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        } else {
            return getRecursiveHelper(current.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index); // 从第一个实际节点开始
    }

}