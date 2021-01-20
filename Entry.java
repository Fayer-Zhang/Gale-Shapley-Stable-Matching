/*
 * Student Name: Feier Zhang
 * Student Number: 8589976
 */

public class Entry<K extends Comparable,V> {
    K key;
    V value;
    
    // Returns the key stored in this entry.
    K getKey(){
        return key;
    }

    //Returns the value stored in this entry.
    V getValue(){
        return value;
    }
    
    public Entry(K k, V v){
        key = k;
        value = v;
    }   
}
