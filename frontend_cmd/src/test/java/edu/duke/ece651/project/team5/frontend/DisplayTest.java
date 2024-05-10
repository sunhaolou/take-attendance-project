package edu.duke.ece651.project.team5.frontend;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class DisplayTest {

    @Test
    public void testSimpleDisplay() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Display.simpleDisplay("Hello, world!");
    }

    @Test
    public void testDisplayToUser() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3");
        Display.displayToUser("Choose an option:", options);
        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("Choose an option:\n");
        for (int i = 0; i < options.size(); i++) {
            expectedOutput.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }
    }
}
