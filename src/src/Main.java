import java.util.*;

public class Main {

    // GoodsBogie class
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

    public static void main(String[] args) {

        System.out.println("=== UC12: Safety Compliance Check for Goods Bogies ===");

        // Create list of goods bogies
        List<GoodsBogie> goodsBogies = new ArrayList<>();
        goodsBogies.add(new GoodsBogie("Cylindrical", "Petroleum"));
        goodsBogies.add(new GoodsBogie("Box", "Coal"));
        goodsBogies.add(new GoodsBogie("Open", "Grain"));

        // Safety Rule:
        // Cylindrical bogies should ONLY carry Petroleum
        boolean isSafe = goodsBogies
                .stream()
                .allMatch(b ->
                        !b.getType().equals("Cylindrical") ||
                                b.getCargo().equals("Petroleum")
                );

        // Result
        System.out.println(isSafe ? "Train is SAFE" : "Train is UNSAFE");
    }
}