// was reading this file https://www.geeksforgeeks.org/dsa/bin-packing-problem-minimize-number-of-used-bins/

// we are using the Best Fit Increasing strategy

import java.util.*;
import java.util.stream.Collectors;

public class BinPacker {
    private final int binCapacity;
    private final int numBins;
    private final List<List<Integer>> bins = new ArrayList<>();
    private final List<Integer> unpackedItems = new ArrayList<>();


  
    public BinPacker(int binCapacity, int numBins) {
        this.binCapacity = binCapacity;
        this.numBins = numBins;
        for (int i = 0; i < numBins; i++) bins.add(new ArrayList<>());
    }

    public void packItemsBFI(List<Integer> items) {
        for (List<Integer> b : bins) { b.clear(); }
        unpackedItems.clear();
        
        List<Integer> candidates = items.stream().filter(x -> x <= binCapacity).collect(Collectors.toList());

        candidates.sort(Comparator.reverseOrder());

        int seed = Math.min(numBins, candidates.size());
        int[] sums = new int[numBins];
        for (int i = 0; i < seed; i++) {
            int x = candidates.get(i);
            bins.get(i).add(x);
            sums[i] += x;
        }

        List<Integer> remaining = candidates.subList(seed, candidates.size());

        int totalPackedable = candidates.stream().mapToInt(Integer::intValue).sum();
        int target = Math.min(binCapacity, (int)Math.ceil(totalPackedable / (double)numBins));

        for (int x : remaining) {
            int best = -1;
            int bestPrimary = Integer.MAX_VALUE;
            int bestSecondary = Integer.MIN_VALUE;
            for (int i = 0; i < numBins; i++) {
                if (sums[i] + x <= binCapacity) {
                    int newSum = sums[i] + x;
                    int primary = Math.abs(newSum - target);
                    int secondary = binCapacity - newSum;
                    if (primary < bestPrimary || (primary == bestPrimary && secondary > bestSecondary)) {
                        bestPrimary = primary;
                        bestSecondary = secondary;
                        best = i;
                    }
                }
            }
            if (best >= 0) {
                bins.get(best).add(x);
                sums[best] += x;
            } else {
                unpackedItems.add(x);
            }
        }
    }

    public void printResult() {
        int totalUnused = 0;
        for (int i = 0; i < bins.size(); i++) {
            int used = bins.get(i).stream().mapToInt(Integer::intValue).sum();
            int unused = binCapacity - used;
            totalUnused += unused;
            System.out.println("Bin " + (i + 1) + " (sum=" + used + "): " + bins.get(i) + " | unused = " + unused);
        }
        System.out.println("Unpacked items: " + unpackedItems);
        System.out.println("Total unused space = " + totalUnused);
    }

}
