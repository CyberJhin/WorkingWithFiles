package org.example;

import org.example.model.Menu;
import org.example.model.MenuItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
public class JsonParsingTest {
    private final ClassLoader cl = JsonParsingTest.class.getClassLoader();


    @Test
    public void testJsonParsing() throws Exception  {
        try (InputStream is = cl.getResourceAsStream("test.json")) {
            assert is != null;
            try (Reader reader = new InputStreamReader(is)) {
                ObjectMapper objectMapper = new ObjectMapper();
                Menu menu = objectMapper.readValue(reader, Menu.class);

                Assertions.assertEquals("file", menu.getId());
                Assertions.assertEquals(Arrays.asList("New", "Open", "Close"),
                        menu.getPopup().getMenuitem().stream()
                                .map(MenuItem::getValue)
                                .collect(Collectors.toList()));

            }
        }
    }
}
