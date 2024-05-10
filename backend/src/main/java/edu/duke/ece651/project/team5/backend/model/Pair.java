package edu.duke.ece651.project.team5.backend.model;

/**
 * The class to store a pair of key and value
 * 
 * @param <K> The key type
 * @param <V> The value type
 */
public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

}
