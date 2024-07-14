package org.io.serialization.jackson.simple;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Date;

/**
 * Custom serializer for java.util.Date objects.
 * This class extends Jackson's StdSerializer to provide custom serialization logic for Date objects.
 * It is used to serialize Date objects as a long timestamp representing the milliseconds since Unix epoch.
 */
public class CustomDateSerializer extends StdSerializer<Date> {

    /**
     * Default constructor for the serializer.
     * Calls the superclass constructor with Date.class to indicate this serializer is for Date objects.
     */
    public CustomDateSerializer() {
        super(Date.class);
    }

    /**
     * Method called by Jackson for each Date object being serialized.
     *
     * @param value     The Date object being serialized.
     * @param gen       The JsonGenerator used to write JSON content.
     * @param provider  The SerializerProvider that can be used to get serializers for serializing Objects value contains.
     * @throws IOException  If an error occurs while writing JSON content.
     */
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        // Writes the date as a number to the JSON output.
        // The number represents the milliseconds since January 1, 1970, 00:00:00 GMT.
        gen.writeNumber(value.getTime());
    }
}



