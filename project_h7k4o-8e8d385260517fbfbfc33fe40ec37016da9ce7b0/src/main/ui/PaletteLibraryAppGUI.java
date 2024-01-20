package ui;

import model.Event;
import model.EventLog;
import model.PaletteLibrary;
import model.Palette;
import model.Colour;
import persistence.JsonWriter;
import persistence.JsonReader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Creates GUI for Palette Library App
public class PaletteLibraryAppGUI {
    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;
    private static final String JSON_LOCATION = "./data/paletteLibrary.json";
    private static final String IMG_PATH = "src/main/ui/image.png";
    private static PaletteLibrary paletteLibrary;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private final JPanel colourDisplay;
    private JPanel paletteDisplay;
    private JPanel libraryDisplay;
    private JFrame window;

    // EFFECTS: Constructs a new GUI with unnamed palette library, initializes JPanels and Json, initializes the GUI.
    public PaletteLibraryAppGUI() {
        paletteDisplay = new JPanel();
        libraryDisplay = new JPanel();
        colourDisplay = new JPanel();
        jsonWriter = new JsonWriter(JSON_LOCATION);
        jsonReader = new JsonReader(JSON_LOCATION);
        paletteLibrary = new PaletteLibrary("");
        initializeGraphics();
    }

    // EFFECTS: Initializes graphics for Palette Library App
    private void initializeGraphics() {
        hero();
        window = new JFrame("Palette Library App");
        window.setSize(WIDTH, HEIGHT);
        window.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setLayout(new BorderLayout());
        window.setBackground(new Color(252, 249, 220));
        window.getContentPane();
        setDisplay();
        window.add(libraryDisplay, BorderLayout.LINE_START);
        window.add(colourDisplay, BorderLayout.CENTER);
        window.add(paletteDisplay, BorderLayout.LINE_END);
        addPaletteButtons(paletteDisplay);
        addLibraryButtons(libraryDisplay);
        window.validate();
    }

    // EFFECTS: Re-initializes side panel to make updates to display
    public void reinitialize() {
        libraryDisplay.removeAll();
        paletteDisplay.removeAll();
        window.add(libraryDisplay, BorderLayout.LINE_START);
        window.add(paletteDisplay, BorderLayout.LINE_END);
        addPaletteButtons(paletteDisplay);
        addLibraryButtons(libraryDisplay);
        libraryDisplay.setAlignmentX(Component.RIGHT_ALIGNMENT);
        window.validate();
    }

