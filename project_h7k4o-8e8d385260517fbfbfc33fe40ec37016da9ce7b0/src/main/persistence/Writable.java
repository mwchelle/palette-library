package persistence;

import org.json.JSONObject;

// Code influenced by the JsonSerializationDemo https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master/lib
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
