package ex;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.annotation.processing.SupportedAnnotationTypes;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

public class CombineJSONFiles {
    private static String StringBuilder;

    public static void main(String[] args) {


        com.google.gson.Gson gson;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();


        JsonArray jsonArray1 = new JsonArray();
        jsonArray1.add(createJsonObject(0, "This is a J.P.M. report detailing the latest trends in global financial markets."));
        jsonArray1.add(createJsonObject(1, "To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' ROI."));
        jsonArray1.add(createJsonObject(2, "Thus we'll adjust our strategies based on these insights."));
        jsonArray1.add(createJsonObject(3, "U.S. market trends will also be compared to ensure global alignment."));


        JsonArray jsonArray2 = new JsonArray();
        jsonArray2.add(createJsonObjectWithMatchedValue(0, "This is a J.P.M. report detailing the latest trends in global financial markets.", "This is a JP Morgan report detailing the latest trends in global financial markets.2"));
        jsonArray2.add(createJsonObjectWithMatchedValue(1, "To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' ROI.", "To ensure we are allocating our resources effectively, we must consistently track and analyze our marketing campaigns' return of investment."));
        jsonArray2.add(createJsonObjectWithMatchedValue(2, "Thus we'll adjust our strategies based on these insights.", "Thus we'll adjust our strate-gies based on these insights."));
        jsonArray2.add(createJsonObjectWithMatchedValue(3, "U.S. market trends will also be compared to ensure global alignment.", "- United States of America market trends will also be compared to ensure global alignment.'3"));


        JsonArray combinedArray = new JsonArray();
        combinedArray.addAll(jsonArray1);
        combinedArray.addAll(jsonArray2);

        try (FileWriter file = new FileWriter("combined_output.json")) {
            switch (gson.toString()) {
                default -> throw new IllegalStateException("Unexpected value: " + gson.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JsonObject createJsonObject(int index, String value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("index", index);
        jsonObject.addProperty("value", value);
        return jsonObject;
    }

    private static JsonObject createJsonObjectWithMatchedValue(int index, String value, String matchedValue) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("index", index);
        jsonObject.addProperty("value", value);
        jsonObject.addProperty("matchedValue", matchedValue);
        return jsonObject;
    }

    private static class Gson {
        public void toString(JsonArray combinedArray, FileWriter file) {

        }
    }



    private static class JsonArray {
        public void addAll(JsonArray jsonArray1) {

        }

        public void add(JsonObject jsonObjectWithMatchedValue) {

        }
    }

    private static class JsonObject {
        public void addProperty(String index, int index1) {
        }

        public void addProperty(String value, String value1) {

        }
    }
}
