package edu.duke.ece651.project.team5.backend.model;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PairTest {
    
    @Test
    public void testGetKey() {
        // Create a Pair object with key "testKey" and value "testValue"
        Pair<String, String> pair = new Pair<>("testKey", "testValue");
        
        // Verify that the getKey() method returns "testKey"
        assertEquals("testKey", pair.getKey());
    }
    
    @Test
    public void testGetValue() {
        // Create a Pair object with key "testKey" and value "testValue"
        Pair<String, String> pair = new Pair<>("testKey", "testValue");
        
        // Verify that the getValue() method returns "testValue"
        assertEquals("testValue", pair.getValue());
    }
    
}