/**
   An interface that describes the operations of a bag of objects.
   @author Hasti Abbasi Kenarsari
*/
public interface BagInterface<T> {

    /**
     * Gets the current number of entries in the bag
     * @return The current number of entries in the bag as an integer
     */
    public int getCurrentSize();

    /**
     * Adds a new entry to the bag 
     * @param newEntry The object of type T that is going to be added as a new entry
     * @return True if the addition of the object is successful, false if the addition is unsuccessful
     */
    public boolean add(T newEntry);

     /**
     * Gets all entries that are in the bag
     * @return An array of all entries in the bag (emptpy bag returns empty array)
     */
    public T[] toArray();

    /**
     * Sees whether this bag is empty 
     * @return True if the bag is empty, false if the bag is not empty
     */
    public boolean isEmpty();

    /**
     * If possible, removes one unspecified entry from the bag
     * @return The removed entry if the removal was successful, null if unsuccessful
     */
    public T remove();

    /**
     * If possible, removes an occurence of a given entry from the bag
     * @param anEntry The entry of type T that is going to be removed
     * @return True if the removal of the entry is successful, false if removal is unsuccessful  
     */
    public boolean remove(T anEntry);

    /**
     * Removes all the entries in the bag
     */
    public void clear();

    /**
     * Gets the frequency of a given entry in the bag/counts the number of occurences
     * @param anEntry The entry to be counted
     * @return The # of times anEntry appears in the bag
     */
    public int getFrequencyOf(T anEntry);

    /**
     * Sees whether the bag contains a given entry 
     * @param anEntry The entry to be found 
     * @return True if the bag contains anEntry, false if the bag does not contain anEntry
     */
    public boolean contains(T anEntry);

    /**
     * Returns a string representation of the contents of the bag.
     * 
     * @return A string representation of the bag enclosed by brackets and divded by commas. 
     */
    public String toString();

}
