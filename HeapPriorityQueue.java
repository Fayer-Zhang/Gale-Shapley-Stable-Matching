/*
 * Student Name: Feier Zhang
 * Student Number: 8589976
 */

public class HeapPriorityQueue<K extends Comparable,V> implements PriorityQueue<K ,V> {
    
    private Entry[] storage; //The Heap itself in array form
    private int tail;        //Index of last element in the heap
    
    public HeapPriorityQueue(){
        this(100);
    }
    

    public HeapPriorityQueue(int size){
        storage = new Entry[size];
        tail = -1;
    }
    
    // Returns the number of items in the priority queue.
    @SuppressWarnings("unchecked")
    public int size(){
        return tail+1;
    }

    // Tests whether the priority queue is empty.
    @SuppressWarnings("unchecked")
    public boolean isEmpty(){
        return tail<0;
    }
    
    
    // Inserts a key-value pair and returns the entry created. O(log(n))
    @SuppressWarnings("unchecked")
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
        if(tail == storage.length -1) throw new IllegalArgumentException("Heap Overflow");
        Entry<K,V> e = new Entry<>(key,value);        
        storage[++tail] = e;
        upHeap(tail);
        return e;
    }
    

    // Returns (but does not remove) an entry with minimal key. O(1)
    @SuppressWarnings("unchecked")
    public Entry<K,V> min(){
        if(isEmpty()) return null;
        return storage[0];
    }
    
    
    // Removes and returns an entry with minimal key. O(log(n))
    @SuppressWarnings("unchecked")
    public Entry<K,V> removeMin(){
        if(isEmpty()) return null;
        Entry<K,V> min = storage[0];
        
        if(tail == 0)
        {
            tail = -1;
            storage[0] = null;
            return min;
        }
        
        storage[0] = storage[tail];
        storage[tail--] = null;
        downHeap(0);
        return min;
    }  
    
    
    // Algorithm to place element after insertion at the tail. O(log(n))
    @SuppressWarnings("unchecked")
    private void upHeap(int location){
        if(location == 0)
        {
            return;
        } 
            
        int parent = parent(location);
        if(storage[parent].key.compareTo(storage[location].key) > 0)
        {
            swap(location,parent);
            upHeap(parent);
        }               
    }
    

    // Algorithm to place element after removal of root and tail element placed at root. O(log(n))
    @SuppressWarnings("unchecked")
    private void downHeap(int location){
        int left = (location*2) +1;
        int right = (location*2) +2;
         
         //Both children null or out of bound
        if(left > tail)
        {
            return;
        }

         //left in right out;
        if(left == tail)
        {
            if(storage[location].key.compareTo(storage[left].key) > 0)
            {
                swap(location,left);                  
            }
            return;
        }

        int toSwap= (storage[left].key.compareTo(storage[right].key) < 0)? left:right;         
        if(storage[location].key.compareTo(storage[toSwap].key) > 0){
            swap(location,toSwap);
            downHeap(toSwap);
        }                 
    }
    
    @SuppressWarnings("unchecked")
    private int parent(int location){
        return (location-1)/2;
    }
    
    @SuppressWarnings("unchecked")
    private void swap(int location1, int location2){
        Entry<K,V> temp = storage[location1];
        storage[location1] = storage[location2];
        storage[location2] = temp;  
    }
    
}
