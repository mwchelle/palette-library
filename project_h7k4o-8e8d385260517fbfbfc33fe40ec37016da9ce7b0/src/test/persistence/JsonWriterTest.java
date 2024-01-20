package persistence;

import model.Palette;
import model.PaletteLibrary;
import model.Colour;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            PaletteLibrary pl = new PaletteLibrary("new");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPaletteLibrary() {
        try {
            PaletteLibrary pl = new PaletteLibrary("library");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPaletteLibrary.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPaletteLibrary.json");
            pl = reader.read();
            assertEquals("library", pl.getOwner());
            assertEquals(0, pl.getPaletteCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPaletteLibrary() {
        try {
            PaletteLibrary pl = new PaletteLibrary("mich");
            pl.addPalette(new Palette(1));
            pl.addPalette(new Palette(2));
            pl.addPalette(new Palette(0));
            List<Palette> palettes = pl.getPaletteLibrary();
            palettes.get(0).setName("cool");
            palettes.get(1).setName("boo");
            palettes.get(2).setName("random");

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPaletteLibrary.json");
            writer.open();
            writer.write(pl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPaletteLibrary.json");
            pl = reader.read();
            assertEquals("mich", pl.getOwner());
            List<Palette> palette = pl.getPaletteLibrary();
            assertEquals(3, palette.size());
            checkPalette("cool", 1, palette.get(0));
            checkPalette("boo", 2, palette.get(1));
            checkPalette("random", 0, palette.get(2));
            List<Colour> colours = palette.get(0).getPalette();

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
