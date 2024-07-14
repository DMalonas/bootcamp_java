package org.d3;

import java.io.*;
import java.util.*;

public class RandomNamePicker {
    private static Map<String, Integer> pointsMap = new HashMap<>();
    private static int runCount = 0;
    private static final String DATA_FILE = "names_points_data.txt";

    public static void main(String[] args) {
        loadData();

        List<String> names = Arrays.asList("Chrisanthi", "Giannis", "Golfo", "Katerina",
                "Konstantinos", "Petros", "Anestis", "Anthi",
                "Despoina", "George", "Panagiotis");

        Random random = new Random();
        runCount++;

        String selectedName = names.get(random.nextInt(names.size()));
        System.out.println("Selected: " + selectedName);

        applyRules(selectedName, names, random);

        saveData();
    }

    private static void applyRules(String selectedName, List<String> names, Random random) {
        if ("Chrisanthi".equals(selectedName)) {
            // Chrisanthi picks someone to speak.
            names = new ArrayList<>(names);
            names.remove("Chrisanthi");
            selectedName = names.get(random.nextInt(names.size()));
            System.out.println("Chrisanthi picks " + selectedName + " to speak.");
        }

        // Update social currency points
        pointsMap.put(selectedName, pointsMap.getOrDefault(selectedName, 0) + 1);

        // Rule based on run count
        if (runCount % 2 == 0) {
            // Even run number rule
            if (random.nextBoolean()) {
                System.out.println(selectedName + " has to discuss the topic with a person of their choosing.");
            }
        } else {
            // Odd run number rule
            System.out.println(selectedName + " has to talk for at least 3 minutes.");
        }

        // Name length divisibility condition
        if (selectedName.length() % runCount == 0) {
            System.out.println(selectedName + " gets a conditional pass.");
        } else {
            int talkTime = Math.max(3, selectedName.length() % runCount);
            talkTime = Math.min(talkTime, 5);
            System.out.println(selectedName + " has to talk for " + talkTime + " minutes.");
        }

        // Screen sharing rule
        if (runCount == names.size() - 1) { // Excluding Chrisanthi
            System.out.println(selectedName + " has to share their screen and discuss the topic for 3-5 minutes.");
        }

        // Delegate to the next closest name
        int choice = 0;
        while (true) {
            try {
                System.out.println("(1 for yes any other integer for no");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                break;
            } catch (Exception e) {
                continue;
            }
        }

        if (choice != 1) {
            String nextSpeaker = findNextSpeaker(selectedName, names);
            System.out.println(selectedName + " delegates to " + nextSpeaker);
            pointsMap.put(nextSpeaker, pointsMap.getOrDefault(nextSpeaker, 0) + 1);
        }
    }

    public static String findNextSpeaker(String currentName, List<String> names) {
        Collections.sort(names);

        int currentIndex = Collections.binarySearch(names, currentName);
        if (currentIndex < 0) {
            // The current name is not in the list, find the insertion point
            currentIndex = -currentIndex - 1;
        } else {
            // Move to the next name in the list
            currentIndex++;
        }

        if (currentIndex >= names.size()) {
            // If the current index is beyond the list size, loop back to the start
            currentIndex = 0;
        }

        return names.get(currentIndex);
    }

    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    pointsMap.put(parts[0], Integer.parseInt(parts[1]));
                } else if (parts.length == 1) {
                    runCount = Integer.parseInt(parts[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
            bw.write(String.valueOf(runCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
