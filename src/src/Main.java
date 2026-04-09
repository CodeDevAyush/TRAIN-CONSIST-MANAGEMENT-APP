import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

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
        public void setCargo(String cargo) { this.cargo = cargo; }
    }

    // ===== UC14 Exception =====
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

    // ===== UC15 Runtime Exception =====
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    // ===== UC15 Method =====
    public static void assignCargo(GoodsBogie bogie, String cargo) {
        try {
            System.out.println("\nAssigning " + cargo + " to " + bogie.getType() + " bogie");

            if (bogie.getType().equalsIgnoreCase("Rectangular") &&
                    cargo.equalsIgnoreCase("Petroleum")) {

                throw new CargoSafetyException(
                        "Unsafe Assignment: Petroleum cannot be loaded in Rectangular bogie"
                );
            }

            bogie.setCargo(cargo);
            System.out.println("Cargo assigned successfully");

        } catch (CargoSafetyException e) {
            System.out.println("ERROR: " + e.getMessage());

        } finally {
            System.out.println("Assignment process completed (logged)");
        }
    }

    // ===== UC16 Bubble Sort =====
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ===== UC1 =====
        System.out.println("=== Train Consist Management App ===");
        List<String> trainConsist = new ArrayList<>();
        System.out.println("Initial bogie count: " + trainConsist.size());

        // ===== UC2 =====
        System.out.println("\nEnter number of passenger bogies:");
        int n = sc.nextInt();
        sc.nextLine();

        List<String> passengerBogies = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            passengerBogies.add(sc.nextLine());
        }

        System.out.println("Enter bogie to remove:");
        String remove = sc.nextLine();
        passengerBogies.remove(remove);

        System.out.println("Final List: " + passengerBogies);

        // ===== UC3 =====
        Set<String> bogieIds = new HashSet<>();
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG101");
        System.out.println("Unique IDs: " + bogieIds);

        // ===== UC4 =====
        LinkedList<String> consist = new LinkedList<>();
        consist.add("Engine");
        consist.add("Sleeper");
        consist.add("AC");
        consist.add("Guard");

        consist.add(2, "Pantry");
        consist.removeFirst();
        consist.removeLast();
        System.out.println("Updated Consist: " + consist);

        // ===== UC5 =====
        LinkedHashSet<String> formation = new LinkedHashSet<>();
        formation.add("Engine");
        formation.add("Sleeper");
        formation.add("Cargo");
        formation.add("Sleeper");
        System.out.println("Formation: " + formation);

        // ===== UC6 =====
        HashMap<String, Integer> capacityMap = new HashMap<>();
        capacityMap.put("Sleeper", 72);
        capacityMap.put("AC Chair", 60);
        capacityMap.put("First Class", 24);

        capacityMap.forEach((k, v) -> System.out.println(k + " -> " + v));

        // ===== UC7 =====
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("Sorted Bogies:");
        bogies.forEach(b -> System.out.println(b.getName() + " -> " + b.getCapacity()));

        // ===== UC8 =====
        System.out.println("Filtered (>60 capacity):");
        capacityMap.entrySet()
                .stream()
                .filter(e -> e.getValue() > 60)
                .forEach(e -> System.out.println(e.getKey()));

        // ===== UC9 =====
        Map<String, List<Bogie>> grouped =
                bogies.stream()
                        .collect(Collectors.groupingBy(b -> "Passenger"));

        System.out.println("Grouped: " + grouped);

        // ===== UC10 =====
        int totalSeats = bogies.stream()
                .map(Bogie::getCapacity)
                .reduce(0, Integer::sum);

        System.out.println("Total Seats: " + totalSeats);

        // ===== UC11 =====
        System.out.println("\nEnter Train ID:");
        String trainId = sc.next();

        System.out.println("Enter Cargo Code:");
        String cargoCode = sc.next();

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
            System.out.println("\nEnter Passenger Bogie Type:");
            String type = sc.next();

            System.out.println("Enter Capacity:");
            int cap = sc.nextInt();

            PassengerBogie pb = new PassengerBogie(type, cap);
            System.out.println("Created: " + pb.getType() + " with capacity " + pb.getCapacity());

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // ===== UC15 =====
        System.out.println("\n=== UC15: Safe Cargo Assignment ===");

        GoodsBogie g1 = new GoodsBogie("Cylindrical", "");
        GoodsBogie g2 = new GoodsBogie("Rectangular", "");

        assignCargo(g1, "Petroleum");   // safe
        assignCargo(g2, "Petroleum");   // unsafe
        assignCargo(g2, "Coal");        // safe again

        System.out.println("Program continues after handling exceptions");

        // ===== UC16 =====
        System.out.println("\n=== UC16: Bubble Sort Passenger Capacities ===");

        System.out.println("Enter number of bogies:");
        int size = sc.nextInt();

        int[] capacities = new int[size];

        System.out.println("Enter capacities:");
        for (int i = 0; i < size; i++) {
            capacities[i] = sc.nextInt();
        }

        bubbleSort(capacities);

        System.out.println("Sorted Capacities:");
        for (int c : capacities) {
            System.out.print(c + " ");
        }

        System.out.println();

        sc.close();
    }
}