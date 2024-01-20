package model;

import java.util.*;
import org.json.JSONObject;
import org.json.JSONArray;
import persistence.Writable;

// Represents a list of Palettes with a
// library owner, number of palettes
public class PaletteLibrary implements Writable {
    private String owner;
    private int paletteCount;
    private List<Palette> library;

    //REQUIRES: name has a non-zero length
    //EFFECTS: constructs a palette library,
    //         name is the owner's id for the library,
    //         paletteCount is initialized to 0
    public PaletteLibrary(String name) {
        this.owner = name;
        this.paletteCount = 0;
        this.library = new ArrayList<>();
    }


    public void addPalette(Palette p) {
        EventLog.getInstance().logEvent(new Event("Added Palette to Library."));
        this.library.add(p);
        this.paletteCount++;
    }

    public int getPaletteCount() {
        return paletteCount;
    }

    public void setOwner(String newName) {
        EventLog.getInstance().logEvent(new Event("Library owner name set to " + newName + "."));
        this.owner = newName;
    }

    public String getOwner() {
        return owner;
    }

    public List<Palette> getPaletteLibrary() {
        return library;
    }

    // MODIFIES: this
    // EFFECTS: removes the specified palette from the library
    public void removePalette(Palette p) {
        EventLog.getInstance().logEvent(new Event("Remove " + p.getName() + " from library."));
        this.library.remove(p);
        this.paletteCount--;
    }

    // EFFECTS: adds palettes and palette library to a JSON object
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("owner", owner);
        json.put("palettes", palettesToJson());
        return json;
    }

    // EFFECTS: returns palettes in this palette library as a JSON array
    // Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
    private JSONArray palettesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Palette next : library) {
            jsonArray.put(next.toJson());
        }

        return jsonArray;
    }
}
