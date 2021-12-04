package aoc2021.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {
    public static void main(String[] args) {

        String fileName = "src/aoc2021/day1/day1.in";
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try {
            File input = new File(fileName);
            Scanner reader = new Scanner(input);
            while (reader.hasNextInt()) {
                numbers.add(reader.nextInt());
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        int result1 = partOne(numbers);
        int result2 = partTwo(numbers);
        System.out.printf("1. Number of increases: %d\n2. Number of 3 line increases: %d\n", result1, result2);
    }

    /**
     * If the next depth is greater than the
     * previous one, it should be counted
     *
     * @param numbers Numbers to read from
     * @return counter
     */
    public static int partOne(ArrayList<Integer> numbers) {
        Integer increases = 0;
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i + 1) > numbers.get(i)) {
                increases++;
            }
        }
        return increases;
    }

    /**
     * If the sum of 3 numbers is smaller than the sum of the 3 numbers
     * - one number after the previous one - then it should be counted
     *
     * @param numbers Numbers to read from
     * @return counted times
     */
    public static int partTwo(ArrayList<Integer> numbers) {
        Integer increases = 0;
        for (int i = 0; i < numbers.size() - 3; i++) {
            // only i+3 because if b+c+d > a+b+c , you can cancel b and c
            increases += (numbers.get(i + 3) > numbers.get(i) ? 1 : 0);
        }
        return increases;
    }
}
