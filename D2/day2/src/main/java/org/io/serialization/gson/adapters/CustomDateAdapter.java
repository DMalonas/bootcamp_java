package org.io.serialization.gson.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateAdapter extends TypeAdapter<Date> {

    // SimpleDateFormat to format dates in the "yyyy-MM-dd" pattern
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        // When writing a date to JSON
        if (value == null) {
            out.nullValue(); // Write null if the date is null
        } else {
            out.value(formatter.format(value)); // Otherwise, format the date and write it as a string
        }
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        // When reading a date from JSON
        try {
            return formatter.parse(in.nextString()); // Parse the date string from JSON
        } catch (Exception e) {
            // If parsing fails, throw an IOException with details
            throw new IOException("Error parsing date: " + in.nextString(), e);
        }
    }
}
