import java.util.*;

/* Bogie Class */
class Bogie {
    private String name;
    private int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() { return name; }
    public int getCapacity() { return capacity; }
}

public class Main {

    public static void main(String[] args) {

        System.out.println("=== UC10: Total Seating Capacity Using reduce() ===");

        // Step 1: Create list of bogies
        List<Bogie> bogies = new ArrayList<>();
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 60));
        bogies.add(new Bogie("First Class", 24));

        // Step 2: Stream → map → reduce
        int totalCapacity = bogies
                .stream()
                .map(Bogie::getCapacity)   // extract capacity
                .reduce(0, Integer::sum);  // sum all values

        // Step 3: Display result
        System.out.println("Total Seating Capacity of Train: " + totalCapacity);
    }
}