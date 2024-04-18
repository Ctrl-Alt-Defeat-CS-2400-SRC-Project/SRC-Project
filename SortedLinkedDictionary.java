import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class that implements the DictionaryInterface using a sorted linked dictionary. 
 * The dictionary is sorted and has distinct search keys.
 * Search keys and associated values are not null.
 * 
 * @author Ryan Wei
 */
public class SortedLinkedDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> {
    private Node firstNode;
    private int numberOfEntries;

    /**
     * Default constructor for the SortedLinkedDictionary class
     */
    public SortedLinkedDictionary() {
        initializeDataFields();
    }

    /**
     * Adds a new entry to this dictionary. 
     * If the given key already exists in the dictionary, replaces the corresponding value.
     * @param key An object that serves as the key of the new entry.
     * @param value An object that is the desired value of the new entry.
     * @return Either null if the new entry was added to the dictionary or the value that was associated with key if that value was replaced.
     */
    public V add(K key, V value) {
        V result = null;
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Cannot add null to a dictionary.");
        } else {
            Node currentNode = firstNode;
            Node nodeBefore = null;
            while ((currentNode != null) && (key.compareTo(currentNode.getKey()) > 0)) {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }
            if ((currentNode != null) && key.equals(currentNode.getKey())) {
                result = currentNode.getValue();
                currentNode.setValue(value);
            } else {
                Node newNode = new Node(key, value);
                if (nodeBefore == null) {
                    newNode.setNextNode(firstNode);
                    firstNode = newNode;
                } else {
                    newNode.setNextNode(currentNode);
                    nodeBefore.setNextNode(newNode);
                }
                numberOfEntries++;
            }
        }
        return result;
    }

    /**
     * Removes a specific entry from this dictionary.
     * @param key An object that serves as the key of the entry to be removed.
     * @return Either the value that was removed and was associated with the key or null if no such object exists.
     */
    public V remove(K key) {
        V result = null;
        if (key != null) {
            Node currentNode = firstNode;
            Node nodeBefore = null;
            while ((currentNode != null) && (key.compareTo(currentNode.getKey()) > 0)) {
                nodeBefore = currentNode;
                currentNode = currentNode.getNextNode();
            }
            if ((currentNode != null) && key.equals(currentNode.getKey())) {
                Node nodeAfter = currentNode.getNextNode();
                if (nodeBefore == null) {
                    firstNode = nodeAfter;
                } else {
                    nodeBefore.setNextNode(nodeAfter);
                }
                result = currentNode.getValue();
                numberOfEntries--;
            }
        }
        return result;
    }

    /**
     * Retrieves the value associated with a given key.
     * @param key An object that serves as the key of the entry to be retrieved.
     * @return Either the value that is associated with the key or null if no such object exists.
     */
    public V getValue(K key) {
        V result = null;
        Node currentNode = firstNode;
        while ((currentNode != null) && (key.compareTo(currentNode.getKey()) > 0)) {
            currentNode = currentNode.getNextNode();
        }
        if ((currentNode != null) && key.equals(currentNode.getKey())) {
            result = currentNode.getValue();
        }
        return result;
    }

    /**
     * Sees whether a specific entry is in this dictionary.
     * @param key An object that serves as the key of the desired entry.
     * @return True if key is associated with an entry in the dictionary, false if not.
     */
    public boolean contains(K key) {
        return getValue(key) != null;
    }

    /**
     * Creates an Iterator that traverses all search keys in this dictionary.
     * @return An Iterator that provides sequential access to the search keys in the dictionary
     */
    public Iterator<K> getKeyIterator() {
        return new KeyIterator();
    }

    /**
     * Creates an Iterator that traverses all values in this dictionary.
     * @return An Iterator that provides sequential access to the values in this dictionary.
     */
    public Iterator<V> getValueIterator() {
        return new ValueIterator();
    }

    /**
     * Sees whether this dictionary is empty.
     * @return True if the dictionary is empty, false if not.
     */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * Gets the size of this dictionary.
     * @return The number of entries in this dictionary.
     */
    public int getSize() {
        return numberOfEntries;
    }

    /**
     * Removes all entries from this dictionary.
     */
    public void clear() {
        initializeDataFields();
    }

    private class KeyIterator implements Iterator<K> {
        private Node nextNode;

        private KeyIterator() {
            nextNode = firstNode;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public K next() {
            K result;
            if (hasNext()) {
                result = nextNode.getKey();
                nextNode = nextNode.getNextNode();
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class ValueIterator implements Iterator<V> {
        private Node nextNode;

        private ValueIterator() {
            nextNode = firstNode;
        }

        public boolean hasNext() {
            return nextNode != null;
        }

        public V next() {
            V result;
            if (hasNext()) {
                result = nextNode.getValue();
                nextNode = nextNode.getNextNode();
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    private class Node {
        private K key;
        private V value;
        private Node next;

        private Node(K searchKey, V dataValue) {
            key = searchKey;
            value = dataValue;
            next = null;
        }

        private Node(K searchKey, V dataValue, Node nextNode) {
            key = searchKey;
            value = dataValue;
            next = nextNode;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        private void setValue(V newValue) {
            value = newValue;
        }

        private Node getNextNode() {
            return next;
        }

        private void setNextNode(Node nextNode) {
            next = nextNode;
        }

    }

}
