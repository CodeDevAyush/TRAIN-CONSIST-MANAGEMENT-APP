import java.util.*;
import java.util.regex.*;

public class Main {

    // ===== Common Classes =====

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

    static class GoodsBogie {
        private String type;
        private String cargo;

        public GoodsBogie(String type, String cargo) {
            this.type = type;
            this.cargo = cargo;
        }

        public String getType() { return type; }
        public String getCargo() { return cargo; }
    }

    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String msg) {
            super(msg);
        }
    }

    static class PassengerBogie {
        private String type;
        private int capacity;

        public PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0)
                throw new InvalidCapacityException("Capacity must be greater than zero");
            this.type = type;
            this.capacity = capacity;
        }

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

        if (passengerBogies.contains("Sleeper")) {
            System.out.println("Sleeper exists");
        }
        System.out.println(passengerBogies);

        // ===== UC3 =====
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        System.out.println(bogieIds);

        // ===== UC4 =====
        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Guard");

        consist.add(2, "Pantry");
        consist.removeFirst();
        consist.removeLast();
        System.out.println(consist);

        // ===== UC5 =====
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Sleeper");
        System.out.println(formation);

        // ===== UC6 =====
        HashMap<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 24);

        for (Map.Entry<String, Integer> e : capacityMap.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        // ===== UC7 =====
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        for (Bogie b : bogies) {
            System.out.println(b.getName() + " -> " + b.getCapacity());
        }

        // ===== UC8 =====
        List<Map.Entry<String, Integer>> filtered =
                capacityMap.entrySet()
                        .stream()
                        .filter(e -> e.getValue() > 60)
                        .toList();

        for (Map.Entry<String, Integer> e : filtered) {
            System.out.println(e.getKey());
        }

        // ===== UC9 =====
        Map<String, List<Bogie>> grouped =
                bogies.stream()
                        .collect(Collectors.groupingBy(b -> "Passenger"));

        System.out.println(grouped);

        // ===== UC10 =====
        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seats: " + totalSeats);

        // ===== UC11 =====
        String trainId = "TRN-1234";
        String cargoCode = "PET-AB";

        Pattern trainPattern = Pattern.compile("TRN-\\d{4}");
        Pattern cargoPattern = Pattern.compile("PET-[A-Z]{2}");

        System.out.println(trainPattern.matcher(trainId).matches() ? "Train VALID" : "Train INVALID");
        System.out.println(cargoPattern.matcher(cargoCode).matches() ? "Cargo VALID" : "Cargo INVALID");

        // ===== UC12 =====
        List<GoodsBogie> goods = new ArrayList<>();
        goods.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goods.add(new GoodsBogie("Box", "Coal"));

        boolean isSafe = goods.stream()
                .allMatch(b -> !b.getType().equals("Cylindrical") || b.getCargo().equals("Petroleum"));

        System.out.println(isSafe ? "SAFE" : "UNSAFE");

        // ===== UC13 =====
        List<Bogie> big = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            big.add(new Bogie("B" + i, i % 100));
        }

        long start1 = System.nanoTime();
        List<Bogie> loop = new ArrayList<>();
        for (Bogie b : big) {
            if (b.getCapacity() > 60) loop.add(b);
        }
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        List<Bogie> stream = big.stream()
                .filter(b -> b.getCapacity() > 60)
                .toList();
        long end2 = System.nanoTime();

        System.out.println("Loop time: " + (end1 - start1));
        System.out.println("Stream time: " + (end2 - start2));

        // ===== UC14 =====
        try {
            PassengerBogie pb1 = new PassengerBogie("Sleeper", 72);
            System.out.println(pb1.getType());

            PassengerBogie pb2 = new PassengerBogie("AC", -10);
        } catch (InvalidCapacityException e) {
            System.out.println(e.getMessage());
        }
    }
}