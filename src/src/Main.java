import java.util.*;

/* Bogie Class (UC7) */
class Bogie {
    private String name;
    private int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}

public class Main {

    public static void main(String[] args) {

        // ===== UC1 =====
        System.out.println("=== Train Consist Management App ===");

        List<String> trainConsist = new ArrayList<>();
        System.out.println("Train consist initialized.");
        System.out.println("Initial bogie count: " + trainConsist.size());

        // ===== UC2 =====
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("\nPassenger Bogies after addition:");
        System.out.println(passengerBogies);

        passengerBogies.remove("AC Chair");

        System.out.println("\nAfter removing AC Chair:");
        System.out.println(passengerBogies);

        if (passengerBogies.contains("Sleeper")) {
            System.out.println("\nSleeper bogie exists in the train.");
        }

        System.out.println("\nFinal Passenger Bogie List:");
        System.out.println(passengerBogies);

        // ===== UC3 =====
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // duplicate

        System.out.println("\nBogie IDs (duplicates removed):");
        System.out.println(bogieIds);

        // ===== UC4 =====
        System.out.println("\n=== UC4: Train Consist Using LinkedList ===");

        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        System.out.println("\nInitial Train Consist:");
        System.out.println(consist);

        consist.add(2, "Pantry Car");

        System.out.println("\nAfter inserting Pantry Car at position 2:");
        System.out.println(consist);

        consist.removeFirst();
        consist.removeLast();

        System.out.println("\nAfter removing first and last bogie:");
        System.out.println(consist);

        System.out.println("\nFinal Ordered Train Consist:");
        System.out.println(consist);

        // ===== UC5 =====
        System.out.println("\n=== UC5: Train Formation Using LinkedHashSet ===");

        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper"); // duplicate

        System.out.println("\nFinal Train Formation (Order preserved, no duplicates):");
        System.out.println(formation);

        // ===== UC6 =====
        System.out.println("\n=== UC6: Bogie Capacity Mapping Using HashMap ===");

        HashMap<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 24);

        System.out.println("\nBogie Capacity Details:");
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }

        // ===== UC7 =====
        System.out.println("\n=== UC7: Sort Bogies by Capacity ===");

        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\nBogies sorted by capacity (Ascending):");
        for (Bogie b : bogies) {
            System.out.println(b.getName() + " -> Capacity: " + b.getCapacity());
        }

        // ===== UC8 =====
        System.out.println("\n=== UC8: Filter Passenger Bogies Using Streams ===");

        Map<String, Integer> bogieData = new HashMap<>();
        bogieData.put("Sleeper", 72);
        bogieData.put("AC Chair", 60);
        bogieData.put("First Class", 24);
        bogieData.put("Luxury", 80);

        List<Map.Entry<String, Integer>> filteredBogies = bogieData
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 60)
                .toList();

        System.out.println("\nFiltered Bogies (Capacity > 60):");
        for (Map.Entry<String, Integer> entry : filteredBogies) {
            System.out.println(entry.getKey() + " -> Capacity: " + entry.getValue());
        }
    }
}