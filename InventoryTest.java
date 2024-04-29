import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    
    @Test
    @SuppressWarnings("unused")
    public void testInventoryAdd() {
        Inventory inventory;
        try{
            inventory = new Inventory();
        } catch (Exception e) {
            System.out.println("Error creating inventory");
        }
        Produce produce = new Produce("Apple", "Fall");
        Inventory.addProduce(produce, 5);
        assertTrue(Inventory.contains("Apple"));
        assertEquals(5, Inventory.getCount("Apple"));
    }

    @Test
    @SuppressWarnings("unused")
    public void testInventoryRemove() {
        Inventory inventory;
        try{
            inventory = new Inventory();
        } catch (Exception e) {
            System.out.println("Error creating inventory");
        }
        Produce produce = new Produce("Apple", "Fall");
        Inventory.addProduce(produce, 5);
        Inventory.removeProduce("Apple", 3);
        assertEquals(2, Inventory.getCount("Apple"));
    }

    @Test
    @SuppressWarnings("unused")
    public void testInventoryGetProduce() {
        Inventory inventory;
        try{
            inventory = new Inventory();
        } catch (Exception e) {
            System.out.println("Error creating inventory");
        }
        Produce produce = new Produce("Apple", "Fall");
        Inventory.addProduce(produce, 5);
        assertEquals(produce, Inventory.getProduce("Apple"));
    }

    @Test
    public void testInventoryToString() {
        Inventory inventory = null; // Initialize the inventory variable
        try{
            inventory = new Inventory();
        } catch (Exception e) {
            System.out.println("Error creating inventory");
        }
        Produce produce = new Produce("Apple", "Fall");
        Inventory.addProduce(produce, 5);
        String expectedToString = "Apple: \n   Count: 5\n   Status: Out of Season\n";
        assertEquals(expectedToString, inventory.toString());
    }

    @Test
    @SuppressWarnings("unused")
    public void testInventoryInStock() {
        Inventory inventory;
        try{
            inventory = new Inventory();
        } catch (Exception e) {
            System.out.println("Error creating inventory");
        }
        Produce produce = new Produce("Apple", "Fall");
        Inventory.addProduce(produce, 5);
        assertTrue(Inventory.inStock("Apple", 5));
    }



}
