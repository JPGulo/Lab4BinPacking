import java.util.*;

public class Main {
    public static void main(String[] args) {



        // Custom test input
        int B = 20; // bin capacity
        List<Integer> items = Arrays.asList(12, 4, 8, 15, 9, 3, 1, 10);



        
        // Create a packer with 3 bins
        BinPacker packer = new BinPacker(B, 3);
        packer.packItemsBFI(new ArrayList<>(items)); // pass copy since sorting mutates list
        packer.printResult();
    }
}
