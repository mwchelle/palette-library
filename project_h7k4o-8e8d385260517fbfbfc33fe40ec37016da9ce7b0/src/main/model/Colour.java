package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;


// Represents a colour profile with status of
// type of colour and RGB (red, green, blue) numbers
public class Colour implements Writable {
    private int type; // type 0 = random, type 1 = pastel, type 2 = greyscale
    private int red; // red variable
    private int green; // green variable
    private int blue; // blue variable

    //REQUIRES: type == 0 || type == 1 || type == 2
    //EFFECTS: constructs a colour profile with the inputted type,
    ///        initializes the variable
    //         then generates RGB values according to specified colour type
    public Colour(int type) {
        this.type = type;
        red = -1;
        green = -1;
        blue = -1;
        generate();
    }

    // MODIFIES: this
    // EFFECTS: generates the colour profile in accordance to the type
    public void generate() {
        switch (type) {
            case 0:
                Random rand = new Random();
                this.red = rand.nextInt(256);
                this.green = rand.nextInt(256);
                this.blue = rand.nextInt(256);
                break;
            case 1:
                Random rand1 = new Random();
                this.red = rand1.nextInt((256 - 200) + 1) + 200;
                this.green = rand1.nextInt((256 - 200) + 1) + 200;
                this.blue = rand1.nextInt((256 - 200) + 1) + 200;
                break;
            case 2:
                Random rand2 = new Random();
                this.red = rand2.nextInt(256);
                this.green = this.red;
                this.blue = this.red;
                break;
        }
    }

    // EFFECTS: converts the RGB colour information to a hex code
    public String hexConversion() {
        return (Integer.toHexString((this.red * 256 + this.green) * 256 + this.blue));
    }

    // EFFECTS: returns a List including RGB and hexcode information
    public List<String> getInformation() {
        List<String> information = new ArrayList<>();
        information.add("R: " + getRed());
        information.add("G: " + getGreen());
        information.add("B: " + getBlue());
        information.add("HEX: #" + hexConversion());
        return information;
    }

    //MODIFIES: this
    //EFFECTS: forcibly edits a colour's RGB values
    public void forceColour(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getType() {
        return type;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public String getRGB() {
        return (red + "," + blue + "," + green);
    }

    // EFFECTS: adds palettes and colours to a JSON object
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("r", red);
        json.put("g", green);
        json.put("b", blue);
        return json;
    }

}
