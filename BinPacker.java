// was reading this file https://www.geeksforgeeks.org/dsa/bin-packing-problem-minimize-number-of-used-bins/

// we are using the Best Fit Increasing strategy

import java.util.*;

public class BinPacker {
    private int binCapacity;
    private int numBins;
    private List<List<Integer>> bins;
    private List<Integer> unpackedItems;

    public BinPacker(int binCapacity, int numBins) {
        this.binCapacity = binCapacity;
        this.numBins = numBins;
        this.bins = new ArrayList<>();
        this.unpackedItems = new ArrayList<>();
        for (int i = 0; i < numBins; i++) {
            bins.add(new ArrayList<>());
        }
    }

    public void packItemsBFI(List<Integer> items) {
        // Sort items in increasing order
        Collections.sort(items);

        for (int item : items) {
            int bestBin = -1;
            int minSpaceLeft = Integer.MAX_VALUE;

            // Try placing into the best-fitting bin
            for (int i = 0; i < numBins; i++) {
                int used = bins.get(i).stream().mapToInt(Integer::intValue).sum();
                int spaceLeft = binCapacity - used;
                if (item <= spaceLeft && (spaceLeft - item) < minSpaceLeft) {
                    bestBin = i;
                    minSpaceLeft = spaceLeft - item;
                }
            }

            if (bestBin != -1) {
                bins.get(bestBin).add(item);
            } else {
                unpackedItems.add(item);
            }
        }
    }

    public void printResult() {
        int totalUnused = 0;
        for (int i = 0; i < bins.size(); i++) {
            int used = bins.get(i).stream().mapToInt(Integer::intValue).sum();
            int unused = binCapacity - used;
            totalUnused += unused;
            System.out.println("Bin " + (i + 1) + ": " + bins.get(i) + " | unused = " + unused);
        }

        System.out.println("Unpacked items: " + unpackedItems);
        System.out.println("Total unused space = " + totalUnused);
    }
}
