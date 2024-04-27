import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProduceTest {

    @Test
    public void testProduce() {
        Produce produce = new Produce("Apple", "fall");
        assertEquals("Apple", produce.getName());
        assertEquals("fall", produce.getSeason());

        produce = new Produce("Strawberry", "winter");
        assertEquals("Strawberry", produce.getName());
        assertEquals("winter", produce.getSeason());
    }

    @Test
    public void testCheckInSeason() {
        Produce produce = new Produce("Apple", "fall");
        assertFalse(produce.checkInSeason(produce.getSeason()));

        produce = new Produce("Strawberry", "winter");
        assertFalse(produce.checkInSeason(produce.getSeason()));
    }

    @Test
    public void testGetCurrentSeason() {
    	Produce produce = new Produce("Apple", "fall");
        assertNotEquals("fall", produce.getCurrentSeason());

        produce = new Produce("Strawberry", "winter");
        assertNotEquals("winter", produce.getCurrentSeason());
    }

    @Test
    public void testCompareTo() {
        Produce produce1 = new Produce("Apple", "fall");
        Produce produce2 = new Produce("Banana", "summer");
        Produce produce3 = new Produce("Apple", "fall");

        assertTrue(produce1.compareTo(produce2) < 0);
        assertEquals(0, produce1.compareTo(produce3));
    }
}