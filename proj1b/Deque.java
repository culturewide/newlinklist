public interface Deque<T> {
        public void addFirst(T x);
        public void addLast(T x);
        public T removeFirst();
        public T removeLast();
        public boolean isEmpty();
        public int size();
        public void printDeque();
        public T get(int index);
    }


