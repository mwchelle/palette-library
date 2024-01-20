package model;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// JUnit tests for PaletteLibrary class
public class PaletteLibraryTest {
    PaletteLibrary pl1;
    List<Palette> paletteLibrary1;
    Palette p1;
    Palette p2;

    @BeforeEach
    void runBefore() {
        pl1 = new PaletteLibrary("Michelle");
        paletteLibrary1 = pl1.getPaletteLibrary();
        p1 = new Palette(0);
        p2 = new Palette(1);
    }

    // Test whether the constructor is correctly generating
    // a palette library
    @Test
    void testConstructor() {
        assertEquals("Michelle", pl1.getOwner());
        assertEquals(0, pl1.getPaletteCount());
        assertEquals(0,paletteLibrary1.size() );
    }

    // Test whether the owner's name can be set
    @Test
    void testSetOwner() {
        pl1.setOwner("Karan");
        assertEquals("Karan", pl1.getOwner());
    }

    // Test whether the palette counter is increasing
    // as palettes are added and if the palette is being added
    @Test
    void testAddPalettes() {
        pl1.addPalette(p1);
        assertEquals(1, pl1.getPaletteCount());
        pl1.addPalette(p2);
        assertEquals(2, pl1.getPaletteCount());
    }

    @Test
    void testRemovePalette() {
        pl1.addPalette(p1);
        pl1.addPalette(p2);
        pl1.removePalette(p1);
        assertEquals(1, pl1.getPaletteCount());
    }
}
