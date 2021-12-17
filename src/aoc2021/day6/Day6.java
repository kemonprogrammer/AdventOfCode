package aoc2021.day6;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    final static int parentCycle = 6;
    final static int childCycle = 8;
    public static void main(String[] args) {
        String path = "src/aoc2021/day6/day6.txt";
        String[] input = new String[0];
        try (Scanner reader = new Scanner(new File(path))) {
            input = reader.next().split(",");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Integer> inputAsNumbers = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            inputAsNumbers.add(Integer.valueOf(input[i]));
        }

        Integer[] testArray = new Integer[]{3,4,3,1,2};

        System.out.println("\nTest cases:");
        int test = partOne(new ArrayList<>(Arrays.asList(testArray)), 80);
        System.out.printf("test: %d\n", test);

        System.out.println("\nPart one:");
        int res1 = partOne(new ArrayList<>(inputAsNumbers), 80);
        System.out.printf("res1: %d\n", res1);

        System.out.println("\nPart two:");
        int res2 = partOne(new ArrayList<>(inputAsNumbers), 256);
        System.out.printf("res2: %d\n", res2);


    }

    public static int partOne(List<Integer> fishs, int totalDays) {

        int initialSize = fishs.size();
        int day = 0;
//        System.out.printf("Initial state: %d\n", day);
        while (day < totalDays) {
            // go through each line
            int size = fishs.size();
            for (int i = 0; i < size; i++) {
                int newLifespan = fishs.get(i) - 1;
                if (newLifespan < 0) {
                    newLifespan =parentCycle;
                    fishs.add(childCycle);
                }
                fishs.set(i, newLifespan);
            }
            day++;
            System.out.printf("After %d days: %d\n", day, fishs.size());

        }
        return fishs.size();

    }
}
