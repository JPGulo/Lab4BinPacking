import java.util.*;

public class Main {
    public static void main(String[] args) {
        int B = 20; // bin capacity
        int numBins = 3;

        // Custom Test
        List<Integer> items = Arrays.asList(12, 4, 8, 15, 9, 3, 1, 10);
        System.out.println("Custom Test Output #1:");
        BinPacker packer = new BinPacker(B, numBins);
        packer.packItemsBFI(new ArrayList<>(items));
        packer.printResult();
        System.out.println();

        List<Integer> items2 = Arrays.asList(6, 6, 6, 4, 4, 4, 4, 4);
        System.out.println("Custom Test Output #2:");
        BinPacker packer2 = new BinPacker(B, numBins);
        packer2.packItemsBFI(new ArrayList<>(items2));
        packer2.printResult();
        System.out.println();

        List<Integer> items3 = Arrays.asList(16, 16, 5, 4, 4, 4, 4, 4);
        System.out.println("Custom Test Output #3:");
        BinPacker packer3 = new BinPacker(B, numBins);
        packer3.packItemsBFI(new ArrayList<>(items3));
        packer3.printResult();
        System.out.println();

        // Randomly Seeded Testing
        Random rng = new Random(42); // fixed seed for reproducibility
        int listSize = 8;
        int minItem = 1, maxItem = 20;

        for (int t = 1; t <= 3; t++) {
            List<Integer> randItems = new ArrayList<>();
            for (int i = 0; i < listSize; i++) {
                randItems.add(minItem + rng.nextInt(maxItem - minItem + 1));
            }
            System.out.println("Random Test #" + t + " Output:");
            System.out.println("Items: " + randItems);
            BinPacker randPacker = new BinPacker(B, numBins);
            randPacker.packItemsBFI(new ArrayList<>(randItems));
            randPacker.printResult();
            System.out.println();
        }
    }
} // we are using the Best Fit Increasing strategy
