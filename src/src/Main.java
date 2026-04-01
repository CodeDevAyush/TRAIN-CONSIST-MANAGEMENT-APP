import java.util.*;
import java.util.stream.Collectors;

public class Main {

    // ===== UC7 Bogie Class =====
    static class Bogie {
        private String name;
        private int capacity;

        public Bogie(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
        }

        public String getName() { return name; }
        public int getCapacity() { return capacity; }
    }

    // ===== UC9 BogieType Class =====
    static class BogieType {
        private String name;
        private String type;
        private int capacity;

        public BogieType(String name, String type, int capacity) {
            this.name = name;
            this.type = type;
            this.capacity = capacity;
        }

        public String getName() { return name; }
        public String getType() { return type; }
        public int getCapacity() { return capacity; }
    }

    public static void main(String[] args) {

        // ===== UC1 =====
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // ===== UC2 =====
        List<String> passengerBogies = new ArrayList<>();
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        passengerBogies.remove("AC Chair");

        System.out.println("\nPassenger Bogies:");
        System.out.println(passengerBogies);

        // ===== UC3 =====
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");

        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);

        // ===== UC4 =====
        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Cargo");
        consist.add("Guard");

        consist.add(2, "Pantry Car");
        consist.removeFirst();
        consist.removeLast();

        System.out.println("\nTrain Consist:");
        System.out.println(consist);

        // ===== UC5 =====
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Guard");
        formation.add("Sleeper");

        System.out.println("\nFormation:");
        System.out.println(formation);

        // ===== UC6 =====
        HashMap<String, Integer> bogieCapacity = new HashMap<>();
        bogieCapacity.put("Sleeper", 72);
        bogieCapacity.put("AC Chair", 60);
        bogieCapacity.put("First Class", 24);

        System.out.println("\nBogie Capacities:");
        for (Map.Entry<String, Integer> entry : bogieCapacity.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ===== UC7 =====
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\nSorted Bogies (Ascending Capacity):");
        for (Bogie b : bogies) {
            System.out.println(b.getName() + " -> " + b.getCapacity());
        }

        // ===== UC8 =====
        Map<String, Integer> bogieData = new HashMap<>();
        bogieData.put("Sleeper", 72);
        bogieData.put("AC Chair", 60);
        bogieData.put("First Class", 24);
        bogieData.put("Luxury", 80);

        List<Map.Entry<String, Integer>> filteredBogies = bogieData
                .entrySet()
                .stream()
                .filter(e -> e.getValue() > 60)
                .toList();

        System.out.println("\nFiltered Bogies (>60 capacity):");
        for (Map.Entry<String, Integer> entry : filteredBogies) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // ===== UC9 =====
        System.out.println("\n=== Grouped Bogies by Type ===");

        List<BogieType> bogieList = new ArrayList<>();
        bogieList.add(new BogieType("Sleeper", "Passenger", 72));
        bogieList.add(new BogieType("AC Chair", "Passenger", 60));
        bogieList.add(new BogieType("First Class", "Passenger", 24));
        bogieList.add(new BogieType("Cargo Box", "Goods", 100));
        bogieList.add(new BogieType("Oil Tanker", "Goods", 120));

        Map<String, List<BogieType>> grouped = bogieList
                .stream()
                .collect(Collectors.groupingBy(BogieType::getType));

        for (Map.Entry<String, List<BogieType>> entry : grouped.entrySet()) {
            System.out.println("\nType: " + entry.getKey());
            for (BogieType b : entry.getValue()) {
                System.out.println(b.getName() + " -> " + b.getCapacity());
            }
        }
    }
}