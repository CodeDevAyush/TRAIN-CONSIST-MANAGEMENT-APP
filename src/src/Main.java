import java.util.*;

public class Main {

    // Bogie class
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

    public static void main(String[] args) {

        System.out.println("=== UC13: Performance Comparison ===");

        // Large dataset
        List<Bogie> big = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            big.add(new Bogie("B" + i, i % 100));
        }

        // ===== Traditional Loop =====
        long start1 = System.nanoTime();

        List<Bogie> loopResult = new ArrayList<>();
        for (Bogie b : big) {
            if (b.getCapacity() > 60) {
                loopResult.add(b);
            }
        }

        long end1 = System.nanoTime();

        // ===== Stream API =====
        long start2 = System.nanoTime();

        List<Bogie> streamResult = big.stream()
                .filter(b -> b.getCapacity() > 60)
                .toList();

        long end2 = System.nanoTime();

        // ===== Results =====
        System.out.println("Loop time: " + (end1 - start1) + " ns");
        System.out.println("Stream time: " + (end2 - start2) + " ns");

        System.out.println("Loop result size: " + loopResult.size());
        System.out.println("Stream result size: " + streamResult.size());
    }
}