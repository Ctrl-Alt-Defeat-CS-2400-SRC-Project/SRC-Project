/**
   A Linked implementation of a list of objects.
   @author Hasti Abbasi Kenarsari
*/
public class LinkedList<T> implements ListInterface<T> {

    private Node<T> firstNode;
    private int numberOfEntries;

    public LinkedList() {
        initializeDataFields();
    }

    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public void add(T newEntry) {

        Node<T> newNode = new Node<T>(newEntry);

        if(isEmpty()) {
            firstNode = newNode;
        } else {
            Node lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode);
        }

        numberOfEntries++;
    }

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

    public final void clear() {
        initializeDataFields();
    }

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

    public T getEntry(int givenPosition) {
        if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            // Assertion: !isEmpty()
            return getNodeAt(givenPosition).getData();
        } else {
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
        }
    }

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

    public int getLength() {

        return numberOfEntries;

    }


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

    public T decreaseProduce(int givenPosition, int count) {
        getNodeAt(givenPosition).decrementCount(count);
        return getEntry(givenPosition);
    }

    public T increaseProduce(int givenPosition, int count) {
        getNodeAt(givenPosition).incrementCount(count);
        return getEntry(givenPosition);
    }

    public boolean inStock(int givenPosition, int count) {
        return getNodeAt(givenPosition).getCount() >= count;
    }

    public int getStock(int givenPosition) {
        return getNodeAt(givenPosition).getCount();
    }

    private Node<T> getNodeAt(int givenPosition) {

        // Assertion: (firstNode != null) && (1 <= givenPosition) && (givenPosition <= )numberOfNodes)

        Node<T> currentNode = firstNode;

        for(int counter = 1; counter < givenPosition; counter++) {

            currentNode = currentNode.getNextNode();

        }

        // Assertion: currentNode != null

        return currentNode;
    }

    public int getCount() {

        return numberOfEntries;
        
    }

}