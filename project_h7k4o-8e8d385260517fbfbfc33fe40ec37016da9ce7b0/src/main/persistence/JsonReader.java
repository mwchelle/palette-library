package persistence;

import model.Palette;
import model.Colour;
import model.PaletteLibrary;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.*;

import org.json.*;

// Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
// Represents a reader that reads palette library from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public PaletteLibrary read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePaletteLibrary(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses palette library from JSON object and returns it
    private PaletteLibrary parsePaletteLibrary(JSONObject jsonObject) {
        String owner = jsonObject.getString("owner");
        PaletteLibrary pl = new PaletteLibrary(owner);
        addPalettes(pl, jsonObject);
        return pl;
    }

    // MODIFIES: pl
    // EFFECTS: parses palettes from JSON object and adds them to palette library
    private void addPalettes(PaletteLibrary pl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("palettes");
        for (Object json : jsonArray) {
            JSONObject nextPalette = (JSONObject) json;
            addPalette(pl, nextPalette);
        }
    }

    // MODIFIES: pl
    // EFFECTS: parses palette from JSON object and adds it to wpalette library
    private void addPalette(PaletteLibrary pl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int type = jsonObject.getInt("type");
        Palette palette = new Palette(type);
        palette.setName(name);
        JSONArray jsonArray = jsonObject.getJSONArray("colours");
        List<Colour> current = palette.getPalette();
        for (int i = 0; i < 5; i++) {
            int red = ((JSONObject) jsonArray.get(i)).getInt("r");
            int green = ((JSONObject) jsonArray.get(i)).getInt("g");
            int blue = ((JSONObject) jsonArray.get(i)).getInt("b");
            current.get(i).forceColour(red, green, blue);
        }
        pl.addPalette(palette);
    }

}
