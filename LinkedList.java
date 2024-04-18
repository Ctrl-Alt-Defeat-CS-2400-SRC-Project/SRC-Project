import java.util.Arrays;
/**
   The inventory of products sold at the farm. 
   @author Hasti Abbasi Kenarsari
*/
public final class LinkedList<T> implements ListInterface<T> {

    private T[] list;   
	private int numberOfEntries;
    private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
    

    /**
    * Creates an list with default capacity.
    */
    public LinkedList() {

        this(DEFAULT_CAPACITY);

    }

    /**
    * Creates an empty list with a given initial capacity. 
    * 
    * @param initialCapacity The capacity desired represented as an integer.
    */
    public LinkedList(int initialCapacity) {

        integrityOK = false;
        
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            checkCapacity(initialCapacity);
        }
       
        @SuppressWarnings("unchecked")
        T[] tempList = (T[])new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        integrityOK = true;
        
    }

    /**
    * Creates a list containing given entries.  
    * 
    * @param contents An array of objects
    */
    public LinkedList(T[] contents) {


    }

    @Override
    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list's size is increased by 1.
        @param newEntry  The object to be added as a new entry. */
    public void add(T newEntry) {

    }
   
    @Override
    /** Adds a new entry at a specified position within this list.
        Entries originally at and above the specified position
        are at the next higher position within the list.
        The list's size is increased by 1.
        @param newPosition  An integer that specifies the desired position of the new entry.
        @param newEntry     The object to be added as a new entry.
        @throws  IndexOutOfBoundsException if either newPosition < 1 or newPosition > getLength() + 1. */
    public void add(int newPosition, T newEntry) {

   }
   
    @Override
    /** Removes the entry at a given position from this list.
        Entries originally at positions higher than the given
        position are at the next lower position within the list,
        and the list's size is decreased by 1.
        @param givenPosition  An integer that indicates the position of the entry to be removed.
        @return  A reference to the removed entry.
        @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). */
    public T remove(int givenPosition) {
        return null;
    }
   
   @Override
   /** Removes all entries from this list. */
   public void clear() {

   }
   
    @Override
    /** Replaces the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of the entry to be replaced.
       @param newEntry  The object that will replace the entry at the position givenPosition.
       @return  The original entry that was replaced.
       @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). */
    public T replace(int givenPosition, T newEntry) {
        return null;
    }
   
    @Override
    /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). */
    public T getEntry(int givenPosition) {
        return null;
    }
   
    @Override
    /** Retrieves all entries that are in this list in the order in which
       they occur in the list.
       @return  A newly allocated array of all the entries in the list. If the list is empty, the returned array is empty. */
    public T[] toArray() {
        return null;
    }
   
    @Override
    /** Sees whether this list contains a given entry.
       @param anEntry  The object that is the desired entry.
       @return  True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry) {
        return true;
    }
   
    @Override
    /** Gets the length of this list.
       @return  The integer number of entries currently in the list. */
    public int getLength() {
        return 0;
    }
       
    @Override
    /** Sees whether this list is empty.
        @return  True if the list is empty, or false if not. */
    public boolean isEmpty() {
        return true;
    }

    /** Doubles the capacity of the array list if it is full.
    *  Precondition: checkIntegrity has been called.
    */
    private void ensureCapacity() {
        int capacity = list.length - 1;

        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); 
            list = Arrays.copyOf(list, newCapacity + 1);
        } 
   } 

    /**
    * Throws an exception if the client requests a capacity that is too large. 
    * 
    * @param capacity The capacity requested by the client.
    */
    public void checkCapacity(int capacity) {

        if(capacity > MAX_CAPACITY) {

            throw new IllegalStateException("Attempt to create inventory whose capacity exceeds" 
                + "allowed maximum of " + MAX_CAPACITY);

        }

    }

    /**
    * Throws an exception if receiving object is not initialized. 
    */
    public void checkintegrity() {

        if(!integrityOK) {
            throw new SecurityException ("Object is corrupt.");
        }
    }




}

import java.util.Arrays;
/**
   The inventory of products sold at the farm. 
   @author Hasti Abbasi Kenarsari
*/
public final class LinkedList<T> implements ListInterface<T> {

