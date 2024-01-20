package ui;

import model.Colour;
import model.Palette;
import model.PaletteLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Runs the Palette Library Application
public class PaletteLibraryApp {
    private static final String JSON_STORE = "./data/paletteLibrary.json";
    private Scanner input;
    private PaletteLibrary pl;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the palette library application
    //          initializes a palette library with no owner name
    public PaletteLibraryApp() {
        pl = new PaletteLibrary("");
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        runPaletteLibrary();
    }

    // MODIFIES: this
    // EFFECTS: initializes new library according to username,
    // processes user input to determine if application is still running
    public void runPaletteLibrary() {
        System.out.println("Welcome to your Palette Library");
        System.out.println("To get started, enter your username, this is how you will access your library");
        String owner = input.nextLine();
        pl.setOwner(owner);
        boolean keepGoing = true;
        String action;
        while (keepGoing) {
            System.out.print("Action (type info for instruction list): ");
            action = input.nextLine();
            action = action.toLowerCase();
            if (action.equals("info")) {
                instructions();
            } else if (action.equals("x")) {
                System.out.println("Would you like to save this library to file? (type sl):");
                action = input.nextLine();
                action = action.toLowerCase();
                processAction(action);
                keepGoing = false;
            } else {
                processAction(action);
            }
        }
        System.out.println("Thanks for using this application " + pl.getOwner() + "!");
    }

    // EFFECTS: prints instructions for user to use application
    public void instructions() {
        System.out.println();
        System.out.println("Available Actions: ");
        System.out.println("Generate a new Random palette: r");
        System.out.println("Generate a new Pastel palette: p");
        System.out.println("Generate a new Greyscale palette: g");
        System.out.println("View specific colour information for a palette (RGB/HEX): v");
        System.out.println("Remove palette from library: t");
        System.out.println("View total amount of palettes in library: l");
        System.out.println("Change palette name: n");
        System.out.println("Change username: u");
        System.out.println("Save Palette Library to File: sl");
        System.out.println("Load Palette Library from File: ll");
        System.out.println("Quit: x");
    }

    // REQUIRES: String "a" is a non-zero length
    // MODIFIES: this
    // EFFECTS: Processes users inputting action
    @SuppressWarnings({"MethodLength", "checkstyle:SuppressWarnings"})
    public void processAction(String a) {
        if (a.equals("r")) {
            generatePalette(0);
        } else if (a.equals("p")) {
            generatePalette(1);
        } else if (a.equals("g")) {
            generatePalette(2);
        } else if (a.equals("v")) {
            displayColourInformation(inputPaletteName());
        } else if (a.equals("t")) {
            removePalette(inputPaletteName());
        } else if (a.equals("l")) {
            System.out.println("Palette Count: " + pl.getPaletteCount());
        } else if (a.equals("n")) {
            renamePalette(inputPaletteName());
        } else if (a.equals("u")) {
            System.out.println("Enter new username: ");
            String newName = input.nextLine();
            pl.setOwner(newName);
        } else if (a.equals("sl")) {
            savePaletteLibrary();
        } else if (a.equals("ll")) {
            loadPaletteLibrary();
        } else {
            System.out.println("Not a valid action, try again.");
        }
    }

    // REQUIRES: String "a" is a non-zero length
    // MODIFIES: this
    // EFFECTS: Renames a palette from the palette library
    public void renamePalette(String a) {
        for (Palette next: pl.getPaletteLibrary()) {
            if (next.getName().equals(a)) {
                System.out.println("Enter new name: ");
                String name = input.nextLine();
                next.setName(name);
            }
        }
    }

    // REQUIRES: String "a" is a non-zero length
    // MODIFIES: this
    // EFFECTS: Removes a palette from the palette library
    // CHECK THIS AGAIN
    public void removePalette(String a) {
        for (int i = 0; i < pl.getPaletteLibrary().size(); i++) {
            if (pl.getPaletteLibrary().get(i).getName().equals(a)) {
                pl.removePalette(pl.getPaletteLibrary().get(i));
            }
        }
    }

    // REQUIRES: type <3 && type>=0
    // MODIFIES: this
    // EFFECTS: Generates a new palette,
    //          provides the option to save the palette to library
    public void generatePalette(int type) {
        String action;
        String name;
        Palette newPalette = new Palette(type);
        for (Colour next: newPalette.getPalette()) {
            System.out.println(next.getInformation());
        }

        System.out.println("Press s to save this palette to library");
        System.out.println("Press x to regenerate: ");

        action = input.nextLine();
        action = action.toLowerCase();
        if (action.equals("s")) {
            System.out.println("Name your palette to save: ");
            name = input.nextLine();
            newPalette.setName(name);
            pl.addPalette(newPalette);
        } else {
            generatePalette(type);
        }
    }

    // REQUIRES: String "a" is a non-zero length
    // EFFECTS: Displays information for a palette
    public void displayColourInformation(String a) {
        for (Palette next: pl.getPaletteLibrary()) {
            if (next.getName().equals(a)) {
                for (Colour c : next.getPalette()) {
                    System.out.println(c.getInformation());
                }
            }
        }
    }

    // EFFECTS: Returns inputted palette name
    public String inputPaletteName() {
        String action;

        System.out.println("Enter palette name: ");
        action = input.nextLine();
        action = action.toLowerCase();

        return action;
    }

    // EFFECTS: saves the palette library to file
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    private void savePaletteLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(pl);
            jsonWriter.close();
            System.out.println("Saved " + pl.getOwner() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    private void loadPaletteLibrary() {
        try {
            pl = jsonReader.read();
            System.out.println("Loaded " + pl.getOwner() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
