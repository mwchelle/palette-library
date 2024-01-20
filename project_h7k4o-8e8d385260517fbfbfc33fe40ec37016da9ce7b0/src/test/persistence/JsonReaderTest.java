package persistence;

import model.Palette;
import model.Colour;
import model.PaletteLibrary;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            PaletteLibrary pl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPaletteLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPaletteLibrary.json");
        try {
            PaletteLibrary pl = reader.read();
            assertEquals("library", pl.getOwner());
            assertEquals(0, pl.getPaletteCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPaletteLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPaletteLibrary.json");
        try {
            PaletteLibrary pl = reader.read();
            assertEquals("mich", pl.getOwner());
            assertEquals(2, pl.getPaletteCount());
            List<Palette> palettes = pl.getPaletteLibrary();
            Colour c = palettes.get(0).getPalette().get(0);
            assertEquals(255, c.getRed());
            assertEquals(200, c.getGreen());
            assertEquals(231, c.getBlue());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
