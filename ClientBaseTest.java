import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ClientBaseTest {
    private ClientBase clientBase;
    @SuppressWarnings("unused")
    private Inventory inventory;

    void setUp() {
        try {
            clientBase = new ClientBase();
            inventory = new Inventory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Inventory.addProduce(new Produce("Apple", "Fruit"), 10);
        Inventory.addProduce(new Produce("Banana", "Fruit"), 10);
        Inventory.addProduce(new Produce("Carrot", "Vegetable"), 10);

        clientBase.addClient("John", "123 Main St", "555-1234", "john@example.com", 35);
        clientBase.addClient("Jane", "456 Elm St", "555-5678", "jane@example.com", 28);

        clientBase.addProduce("John", "Apple", 5);
        clientBase.addProduce("John", "Banana", 3);
        clientBase.addProduce("Jane", "Carrot", 8);
    }

    @Test
    void testAddClient() {
        assertTrue(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", 30));
        assertFalse(clientBase.addClient(null, "123 Street", "123-456-7890", "john@example.com", 30));
        assertFalse(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", -1));
        assertFalse(clientBase.addClient("John", "123 Street", null, null, 30));
    }

    @Test
    void testAddProduce() {
        assertTrue(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", 30));
        assertTrue(clientBase.addProduce("John", "Apple", 5));
        assertFalse(clientBase.addProduce("Jane", "Apple", 5));
        assertFalse(clientBase.addProduce("John", "Apple", 15));
    }

    @Test
    void testGetProduce() {
        assertTrue(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", 30));
        assertTrue(clientBase.addProduce("John", "Apple", 5));
        assertNotNull(clientBase.getProduce("John", "Apple"));
        assertNull(clientBase.getProduce("John", "Banana"));
        assertNull(clientBase.getProduce("Jane", "Apple"));
    }

    @Test
    void testGetClient() {
        assertTrue(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", 30));
        assertNotNull(clientBase.getClient("John"));
        assertNull(clientBase.getClient("Jane"));
    }

    @Test
    void testGetAllClients() {
        assertTrue(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", 30));
        assertTrue(clientBase.addClient("Jane", "456 Street", "987-654-3210", "jane@example.com", 25));
        Client[] clients = clientBase.getAllClients();
        assertEquals(2, clients.length);
        assertEquals("John", clients[0].getName());
        assertEquals("Jane", clients[1].getName());
    }

    @Test
    void testGetOrders() {
        assertTrue(clientBase.addClient("John", "123 Street", "123-456-7890", "john@example.com", 30));
        assertTrue(clientBase.addProduce("John", "Apple", 5));
        assertTrue(clientBase.addProduce("John", "Banana", 3));
        Produce[] orders = clientBase.getOrders("John");
        assertEquals(8, orders.length);
        assertEquals("Apple", orders[0].getName());
        assertEquals("Banana", orders[5].getName());
    }
    
    @Test
    void testGetAllOrders() {
        Produce[][] allOrders = clientBase.getAllOrders();
        assertEquals(2, allOrders.length);

        Produce[] johnOrders = allOrders[0];
        assertEquals(2, johnOrders.length);
        assertEquals("Apple", johnOrders[0].getName());
        assertEquals("Banana", johnOrders[1].getName());

        Produce[] janeOrders = allOrders[1];
        assertEquals(1, janeOrders.length);
        assertEquals("Carrot", janeOrders[0].getName());
    }

    @Test
    void testRemoveClient() {
        assertTrue(clientBase.removeClient("John"));
        assertNull(clientBase.getClient("John"));

        assertFalse(clientBase.removeClient("John"));
    }

    @Test
    void testRemoveOrder() {
        assertTrue(clientBase.removeOrder("John", "Apple", 2));
        assertEquals(3, clientBase.checkQuantity("John", "Apple"));

        assertFalse(clientBase.removeOrder("John", "Apple", 4));
        assertEquals(3, clientBase.checkQuantity("John", "Apple"));

        assertFalse(clientBase.removeOrder("Jane", "Apple", 1));
        assertEquals(-1, clientBase.checkQuantity("Jane", "Apple"));
    }

    @Test
    void testCancelOrder() {
        assertTrue(clientBase.cancelOrder("John", "Apple", 2));
        assertEquals(5, Inventory.getCount("Apple"));
        assertFalse(clientBase.cancelOrder("John", "Apple", 4));
        assertEquals(3, clientBase.checkQuantity("John", "Apple"));

        assertFalse(clientBase.cancelOrder("Jane", "Apple", 1));
        assertEquals(-1, clientBase.checkQuantity("Jane", "Apple"));
    }

    @Test
    void testCheckQuantity() {
        assertEquals(2, clientBase.checkQuantity("John", "Apple"));
        assertEquals(1, clientBase.checkQuantity("John", "Banana"));
        assertEquals(1, clientBase.checkQuantity("Jane", "Carrot"));

        assertEquals(-1, clientBase.checkQuantity("John", "Orange"));
        assertEquals(-1, clientBase.checkQuantity("Bob", "Apple"));
    }
}