    private T[] list;   
	private int numberOfEntries;
    private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;
    

    /**
    * Creates an list with default capacity.
    */
    public LinkedList() {

        this(DEFAULT_CAPACITY);

    }

    /**
    * Creates an empty list with a given initial capacity. 
    * 
    * @param initialCapacity The capacity desired represented as an integer.
    */
    public LinkedList(int initialCapacity) {

        integrityOK = false;
        
        if (initialCapacity < DEFAULT_CAPACITY) {
            initialCapacity = DEFAULT_CAPACITY;
        } else {
            checkCapacity(initialCapacity);
        }
       
        @SuppressWarnings("unchecked")
        T[] tempList = (T[])new Object[initialCapacity + 1];
        list = tempList;
        numberOfEntries = 0;
        integrityOK = true;
        
    }

    /**
    * Creates a list containing given entries.  
    * 
    * @param contents An array of objects
    */
    public LinkedList(T[] contents) {


    }

    @Override
    /** Adds a new entry to the end of this list.
        Entries currently in the list are unaffected.
        The list's size is increased by 1.
        @param newEntry  The object to be added as a new entry. */
    public void add(T newEntry) {

    }
   
    @Override
    /** Adds a new entry at a specified position within this list.
        Entries originally at and above the specified position
        are at the next higher position within the list.
        The list's size is increased by 1.
        @param newPosition  An integer that specifies the desired position of the new entry.
        @param newEntry     The object to be added as a new entry.
        @throws  IndexOutOfBoundsException if either newPosition < 1 or newPosition > getLength() + 1. */
    public void add(int newPosition, T newEntry) {

   }
   
    @Override
    /** Removes the entry at a given position from this list.
        Entries originally at positions higher than the given
        position are at the next lower position within the list,
        and the list's size is decreased by 1.
        @param givenPosition  An integer that indicates the position of the entry to be removed.
        @return  A reference to the removed entry.
        @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). */
    public T remove(int givenPosition) {
        return null;
    }
   
   @Override
   /** Removes all entries from this list. */
   public void clear() {

   }
   
    @Override
    /** Replaces the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of the entry to be replaced.
       @param newEntry  The object that will replace the entry at the position givenPosition.
       @return  The original entry that was replaced.
       @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). */
    public T replace(int givenPosition, T newEntry) {
        return null;
    }
   
    @Override
    /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). */
    public T getEntry(int givenPosition) {
        return null;
    }
   
    @Override
    /** Retrieves all entries that are in this list in the order in which
       they occur in the list.
       @return  A newly allocated array of all the entries in the list. If the list is empty, the returned array is empty. */
    public T[] toArray() {
        return null;
    }
   
    @Override
    /** Sees whether this list contains a given entry.
       @param anEntry  The object that is the desired entry.
       @return  True if the list contains anEntry, or false if not. */
    public boolean contains(T anEntry) {
        return true;
    }
   
    @Override
    /** Gets the length of this list.
       @return  The integer number of entries currently in the list. */
    public int getLength() {
        return 0;
    }
       
    @Override
    /** Sees whether this list is empty.
        @return  True if the list is empty, or false if not. */
    public boolean isEmpty() {
        return true;
    }

    /** Doubles the capacity of the array list if it is full.
    *  Precondition: checkIntegrity has been called.
    */
    private void ensureCapacity() {
        int capacity = list.length - 1;

        if (numberOfEntries >= capacity) {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity); 
            list = Arrays.copyOf(list, newCapacity + 1);
        } 
   } 

    /**
    * Throws an exception if the client requests a capacity that is too large. 
    * 
    * @param capacity The capacity requested by the client.
    */
    public void checkCapacity(int capacity) {

        if(capacity > MAX_CAPACITY) {

            throw new IllegalStateException("Attempt to create inventory whose capacity exceeds" 
                + "allowed maximum of " + MAX_CAPACITY);

        }

    }

    /**
    * Throws an exception if receiving object is not initialized. 
    */
    public void checkintegrity() {

        if(!integrityOK) {
            throw new SecurityException ("Object is corrupt.");
        }
    }




}