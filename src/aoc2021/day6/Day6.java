package aoc2021.day6;

import java.io.File;
import java.util.*;

public class Day6 {

    final static long parentCycle = 6;
    final static long childCycle = 8;

    public static void main(String[] args) {
        // read file
        String path = "src/aoc2021/day6/day6.txt";
        String[] input = new String[0];
        try (Scanner reader = new Scanner(new File(path))) {
            input = reader.next().split(",");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // TODO refactor List (used before) to Long[]
        List<Long> inputAsNumbers = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            inputAsNumbers.add(Long.valueOf(input[i]));
        }

        Long[] testArray = new Long[]{3L, 4L, 3L, 1L, 2L};

        long result;
        System.out.println("\nTest cases:");
        result = partOne(new ArrayList<>(Arrays.asList(testArray)), 80);
        System.out.printf("test: %d\n", result);

        System.out.println("\nPart one:");
        result = partOne(new ArrayList<>(inputAsNumbers), 80);
        System.out.printf("res1: %d\n", result);

        System.out.println("\nPart two:");
        result = partOne(new ArrayList<>(inputAsNumbers), 256);
        System.out.printf("res2: %d\n", result);

    }

    public static long partOne(List<Long> fishs, int totalDays) {

        Map<Long, Long> parentCount = new HashMap<>();
        Map<Long, Long> childrenCount = new HashMap<>();

        // fill parentCount and childrenCount with default values
        for (long i = 0; i <= childCycle; i++) {
            if (i <= parentCycle) {
                parentCount.put(i, 0L);
            }
            childrenCount.put(i, 0L);
        }

        // fill parentCount with initial amount of fishs per day left
        for (Long fish : fishs) {
            Long value = parentCount.get(fish);
            parentCount.replace(fish, value + 1);
        }


        int day = 0;
        while (day++ < totalDays) {
            long newFish = parentCount.get(0L) + childrenCount.get(0L);
            iterateFishCount(parentCount, parentCycle, newFish);
            iterateFishCount(childrenCount,childCycle, newFish);
        }

        long totalFish = calculateFishCount(parentCount) + calculateFishCount(childrenCount);
        return totalFish;

    }

    public static long calculateFishCount(Map<Long, Long> fishCount){
        long total = 0;
        // TODO possible to make generic, and let long be decided later?
        for (Long fish :fishCount.values()) {
            total += fish;
        };
        return total;
    }

    public static void iterateFishCount(Map<Long, Long> fishCount, long cycle, long newFish){
        // decrease fish days
        for (long i = 0; i < cycle; i++) {
            long nextDays = fishCount.get(i+1);
            fishCount.replace(i,nextDays);
        }
        // insert new/recurring fish
        fishCount.replace(cycle, newFish);
    }
}
