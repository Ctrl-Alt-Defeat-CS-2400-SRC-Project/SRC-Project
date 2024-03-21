import java.util.Arrays;
/**
   The inventory of products sold at the farm. 
   @author Hasti Abbasi Kenarsari
*/
public final class Inventory<T> implements BagInterface<T> {
    
    private T[] bag;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 25; 
    private static final int MAX_CAPACITY = 10000;

     /**
     * Creates an empty bag whose initial capacity is 25 (the default capacity).
     */
    public Inventory() {

        this(DEFAULT_CAPACITY);

    }

    /**
     * Creates an empty bag with a given initial capacity. 
     * 
     * @param initialCapacity The capacity desired represented as an integer.
     */
    public Inventory(int initialCapacity) {

        checkCapacity(initialCapacity);

        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[initialCapacity];
        bag = tempBag;
        numberOfEntries = 0;
        integrityOK = true;

    }

    /**
     * Creates a bag containing given entries.  
     * 
     * @param contents An array of objects
     */
    public Inventory(T[] contents) {

        checkCapacity(contents.length);
        bag = Arrays.copyOf(contents, contents.length);
        numberOfEntries = contents.length;
        integrityOK = true;

    }

    @Override
    /**
     * Adds a new entry to this bag. 
     * 
     * @param newEntry The object to be added as a new entry.
     * @return True.
     */
    public boolean add(T newEntry) {

        if(newEntry == null) {
            throw new IllegalArgumentException("The entry cannot be null.");
        }

        checkintegrity();
        
        if(isArrayFull()) {
            doubleCapacity();
        }

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;

    }

    @Override
    /**
     * Retrieves all entries that are in the bag. 
     * 
     * @return A newly allocated array of all the entries in the bag. 
     */
    public T[] toArray() {
        
        checkintegrity();

        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];

        for(int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }

        return result;

    }

    @Override
    /**
     * Sees whether this bag is empty. 
     * 
     * @return True if this bag is empty, or false if it is not empty.
     */
    public boolean isEmpty() {

        return numberOfEntries == 0;

    }

    @Override
    /**
     * Gets the current number of enries in this bag. 
     * 
     * @return The integer number of entries currently in this bag. 
     */
	public int getCurrentSize() {

      return numberOfEntries;

	} 
   
    @Override
    /**
     * Counts the number of times a given entry appears in this bag.
     * 
     * @param anEntry The entry to be counted. 
     * @return The number of times anEntry appears in this bag. 
     */
    public int getFrequencyOf(T anEntry) {

        checkintegrity();
        int counter = 0; 

        for(int index = 0; index < numberOfEntries; index++) {

            if(anEntry.equals(bag[index])) {
                counter++;
            }

        }

        return counter;

    }

    @Override
    /**
     * Tests whether this bag contains a given entry.
     * 
     * @param anEntry The entry to locate. 
     * @return True if this bag contains anEntry, or false otherwise.
     */
    public boolean contains(T anEntry) {

        checkintegrity();
        return getIndexOf(anEntry) >= 0;

    }

    @Override
    /**
     * Removes all entries from this bag
     */
    public void clear() {

        while(!isEmpty()) {
            remove();
        }

    }

    @Override
    /**
     * Removes one unspecified entry from this bag (if possible).
     * 
     * @return Either the removed entry, if the removal was successful, or null.
     */
    public T remove() {

        checkintegrity();

        T result = removeEntry(numberOfEntries - 1);
        return result;

    }

    @Override
    /**
     * Removes one occurence of a given entry from this bag.
     * 
     * @param anEntry The entry to be removed. 
     * @return True if the removal was successful, or false if not.
     */
    public boolean remove(T anEntry) {

        checkintegrity();

        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    /**
     * Locates a gien entry within the array bag.
     * 
     * Precondition: checkintegrity has been called.  
     * 
     * @param anEntry The entry to find the index of. 
     * @return THe index of the entry, if located, or -1 otherwise.
     */
    private int getIndexOf(T anEntry) {

        int where = -1;
        boolean found = false;
        int index = 0;

        while(!found && (index < numberOfEntries)) {

            if(anEntry.equals(bag[index])) {

                found = true;
                where = index;

            }

            index++;

        }

        return where;
        
    }

    /**
     * Removes the entry at a given index within the array. 
     * 
     * Precondition: 0 <= givenIndex < numberOfEntries.
     * Precondition: checkintegrity has been called.
     *  
     * @param givenIndex The index of the entry to be removed.
     * @return The entry that was removed. If no such entry exists, returns null.
     */
    private T removeEntry(int givenIndex) {

        T result = null; 

        if(!isEmpty() && (givenIndex >= 0)) {

            result = bag[givenIndex];
            int lastIndex = numberOfEntries - 1;
            bag[givenIndex] = bag[lastIndex];
            bag[lastIndex] = null;
            numberOfEntries--;
            
        }

        return result;
    }

    /**
     * Returns whether the bag is full. 
     * 
     * @return True if the array bag is full, or false if not.
     */
    public boolean isArrayFull() {

        return numberOfEntries >= bag.length;

    }

    /**
     * Doubles the size of the array bag. 
     * 
     * Precondition: checkInitialization has been called.
     */
    public void doubleCapacity() {

        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);

    }

    /**
     * Throws an exception if the client requests a capacity that is too large. 
     * 
     * @param capacity The capacity requested by the client.
     */
    public void checkCapacity(int capacity) {

        if(capacity > MAX_CAPACITY) {

            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds" 
                + "allowed maximum of " + MAX_CAPACITY);

        }

    }

    /**
     * Throws an exception if receiving object is not initialized. 
     */
    public void checkintegrity() {

        if(!integrityOK) {
            throw new SecurityException ("ArrayBag object is corrupt.");
        }
    }

    @Override
    /**
     * Returns a string representation of the contents of the bag.
     * 
     * @return A string representation of the bag enclosed by brackets and divded by commas. 
     */
    public String toString() {

        String result = "[";

        T[] arr = this.toArray();

        if(arr.length > 0) {

            for(int i = 0; i < arr.length - 1; i++) {

                result += (arr[i] + ",");

            }

            result += arr[arr.length - 1];

        }

        result += "]";

        return result;

    }

}
