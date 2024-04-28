/**
   A Linked implementation of a list of objects.
   @author Hasti Abbasi Kenarsari
*/
public class LinkedList<T> implements ListInterface<T> {

    private Node<T> firstNode;
    private int numberOfEntries;

    /**
     * The main constructor of the LinkedList class. Utilized ot initialize data fields
     * involving setting the first node to null and setting the number of entries to 0. 
     */
    public LinkedList() {
        initializeDataFields();
    }

    /**
     * Sets the first node ot null and sets the number of entries to zero.
     */
    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    /**
     * Adds a new object to the linked list. Increases the number of entries accordingly.
     * 
     * @param newEntry The object that is desired to be added. 
     */
    public void add(T newEntry) {

        Node<T> newNode = new Node<T>(newEntry);

        if(isEmpty()) {
            firstNode = newNode;
        } else {

            Node<T> lastNode = getNodeAt(numberOfEntries);
         
            lastNode.setNextNode(newNode);
        }

        numberOfEntries++;
    }

    /**
     * Adds a new object to the linked list at a specific position. Increases the number 
     * of entries accordingly. 
     * 
     * @param givenPosition The integer position at which the new object will be added.
     * @param newEntry The object that is desired to be added. 
     * @throws IndexOutOfBoundsException Throws an exception if the given position is not valid. 
     */
    public void add(int givenPosition, T newEntry) {

        if((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
            Node<T> newNode = new Node<T>(newEntry);

            if(givenPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeAfter = nodeBefore.getNextNode();

                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }

            numberOfEntries++;

        } else {
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }

    }

    /**
     * Removes an object at a specified position. Decreases the number of entries accordingly. 
     * 
     * @param givenPosition The integer position of the object that is going to be removed. 
     * @return The object that is removed. 
     * @throws IndexOutOfBoundsException Throws an exception if the given position is not valid. 
     */
    public T remove(int givenPosition) {

        T result = null;

        if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {

            // Assertion: !isEmpty()
            if(givenPosition == 1) {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            } else {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
               
                Node<T> nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);

            }

            numberOfEntries--;
            return result;

        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }

    /**
     * Clears the linked list by initializing the data fields (sets the first node to null 
     * and sets number of entries to 0).
     */
    public final void clear() {
        initializeDataFields();
    }

    /**
     * Replaces the object at the given position with a new entry. 
     * 
     * @param givenPosition The integer position of the object that is going to be replaced. 
     * @param newEntry The new object that is going to take the place of the old one. 
     * @return The original entry.
     * @throws IndexOutOfBoundsException Throws an exception if the given position is not valid. 
     */
    public T replace(int givenPosition, T newEntry) {

        if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {

            // Assertion: !isEmpty()
            Node<T> desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;

        } else {
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
        }
    }

    /**
     * Returns the object at the given position. 
     * 
     * @param givenPosition The integer position of the desired object. 
     * @return The object that is retreived. 
     * @throws IndexOutOfBoundsException Throws an exception if the given position is not valid. 
     */
    public T getEntry(int givenPosition) {
        if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // Assertion: !isEmpty()
            return getNodeAt(givenPosition).getData();
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
        }
    }

    /**
     * Returns an array representation of the objects in the linked list. 
     * 
     * @return An array of objects in the linked list. 
     */
    public T[] toArray() {

        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node<T> currentNode = firstNode;

        while((index < numberOfEntries) && (currentNode != null)) {

            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;

        }

        return result;
    }

    /**
     * Determines wether the linked list contains a specific entry. 
     * 
     * @param anEntry The entry that is going to be found. 
     * @return True if the entry is found, and false otherwise. 
     */
    public boolean contains(T anEntry) {

        boolean found = false;
        Node<T> currentNode = firstNode;

        while(!found && (currentNode != null)) {
            if(anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }

        return found;
    }

    /**
     * Returns the integer position of a given entry. 
     * 
     * @param anEntry The entry whose integer position is desired. 
     * @return The integer position of the entry. 
     */
    public int getPosition(T anEntry) {
        int position = 1;
        Node<T> currentNode = firstNode;

        while((currentNode != null) && !anEntry.equals(currentNode.getData())) {
            currentNode = currentNode.getNextNode();
            position++;
        }

        if(currentNode == null) {
            position = -1;
        }

        return position;
    }

    /**
     * Returns the number of entries in the linked list. 
     * 
     * @return The integer representation of the number of entries in the linked list. 
     */
    public int getLength() {

        return numberOfEntries;

    }


    /**
     * Determines wether the linked list is empty. 
     * 
     * @return True if the linked list is empty, and false otherwise. 
     */
    public boolean isEmpty() {

        boolean result;
        if(numberOfEntries == 0) {
            // Assertion: firstNode == null
            result = true;
        } else {
            // Assertion: firstNode != null
            result = false;
        }

        return result;
    }

    /**
     * Decreases the quantity of the object at the specified position by the desired amount. 
     * 
     * @param givenPosition The position of the object to be decreemented.  
     * @param count The number of times the count of the object is going to be decreeased. 
     * @return The object at the given position. 
     */
    public T decreaseProduce(int givenPosition, int count) {
        getNodeAt(givenPosition).decrementCount(count);
        return getEntry(givenPosition);
    }

    /**
     * Increases the quanitty of the object at the specified position by the desired amount.
     * 
     * @param givenPosition The position of the object ot be incremented. 
     * @param count The nuber of times the count of the object is going to be increased. 
     * @return The object at the given position. 
     */
    public T increaseProduce(int givenPosition, int count) {
        getNodeAt(givenPosition).incrementCount(count);
        return getEntry(givenPosition);
    }

    /**
     * Determines wether a specified quantity of the object at the given position are in stock. 
     * 
     * @param givenPosition The position of the object to be checked. 
     * @param count The number of desired objects. 
     * @return True if the desired count of the object is in stock (at minimum), and false otherwise. 
     */
    public boolean inStock(int givenPosition, int count) {
        return getNodeAt(givenPosition).getCount() >= count;
    }

    /**
     * Returns the integer quantity of the object at the given position. 
     * 
     * @param givenPosition The integer position of the desired object. 
     * @return The number of items in stock. 
     */
    public int getStock(int givenPosition) {
        return getNodeAt(givenPosition).getCount();
    }

    /**
     * Returns the node at the given position. 
     * 
     * @param givenPosition The integer position of the node. 
     * @return The node at the desired position. 
     */
    private Node<T> getNodeAt(int givenPosition) {

        // Assertion: (firstNode != null) && (1 <= givenPosition) && (givenPosition <= )numberOfNodes)

        Node<T> currentNode = firstNode;

        for(int counter = 1; counter < givenPosition; counter++) {

            currentNode = currentNode.getNextNode();

        }

        // Assertion: currentNode != null

        return currentNode;
    }

    /**
     * Returns the number of entries in the linked list. 
     * 
     * @return The integer representation of the number of entires. 
     */
    public int getCount() {

        return numberOfEntries;
        
    }

}