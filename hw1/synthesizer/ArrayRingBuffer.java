package synthesizer;
// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import synthesizer.BoundedQueue;
//如果父类是抽象类（含 abstract 方法）子类 必须重写所有抽象方法，否则子类也必须声明为 abstract。
import java.util.AbstractQueue;
import java.util.Iterator;
//如果不在同一个文件夹怎么办
//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend synthesizer.AbstractBoundedQueue<t>
public class ArrayRingBuffer<T>  extends AbstractBoundedQueue <T>  {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int wizpos;
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
   /* @Override
      T next(){
        T current=(T) rb[wizpos];
        wizpos++;
        return current;
    }
    @Override
     public boolean hasNext(){
        return wizpos < capacity;

    }*/
    private class  ArraryRingBufferiterator <T> implements Iterator<T> {
        private int count = 0;
       public ArraryRingBufferiterator() {
           wizpos = first;

       }
       @Override
       public boolean hasNext() {
           return count<fillCount;
       }
       @Override
       public T next() {
           T item=(T)rb[wizpos];
           wizpos=(wizpos+1)%rb.length;
           count++;
           return item;
       }

    }


    @Override
    public Iterator<T> iterator() {
        return new ArraryRingBufferiterator<>();
    }

    public ArrayRingBuffer(int capacity) {
         rb = (T[]) new Object[capacity];
         first = 0;
         last = 0;
         fillCount = 0;
         this.capacity = capacity;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from synthesizer.AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if(isFull()) {
            throw new IllegalStateException("Ring buffer overflow");
        }
        rb[last] = x;
        last=(last+1)%capacity;
        fillCount++;


        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(isEmpty()) {
            throw new IllegalStateException("Ring buffer underflow");
        }
        T x = rb[first];
        first=(first+1)%capacity;
        fillCount--;
        return x;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if(isEmpty()) {
            throw new RuntimeException();
        }
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
