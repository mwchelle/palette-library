package model;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// JUnit tests for Colour class
class ColourTest {
    private Colour c1;
    private Colour c2;
    private Colour c3;

    @BeforeEach
    void runBefore() {
        c1 = new Colour(0);
        c2 = new Colour(1);
        c3 = new Colour(2);
    }

    // Tests whether the constructor is constructing a random colour correctly
    @Test
    void testConstructorGenerateRandom() {
        assertTrue(c1.getRed() >= 0 && c1.getRed() <= 256);
        assertTrue(c1.getGreen() >= 0 && c1.getGreen() <= 256);
        assertTrue(c1.getBlue() >= 0 && c1.getBlue() <= 256);
        assertEquals(0, c1.getType());
    }

    // Tests whether the constructor is constructing a pastel colour correctly
    @Test
    void testConstructorGeneratePastel() {
        assertTrue(c2.getRed() >= 200 && c2.getRed() <= 256);
        assertTrue(c2.getGreen() >= 200 && c2.getGreen() <= 256);
        assertTrue(c2.getBlue() >= 0 && c2.getBlue() <= 256);
        assertEquals(1, c2.getType());
    }

    // Tests whether the constructor is constructing a greyscale colour correctly
    @Test
    void testConstructorGenerateGreyscale() {
        assertTrue(c3.getRed() == c3.getGreen() && c3.getGreen() == c3.getBlue());
        assertEquals(2, c3.getType());
    }

    // Tests whether a random colour can be regenerated
    @Test
    void testGenerateRandom() {
        int red = c1.getRed();
        int green = c1.getGreen();
        int blue = c1.getBlue();

        c1.generate();

        assertTrue(red != c1.getRed());
        assertTrue(green != c1.getGreen());
        assertTrue(blue != c1.getBlue());
    }

    // Tests whether a pastel colour can be regenerated
    @Test
    void testGeneratePastel() {
        int red = c2.getRed();
        int green = c2.getGreen();
        int blue = c2.getBlue();

        c2.generate();

        assertTrue(red != c2.getRed());
        assertTrue(green != c2.getGreen());
        assertTrue(blue != c2.getBlue());
    }

    // Tests whether a greyscale colour can be regenerated
    @Test
    void testGenerateGreyscale() {
        int red = c3.getRed();
        int green = c3.getGreen();
        int blue = c3.getBlue();

        c3.generate();

        assertTrue(red != c3.getRed());
        assertTrue(green != c3.getGreen());
        assertTrue(blue != c3.getBlue());
    }

    // Tests if information outputted is accurate to the colour profile
    @Test
    void testInformation() {
        int red = c1.getRed();
        int green = c1.getGreen();
        int blue = c1.getBlue();

        List<String> info = c1.getInformation();

        assertEquals("R: " + red, info.get(0));
        assertEquals("G: " + green, info.get(1));
        assertEquals("B: " + blue, info.get(2));
        assertEquals("HEX: #" + Integer.toHexString((red * 256 + green) * 256 + blue),
                info.get(3));
    }

    @Test
    void testForceColour() {
        c1.forceColour(5, 10, 20);
        int red = c1.getRed();
        int green = c1.getGreen();
        int blue = c1.getBlue();

        assertEquals(5, red);
        assertEquals(10, green);
        assertEquals(20, blue);
    }
}