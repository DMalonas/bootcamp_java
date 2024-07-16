package Exercises;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonExercise {
    public static void main(String[] args) {
        try {// Διαβάζουμε το JSON αρχείο
            JsonArray jsonArray = JsonParser.parseReader(new FileReader("C:\\Users\\user\\Desktop\\lectures\\practice_exercise_week2\\practice\\input\\Sample.json")).getAsJsonArray();
            // Διαβάζουμε το αρχείο κειμένου
            List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\user\\Desktop\\lectures\\practice_exercise_week2\\practice\\input\\Sample.txt"));
            // Συνενώνουμε τις γραμμές του αρχείου κειμένου
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line).append(" ");
            }
            String text = sb.toString().trim();

            // Διαχωρίζουμε το κείμενο σε προτάσεις
            List<String> sentences = new ArrayList<>();
            Pattern pattern = Pattern.compile("([^.!?]*[.!?])");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                sentences.add(matcher.group().trim());
            }

            // Δημιουργούμε το νέο JSON Array
            JsonArray outputArray = new JsonArray();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String value = jsonObject.get("value").getAsString();
                JsonObject newObject = new JsonObject();
                newObject.addProperty("index", jsonObject.get("index").getAsInt());
                newObject.addProperty("value", value);
                if (i < sentences.size()) {
                    newObject.addProperty("matchedValue", sentences.get(i));
                } else {
                    newObject.addProperty("matchedValue", "");
                }
                outputArray.add(newObject);
            }
            // Γράφουμε σε ένα νέο αρχείο JSON
            try (FileWriter file = new FileWriter("C:\\Users\\user\\Desktop\\lectures\\practice_exercise_week2\\practice\\output\\output.json")) {
                Gson gsonPretty = new GsonBuilder().setPrettyPrinting().create();
                file.write(gsonPretty.toJson(outputArray));
                file.flush();
            }
            System.out.println("Το αποτέλεσμα έχει γραφτεί στο output.json");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
