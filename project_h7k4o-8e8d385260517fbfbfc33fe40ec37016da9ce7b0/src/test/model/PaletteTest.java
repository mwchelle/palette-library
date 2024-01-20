package model;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// JUnit tests for Palette class
public class PaletteTest {
    Palette p1;
    Palette p2;
    Palette p3;

    @BeforeEach
    void runBefore() {
        p1 = new Palette(0);
        p2 = new Palette(1);
        p3 = new Palette(2);
    }

    // Tests whether the constructor is correctly
    // generating a random palette
    @Test
    void testConstructorRandom() {
        assertEquals(0, p1.getType());
        assertEquals("", p1.getName());

        List<Colour> palette1 = p1.getPalette();
        assertEquals(0, palette1.get(0).getType());
        assertEquals(0, palette1.get(1).getType());
        assertEquals(0, palette1.get(2).getType());
        assertEquals(0, palette1.get(3).getType());
        assertEquals(0, palette1.get(4).getType());
        assertEquals(5, palette1.size());
    }

    // Tests whether the constructor is correctly
    // generating a pastel palette
    @Test
    void testConstructorPastel() {
        assertEquals(1, p2.getType());
        assertEquals("", p2.getName());

        List<Colour> palette2 = p2.getPalette();
        assertEquals(1, palette2.get(0).getType());
        assertEquals(1, palette2.get(1).getType());
        assertEquals(1, palette2.get(2).getType());
        assertEquals(1, palette2.get(3).getType());
        assertEquals(1, palette2.get(4).getType());
        assertEquals(5, palette2.size());
    }

    // Tests whether the constructor is correctly
    // generating a greyscale palette
    @Test
    void testConstructorGreyscale() {
        assertEquals(2, p3.getType());
        assertEquals("", p3.getName());

        List<Colour> palette3 = p3.getPalette();
        assertEquals(2, palette3.get(0).getType());
        assertEquals(2, palette3.get(1).getType());
        assertEquals(2, palette3.get(2).getType());
        assertEquals(2, palette3.get(3).getType());
        assertEquals(2, palette3.get(4).getType());
        assertEquals(5, palette3.size());
    }

    // Tests if all colours in a palette are unique
    @Test
    void testOnlyUniqueColours() {
        List<Colour> palette = p1.getPalette();
        List<Colour> holder = new ArrayList<>(); // holds values
        List<Colour> duplicates = new ArrayList<>(); // holds duplicates

        for (Colour next:palette) {
            if (!holder.contains(next)) {
                holder.add(next);
            } else {
                duplicates.add(next);
            }
        }

        assertEquals(0, duplicates.size());
        assertEquals(holder, palette);
    }

    // Tests if a non-unique colour will be replaced
    @Test
    void testOnlyUniqueColoursRegenerate() {
        List<Colour> palette = p1.getPalette();

        Colour duplicate = new Colour(0); // makes new colour
        int red = palette.get(0).getRed();
        int green = palette.get(0).getGreen();
        int blue = palette.get(0).getBlue();
        duplicate.forceColour(red, green, blue);
        p1.forceAdd(duplicate); // force add duplicate

        palette = p1.getPalette(); // refresh palette
        assertEquals(6, palette.size());

        assertEquals(1, duplicateChecker(palette).size());
        p1.onlyUniqueColours();
        palette = p1.getPalette();
        assertEquals(0, duplicateChecker(palette).size());
    }

    // Helper function that tests whether there are duplicates within
    // a palette, returns the list of duplicate colours
    public List<String> duplicateChecker(List<Colour> palette) {
        List<String> holder = new ArrayList<>();
        List<String> duplicate = new ArrayList<>();
        for (int i = 0; i < palette.size(); i++) {
            if (!holder.contains(palette.get(i).getRGB())) {
                holder.add(palette.get(i).getRGB());
            } else {
                duplicate.add(palette.get(i).getRGB());
            }
        }
        return duplicate;
    }

    // Tests name changing
    @Test
    void testSetName() {
        p1.setName("Fav");
        assertEquals("Fav", p1.getName());
    }
}
