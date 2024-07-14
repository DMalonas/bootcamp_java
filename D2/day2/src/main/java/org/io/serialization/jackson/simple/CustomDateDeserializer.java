package org.io.serialization.jackson.simple;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom deserializer for java.util.Date objects.
 * This class extends Jackson's JsonDeserializer to provide custom deserialization logic for Date objects.
 */
public class CustomDateDeserializer extends JsonDeserializer<Date> {

    // Define the date format for parsing the date strings.
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Method called by Jackson for each Date object being deserialized.
     *
     * @param p     The JsonParser reading the JSON content.
     * @param ctxt  The DeserializationContext that can be used to obtain additional configuration or to
     *              assert regarding the processing of the deserialized instance.
     * @return Date The deserialized Date object.
     * @throws IOException If an error occurs while reading JSON content or parsing the date.
     */
    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String date = p.getText().trim();
        try {
            // Check if the date string is a number (timestamp)
            if(date.matches("\\d+")){
                long timestamp = Long.parseLong(date);
                // If it's a timestamp, construct the Date object using the timestamp.
                return new Date(timestamp);
            }
            // Otherwise, parse it as a formatted date string based on the defined format.
            return dateFormat.parse(date);
        } catch (ParseException e) {
            // If parsing fails, throw an IOException with the failed date and the original ParseException.
            throw new IOException("Failed to parse date: " + date, e);
        }
    }
}
