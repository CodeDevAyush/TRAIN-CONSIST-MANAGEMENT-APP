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

    // ===== UC18 Linear Search =====
    public static boolean linearSearch(String[] arr, String key) {
        for (String s : arr) {
            if (s.equals(key)) return true;
        }
        return false;
    }

    // ===== UC19 Binary Search =====
    public static boolean binarySearch(String[] arr, String key) {

        if (arr.length == 0) return false; // empty case

        Arrays.sort(arr); // ensure sorted

        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int cmp = key.compareTo(arr[mid]);

            if (cmp == 0) {
                return true; // found
            } else if (cmp < 0) {
                high = mid - 1; // left side
            } else {
                low = mid + 1; // right side
            }
        }

        return false; // not found
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
        GoodsBogie g1 = new GoodsBogie("Cylindrical", "");
        GoodsBogie g2 = new GoodsBogie("Rectangular", "");

        assignCargo(g1, "Petroleum");
        assignCargo(g2, "Petroleum");
        assignCargo(g2, "Coal");

        // ===== UC16 =====
        int[] arr = {72, 56, 24, 70, 60};
        bubbleSort(arr);

        // ===== UC17 =====
        String[] names = {"Sleeper", "AC Chair", "First Class"};
        Arrays.sort(names);

        // ===== UC18 =====
        String[] ids = {"BG101","BG205","BG309","BG412","BG550"};
        System.out.println("\nLinear Search: " + linearSearch(ids, "BG309"));

        // ===== UC19 =====
        System.out.println("\n=== UC19: Binary Search ===");

        System.out.println("Enter number of bogie IDs:");
        int size = sc.nextInt();
        sc.nextLine();

        String[] searchArr = new String[size];

        System.out.println("Enter bogie IDs:");
        for (int i = 0; i < size; i++) {
            searchArr[i] = sc.nextLine();
        }

        System.out.println("Enter ID to search:");
        String key = sc.nextLine();

        boolean found = binarySearch(searchArr, key);

        if (found)
            System.out.println("Bogie ID FOUND");
        else
            System.out.println("Bogie ID NOT FOUND");

        sc.close();
    }
}