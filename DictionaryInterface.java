import java.util.Iterator;
/**
 * An interface for a dictionary datatype
 * Search keys are unique in the dictionary
 * Search keys and associated values are not null
 * @author Ryan Wei
 */
public interface DictionaryInterface<K, V> {
    // interface for dictionary datatype
    
    /**
     * Adds a new entry to this dictionary. If the given key already exists in the
     * dictionary, replaces the corresponding value.
     * 
     * @param key   An object that serves as the key of the new entry.
     * @param value An object that is the desired value of the new entry.
     * @return Either null if the new entry was added to the dictionary or the value
     *         that was associated with key if that value was replaced.
     */
    public V add(K key, V value);

    /**
     * Removes a specific entry from this dictionary.
     * 
     * @param key An object that serves as the key of the entry to be removed.
     * @return Either the value that was associated with the key or null if no such
     *         object exists.
     */
    public V remove(K key);

    /**
     * Retrieves the value associated with a given key.
     * 
     * @param key An object that serves as the key of the entry to be retrieved.
     * @return Either the value that is associated with the key or null if no such
     *         object exists.
     */
    public V getValue(K key);

    /**
     * Sees whether a specific entry is in this dictionary.
     * 
     * @param key An object that serves as the key of the desired entry.
     * @return True if key is associated with an entry in the dictionary.
     */
    public boolean contains(K key);

    /**
     * Creates an Iterator that traverses all search keys in this dictionary.
     * 
     * @return An Iterator that provides sequential access to the search keys in the dictionary
     */
    public Iterator<V> getKeyIterator();

    /**
     * Creates an Iterator that traverses all values in this dictionary.
     * 
     * @return An Iterator that provides sequential access to the values in this dictionary.
     */
    public Iterator<V> getValueIterator();

    /**
     * Sees whether this dictionary is empty.
     * 
     * @return True if the dictionary is empty.
     */
    public boolean isEmpty();

    /**
     * Gets the size of this dictionary.
     * 
     * @return The number of entries (key-value pairs) currently in the dictionary.
     */
    public int getSize();

    /**
     * Removes all entries from this dictionary.
     */
    public void clear();

}
