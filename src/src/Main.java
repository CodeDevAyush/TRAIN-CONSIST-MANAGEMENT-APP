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
            System.out.println("\nAssigning " + cargo + " to " + bogie.getType());

            if (bogie.getType().equalsIgnoreCase("Rectangular") &&
                    cargo.equalsIgnoreCase("Petroleum")) {
                throw new CargoSafetyException("Unsafe: Petroleum not allowed in Rectangular bogie");
            }

            bogie.setCargo(cargo);
            System.out.println("Cargo assigned");

        } catch (CargoSafetyException e) {
            System.out.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("Assignment completed");
        }
    }

    // ===== UC16 Bubble Sort =====
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
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

        Arrays.sort(arr);

        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = key.compareTo(arr[mid]);

            if (cmp == 0) return true;
            else if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    // ===== UC20 Validation Wrapper =====
    public static boolean safeSearch(String[] arr, String key, boolean useBinary) {

        if (arr.length == 0) {
            throw new IllegalStateException("No bogies available for search");
        }

        if (useBinary) {
            return binarySearch(arr, key);
        } else {
            return linearSearch(arr, key);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // ===== UC1 =====
        System.out.println("=== Train Consist Management App ===");

        // ===== UC20 =====
        System.out.println("\n=== UC20: Safe Search with Validation ===");

        System.out.println("Enter number of bogie IDs:");
        int n = sc.nextInt();
        sc.nextLine();

        String[] ids = new String[n];

        if (n > 0) {
            System.out.println("Enter bogie IDs:");
            for (int i = 0; i < n; i++) {
                ids[i] = sc.nextLine();
            }
        }

        System.out.println("Enter ID to search:");
        String key = sc.nextLine();

        try {
            boolean found = safeSearch(ids, key, true); // using binary search

            if (found)
                System.out.println("Bogie FOUND");
            else
                System.out.println("Bogie NOT FOUND");

        } catch (IllegalStateException e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        sc.close();
    }
}