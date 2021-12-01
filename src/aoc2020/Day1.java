package aoc2020;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Day1 {

    public static int target = 2020;

    public static void main(String[] args) {
        String fileName = "src/aoc2020/day1.txt";
        List<Integer> numbers = new ArrayList<Integer>();

        // Fill Arraylist
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            while (sb.toString() != null) {
                sb.setLength(0);
                sb.append(reader.readLine());
                numbers.add(Integer.valueOf(sb.toString()));
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Integer> lowerHalf = new ArrayList<>();
        List<Integer> upperHalf = new ArrayList<>();

        // fill lower and upper half
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            int next = iterator.next();
            if (next > target / 2) {
                upperHalf.add(next);
            } else {
                lowerHalf.add(next);
            }
        }

        // search for product which equals the target
        Iterator<Integer> lowerIterator = lowerHalf.iterator();
        boolean summandsFound = false;
        int lower = 0, upper = 0;
        while (lowerIterator.hasNext()) {
            lower = lowerIterator.next().intValue();
            upper = target - lower;
            if (upperHalf.contains(upper)) {
                summandsFound = true;
                break;
            }
        }
        int product = lower * upper;
        System.out.printf("L:%d,U:%d,P:%d, B:%b", lower, upper, product, summandsFound);
    }
}
