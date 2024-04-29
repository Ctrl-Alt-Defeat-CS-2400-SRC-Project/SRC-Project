// rewrote ClientBaseTest 

/**
   A class that serves a Unit Test using JUnit 5 to test the ClientBase class
   @author Medha Swarnachandrabalaji, Kenzie Lam
*/
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class ClientBaseTest {
    private static ClientBase clientBase;
    //private static Inventory inventory;

    @BeforeEach
    void setUp() {
        try {
            clientBase = new ClientBase();
        } catch (IOException e) {
            fail("ClientBase initialization failed.");
        }
        // try {
        //     inventory = new Inventory();
        // } catch (IOException e) {
        //     fail("Inventory initialization failed.");
        // }
    }

    @Test
    void testAddClient() {
        assertTrue(clientBase.addClient("Jane Smith", "3801 W Temple Ave", "123-456-7890", "jane.smith@example.com", 18));
        assertTrue(clientBase.containsClient("Jane Smith"));
        assertFalse(clientBase.addClient(null, null, null, null, -1));
        assertFalse(clientBase.containsClient(null));
    }

    @Test
    void testChangeClientInfo() {
        clientBase.addClient("Jane Doe", "456 Elm St", "987-654-3210", "jane.doe@example.com", 25);
        assertTrue(clientBase.changeClientInfo("Jane Doe", "789 Oak St", null, null, 30));
        Client client = clientBase.getClient("Jane Doe");
        assertNotNull(client);
        assertEquals("789 Oak St", client.getAddress());
        assertEquals("987-654-3210", client.getPhone());
        assertEquals("jane.doe@example.com", client.getEmail());
        assertEquals(30, client.getAge());
    }

    @Test
    void testAddProduce() {
        // making produce
        Produce apple = new Produce("Apple", "Summer");
        // adding produce to inventory
        Inventory.addProduce(apple, 6);
        assertTrue(clientBase.addProduce("Jane Smith", "Apple", 1));
        // "NonexistentUser" is not in the client base
        assertFalse(clientBase.addProduce("NonexistentUser", "Apple", 3));
        // There are not enough "Apple" units in the inventory
        assertFalse(clientBase.addProduce("John Doe", "Apple", 100));
    }

    @Test
    void testRemoveOrder() {
        // making produce
        Produce banana = new Produce("Banana", "year round");
        // adding produce to inventory
        Inventory.addProduce(banana, 6);
        clientBase.addClient("Bob", "123 Elm St", "555-555-5555", "dfgs", 5);
        clientBase.addProduce("Bob", "Banana", 1);
        assertTrue(clientBase.removeOrder("Bob", "Banana", 1));
        assertNull(clientBase.getProduce("Bob", "Banana"));
    }

    @Test
    void testCancelOrder() {
        // making produce
        Produce orange = new Produce("Orange", "Winter");
        // adding produce to inventory
        Inventory.addProduce(orange, 6);
        clientBase.addClient("Carol Green", "1212 Oak St", "777-888-9999", "carol.green@example.com", 35);
        assertTrue(clientBase.addProduce("Carol Green", "Orange", 2));
        assertTrue(clientBase.cancelOrder("Carol Green", "Orange", 1));
        assertNull(clientBase.getProduce("Carol Green", "Orange"));
    }

    @AfterEach
    void tearDown() {
        clientBase = null;
    }
}
