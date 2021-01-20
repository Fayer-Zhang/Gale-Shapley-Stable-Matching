/*
 * Student Name: Feier Zhang
 * Student Number: 8589976
 * Description: Interface for the priority queue ADT.
 */

public interface PriorityQueue<K extends Comparable,V> {

  // Returns the size of the priority queue.
  int size();

  //Tests whether the priority queue is empty.
  boolean isEmpty();

  // Inserts a key-value pair and returns the entry created.   
  Entry<K,V> insert(K key, V value) throws IllegalArgumentException;

  
  //Returns (but does not remove) an entry with minimal key.
  Entry<K,V> min();

  // Removes and returns an entry with minimal key.
  Entry<K,V> removeMin();
}
