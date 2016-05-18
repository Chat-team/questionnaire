package com.sighingnow.questionnaire;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by He Tao on 2015/12/15.
 */
public final class Reader {
    private JsonObject content;

    public Reader(InputStream input) throws IOException {
        this.content = Json.createReader(input).readObject();
    }

    public String getString(String key) {

        return this.content.getString(key);
    }

    public int getInt(String key) {

        return this.content.getInt(key);
    }
}
