package com.sighingnow.questionnaire;

import javax.json.*;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

public final class Writer {
    private JsonWriter writer;
    private JsonObjectBuilder builder;

    public Writer(OutputStream output) {
        this.writer = Json.createWriter(output);
        this.builder = Json.createObjectBuilder();
    }

    public void write() {
        writer.writeObject(builder.build());
        writer.close();
    }

    public Writer add(String key, List<Map<String, String>> elements) {
        JsonArrayBuilder arraybuilder;
        arraybuilder = Json.createArrayBuilder();
        for (Map<String, String> m: elements) {
            JsonObjectBuilder t  = Json.createObjectBuilder();;
            for (Map.Entry<String, String> e: m.entrySet()) {
                t.add(e.getKey(), e.getValue());
            }
            arraybuilder.add(t);
        }
        this.builder.add(key, arraybuilder);
        return this;
    }
}
