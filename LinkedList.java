/**
   A Linked implementation of a list of objects.
   @author Hasti Abbasi Kenarsari
*/
public class LinkedList<T> implements ListInterface {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedList() {
        initializeDataFields();
    }

    public final void clear() {
        initializeDataFields();
    }

    private void initializeDataFields() {
        firstNode = null;
        numberOfEntries = 0;
    }

    private Node getNodeAt(int givenPosition) {

        // Assertion: (firstNode != null) && (1 <= givenPosition) && (givenPosition <= )numberOfNodes)

        Node currentNode = firstNode;

        for(int counter = 1; counter < givenPosition; counter++) {
            
            currentNode = currentNode.getNextNode();

        }

        // Assertion: currentNode != null

        return currentNode;
    }

    public void add(int givenPosition, T newEntry) {

        if((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1)) {
            Node newNode = new Node(newEntry);

            if(givenPosition == 1) {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            } else {
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeAfter = nodeBefore.getNextNode();
                
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);
            }

            numberOfEntries++;

        } else {
            throw newIndexOutOfBoundsException("Illegal position given to add operation.");
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
                Node nodeBefore = getNodeAt(givenPosition - 1);
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node nodeAfter = nodeToRemove.getNextNode();

                nodeBefore.setNextNode(nodeAfter);

            }

            numberOfEntries--;
            return result;

        } else {
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
        }
    }

    public T replace(int givenPosition, T newEntry) {

        if((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {

            // Assertion: !isEmpty()
            Node desiredNode = getNodeAt(givenPsition);
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

    public boolean contains(T anEntry) {

        boolean found = false;
        Node currentNode = firstNode;

        while(!found && (currentNode != null)) {
            if(anEntry.equals(currentNode.getData())) {
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }
        }

        return found;
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

    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];

        int index = 0; 
        Node currentNode = firstNode;

        while((index < numberOfEntries) && (currentNode != null)) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }

        return result;
    }
}