    // EFFECTS: Creates visual component splash screen and entry sequence for GUI
    private void hero() {
        JPanel hero = new JPanel();
        try {
            BufferedImage myPicture = ImageIO.read(new File(IMG_PATH));
            ImageIcon icon = new ImageIcon(myPicture);
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null, label);
            JOptionPane.showMessageDialog(null, "Welcome to your Palette Library\n"
                    + "To get started, enter your username, this is how you will access your library");
            String user =
                    JOptionPane.showInputDialog("Enter username:");
            paletteLibrary.setOwner(user);
        } catch (IOException e) {
            System.out.println("IoException");
        }
        hero.setVisible(false);
    }

    // EFFECTS: Sets layouts for screen display of GUI
    private void setDisplay() {
        libraryDisplay = (JPanel) window.getGlassPane();
        paletteDisplay = (JPanel) window.getGlassPane();
        libraryDisplay.setVisible(true);
        libraryDisplay.setBounds(10, 10, 140, HEIGHT);
        libraryDisplay.setBackground(new Color(250, 255, 252));
        colourDisplay.setBounds(140, 0, 700, 500);
        colourDisplay.setPreferredSize(new Dimension(700, 600));
        paletteDisplay.setBounds(10, 10, 200, HEIGHT);
        paletteDisplay.setBackground(new Color(250, 255, 252));
        colourDisplay.setLayout(new BoxLayout(colourDisplay, BoxLayout.LINE_AXIS));
        paletteDisplay.setLayout(new BoxLayout(paletteDisplay, BoxLayout.PAGE_AXIS));
        libraryDisplay.setLayout(new BoxLayout(libraryDisplay, BoxLayout.PAGE_AXIS));
        colourDisplay.add(Box.createRigidArea(new Dimension(5, 0)));
    }

    // EFFECTS: Adds buttons to Palette Display JPanel
    // MODIFIES: paletteDisplay
    private void addPaletteButtons(Container container) {
        JButton generateRandom = new JButton(new GenerateRandom());
        JButton generatePastel = new JButton(new GeneratePastel());
        JButton generateGreyscale = new JButton(new GenerateGreyscale());
        container.add(generateRandom);
        container.add(generatePastel);
        container.add(generateGreyscale);
    }

    // EFFECTS: Adds buttons to Library Display JPanel
    // MODIFIES: libraryDisplay
    private void addLibraryButtons(Container container) {
        JButton removePalette = new JButton(new RemovePalette());
        JButton viewNumPalettes = new JButton(new ViewNumPalettes());
        JButton renamePalette = new JButton(new RenamePalette());
        JButton changeUsername = new JButton(new ChangeUsername());
        JButton viewColourInfo = new JButton(new DisplayColourInfo());
        JButton saveLibrary = new JButton(new SavePaletteLibrary());
        JButton loadLibrary = new JButton(new LoadPaletteLibrary());
        JButton exit = new JButton(new Exit());
        container.add(removePalette);
        container.add(viewNumPalettes);
        container.add(renamePalette);
        container.add(changeUsername);
        container.add(viewColourInfo);
        container.add(saveLibrary);
        container.add(loadLibrary);
        container.add(exit);
        List<String> currentPalettes = new ArrayList<>();
        currentPalettes.add("Current Palette Library: ");
        for (int i = 0; i < paletteLibrary.getPaletteLibrary().size(); i++) {
            currentPalettes.add(paletteLibrary.getPaletteLibrary().get(i).getName());
        }
        JList currentlySaved = new JList(currentPalettes.toArray());
        container.add(currentlySaved);
    }

    //EFFECTS: Generates new Random Palette
    public class GenerateRandom extends AbstractAction {
        GenerateRandom() {
            super("Generate Random Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            colourDisplay.removeAll();
            window.add(colourDisplay);
            Palette palette = new Palette(0);
            for (int i = 0; i < 5; i++) {
                JPanel colourBlock = new JPanel();
                colourDisplay.add(colourBlock);
                colourBlock.setBackground(new Color(palette.getPalette().get(i).getRed(),
                        palette.getPalette().get(i).getGreen(),
                        palette.getPalette().get(i).getBlue()));
                colourBlock.setBounds(140 * i, 0, 140, 600);
                colourBlock.add(colourLabel(palette.getPalette().get(i)));
            }
            window.repaint();
            colourDisplay.revalidate();
            String answer = JOptionPane.showInputDialog("Would you like to save this palette? (y/n)");
            if (answer.equalsIgnoreCase("y")) {
                paletteLibrary.addPalette(palette);
                String name = JOptionPane.showInputDialog("Name your palette to save: ");
                palette.setName(name);
                reinitialize();
            }
        }
    }

    //EFFECTS: Generates new Pastel Palette
    public class GeneratePastel extends AbstractAction {
        GeneratePastel() {
            super("Generate Pastel Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            colourDisplay.removeAll();
            window.add(colourDisplay);
            Palette palette = new Palette(1);
            for (int i = 0; i < 5; i++) {
                JPanel colourBlock = new JPanel();
                colourDisplay.add(colourBlock);
                colourBlock.setBackground(new Color(palette.getPalette().get(i).getRed(),
                        palette.getPalette().get(i).getGreen(),
                        palette.getPalette().get(i).getBlue()));
                colourBlock.setBounds(140 * i, 0, 140, 600);
                colourBlock.add(colourLabel(palette.getPalette().get(i)));
            }
            window.repaint();
            colourDisplay.revalidate();
            String answer = JOptionPane.showInputDialog("Would you like to save this palette? (y/n)");
            if (answer.equalsIgnoreCase("y")) {
                paletteLibrary.addPalette(palette);
                String name = JOptionPane.showInputDialog("Name your palette to save: ");
                palette.setName(name);
                reinitialize();
            }
        }
    }

    //EFFECTS: Generates new Greyscale Palette
    public class GenerateGreyscale extends AbstractAction {
        GenerateGreyscale() {
            super("Generate Greyscale Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            colourDisplay.removeAll();
            window.add(colourDisplay);
            Palette palette = new Palette(2);
            for (int i = 0; i < 5; i++) {
                JPanel colourBlock = new JPanel();
                colourDisplay.add(colourBlock);
                colourBlock.setBackground(new Color(palette.getPalette().get(i).getRed(),
                        palette.getPalette().get(i).getGreen(),
                        palette.getPalette().get(i).getBlue()));
                colourBlock.setBounds(140 * i, 0, 140, 600);
                colourBlock.add(colourLabel(palette.getPalette().get(i)));
            }
            window.repaint();
            colourDisplay.revalidate();
            String answer = JOptionPane.showInputDialog("Would you like to save this palette? (y/n)");
            if (answer.equalsIgnoreCase("y")) {
                paletteLibrary.addPalette(palette);
                String name = JOptionPane.showInputDialog("Name your palette to save: ");
                palette.setName(name);
                reinitialize();
            }
        }
    }

    public JLabel colourLabel(Colour colour) {
        return new JLabel("RGB: " + colour.getRGB() + ", HEX: #" + colour.hexConversion());
    }

    //EFFECTS: Removes a palette from the library
    public class RemovePalette extends AbstractAction {
        RemovePalette() {
            super("Remove Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String paletteName =
                    JOptionPane.showInputDialog("What is the name of the palette you would like to remove?");
            for (int i = 0; i < paletteLibrary.getPaletteLibrary().size(); i++) {
                if (paletteLibrary.getPaletteLibrary().get(i).getName().equals(paletteName)) {
                    paletteLibrary.removePalette(paletteLibrary.getPaletteLibrary().get(i));
                    reinitialize();
                }
            }
        }
    }

    //EFFECTS: Renames a palette from the library
    public class RenamePalette extends AbstractAction {
        RenamePalette() {
            super("Rename Palette");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String paletteName =
                    JOptionPane.showInputDialog("What is the name of the palette you would like to rename?");
            for (Palette next: paletteLibrary.getPaletteLibrary()) {
                if (next.getName().equals(paletteName)) {
                    String name = JOptionPane.showInputDialog("Enter new name:");
                    next.setName(name);
                    reinitialize();
                }
            }
        }
    }

    //EFFECTS: Renames a palette from the library
    public static class ViewNumPalettes extends AbstractAction {
        ViewNumPalettes() {
            super("Number of Palettes");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JOptionPane.showMessageDialog(null, "Palette Count: "
                    + paletteLibrary.getPaletteCount());
        }
    }

    //EFFECTS: Changes username of the palette library
    public static class ChangeUsername extends AbstractAction {
        ChangeUsername() {
            super("Change Library Username");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String user =
                    JOptionPane.showInputDialog("Enter new username:");
            paletteLibrary.setOwner(user);
        }
    }

    //EFFECTS: Displays colour information for a specified palette
    public static class DisplayColourInfo extends AbstractAction {
        DisplayColourInfo() {
            super("Display Colour Info");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String paletteName =
                    JOptionPane.showInputDialog("What is the name of the palette you would like to see more info of?");
            String info = "Colour Info: \n";
            for (Palette next: paletteLibrary.getPaletteLibrary()) {
                if (next.getName().equals(paletteName)) {
                    for (Colour c : next.getPalette()) {
                        info += c.getInformation() + "\n";
                    }
                }
            }
            JOptionPane.showMessageDialog(null,  info);
        }
    }

    //EFFECTS: Saves Palette Library to file
    public class SavePaletteLibrary extends AbstractAction {
        SavePaletteLibrary() {
            super("Save Library");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            savePaletteLibrary();
        }
    }

    //EFFECTS: Loads Palette Library from file
    public class LoadPaletteLibrary extends AbstractAction {
        LoadPaletteLibrary() {
            super("Load Library");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadPaletteLibrary();
            reinitialize();
        }
    }

    // EFFECTS: saves the palette library to file
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    private void savePaletteLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(paletteLibrary);
            jsonWriter.close();
            System.out.println("Saved " + paletteLibrary.getOwner() + " to " + JSON_LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    private void loadPaletteLibrary() {
        try {
            paletteLibrary = jsonReader.read();
            System.out.println("Loaded " + paletteLibrary.getOwner() + " from " + JSON_LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_LOCATION);
        }
    }

    //EFFECTS: Exits the Palette Library
    public class Exit extends AbstractAction {
        Exit() {
            super("Exit");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String save =
                    JOptionPane.showInputDialog("Would you like to save this library (y/n)?");
            if (save.equalsIgnoreCase("y")) {
                savePaletteLibrary();
            }
            printActions(EventLog.getInstance());
            System.exit(0);
        }
    }

    // EFFECTS: Prints the list of actions performed since the program start
    public void printActions(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}