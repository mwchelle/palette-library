package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents a palette with 5 colours included, a name, and a status
// showing whether the palette is pastel, greyscale, or random
public class Palette implements Writable {
    private Colour c1; // colours 1-5 within the palette
    private Colour c2;
    private Colour c3;
    private Colour c4;
    private Colour c5;
    private String name; // name of palette, empty until saved
    private int type; // type 0 = random, type 1 = pastel, type 2 = greyscale
    private List<Colour> palette;

    //REQUIRES: type == 0 || type == 1 || type == 2
    //EFFECTS: constructs a palette with a blank string name
    //         type is the type of palette it is,
    //         adds 5 colours generated according to type to the palette
    //         ensures that the colours are unique
    public Palette(int type) {
        this.type = type;

        c1 = new Colour(type);
        c2 = new Colour(type);
        c3 = new Colour(type);
        c4 = new Colour(type);
        c5 = new Colour(type);

        this.palette = new ArrayList<>();
        palette.add(c1);
        palette.add(c2);
        palette.add(c3);
        palette.add(c4);
        palette.add(c5);

        onlyUniqueColours();
        EventLog.getInstance().logEvent(new Event("New Palette generated."));
    }

    //MODIFIES: this
    //EFFECTS: removes and replaces any repeating colours in the palette
    public void onlyUniqueColours() {
        ArrayList<String> compareList = new ArrayList<>();
        for (int i = 0; i < palette.size(); i++) {
            if (!compareList.contains(palette.get(i).getRGB())) {
                compareList.add(palette.get(i).getRGB());
            } else {
                palette.get(i).generate();
            }
        }
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }

    public List<Colour> getPalette() {
        return palette;
    }

    public int getType() {
        return type;
    }

    //MODIFIES: this
    //EFFECTS: used to test duplicate colours, forcibly adds to palette
    public void forceAdd(Colour c) {
        palette.add(c);
    }

    // EFFECTS: adds palettes and colours to a JSON object
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", type);
        json.put("colours", coloursToJson());
        return json;
    }

    // EFFECTS: returns palettes in this palette library as a JSON array
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    private JSONArray coloursToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Colour next : palette) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }
}
