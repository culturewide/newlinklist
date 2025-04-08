public class ArrayDeque<T> {
    public int size;
    public int frontPtr;
    public int rearPtr;
    public T[] items;
    public int capacity;
    public ArrayDeque () {
        items = (T[]) new Object[8];
        size=0;
        frontPtr = 0;
        rearPtr = 0;
        capacity=8;
    }
    public void update(){
        if(size==capacity){
            resize(capacity*2);
        }else if(size>0&&capacity>=16&&size*4<capacity){
            resize(Math.max(capacity/2,16));
        }
    }
    //循环需要用到+size/size的方法
    public void resize(int newcapacity){
        T[] newItems = (T[]) new Object[newcapacity];
       // int current = frontPtr;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[frontPtr];
            frontPtr = (frontPtr + 1) % capacity;
        }
        items = newItems;
        frontPtr = 0;
        rearPtr =size;
        capacity=newcapacity;
    }
    public void addFirst(T num) {
        update();
        frontPtr = (frontPtr-1+capacity)%capacity;
        size++;
        items[frontPtr]=num;
    }
    public boolean isEmpty() {
        return size==0;
    }
    public int size() {
        return size;
    }
    public void addLast(T item){
        update();
        items[rearPtr]=item;
        size++;
        rearPtr = (rearPtr+1+capacity)%capacity;
    }
    public T removeFirst() {
        if(size==0){
            return null;
        }
        T x=items[frontPtr];
        frontPtr = (frontPtr+1+capacity)%capacity;
        size--;
        update();
        return x;
    }
    public T removeLast() {
        if(size==0){
            return null;
        }
        T x=items[(rearPtr-1+capacity)%capacity];
        rearPtr = (rearPtr-1+capacity)%capacity;
        size--;
        update();
        return x;
    }
    public T get(int index) {
        if(index>=size)
            return null;
        return items[(frontPtr+index)%capacity];

    }
    public void printDeque() {
        for(int i=0; i<size; i++) {
            System.out.print(items[(i+frontPtr)%capacity]+" ");
        }

    }
   /* public static void main(String[] args) {
        ArrayDeque <Integer>a=new ArrayDeque();
        a.addLast(0);
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        System.out.println(a.get(0));
    }*/
}
