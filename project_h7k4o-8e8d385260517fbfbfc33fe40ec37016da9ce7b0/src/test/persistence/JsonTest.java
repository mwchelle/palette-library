package persistence;

import model.Palette;

import static org.junit.jupiter.api.Assertions.*;

// Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
public class JsonTest {
    protected void checkPalette(String name, int type, Palette p){
        assertEquals(name, p.getName());
        assertEquals(type, p.getType());
    }
}
