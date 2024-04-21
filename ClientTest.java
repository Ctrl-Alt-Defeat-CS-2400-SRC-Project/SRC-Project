/**
 * Unit test class for the Client class
 * @author Kenzie Lam 
 */


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class ClientTest {

    @Test
    public void testClientConstructor() {
        Client client = new Client("John Doe", "123 Main St", "123-456-7890", "john.doe@example.com", 30);

        assertEquals("John Doe", client.getName());
        assertEquals("123 Main St", client.getAddress());
        assertEquals("123-456-7890", client.getPhone());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals(30, client.getAge());
    }

    @Test
    public void testClientToString() {
        Client client = new Client("Jane Doe", "456 Elm St", "987-654-3210", "jane.doe@example.com", 25);
        String expectedToString = "Name: Jane Doe\nAddress: 456 Elm St\nPhone: 987-654-3210\nEmail: jane.doe@example.com\nAge: 25";

        assertEquals(expectedToString, client.toString());
    }

    @Test
    public void testClientCompareTo() {
        Client client1 = new Client("Alice", "789 Oak St", "111-222-3333", "alice@example.com", 20);
        Client client2 = new Client("Bob", "321 Pine St", "444-555-6666", "bob@example.com", 22);

        assertEquals(-1, client1.compareTo(client2));
        assertEquals(0, client1.compareTo(client1));
        assertEquals(1, client2.compareTo(client1));
    }
    
}