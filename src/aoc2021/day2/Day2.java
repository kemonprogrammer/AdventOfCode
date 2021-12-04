package aoc2021.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Enum
import static aoc2021.day2.Direction.*;


public class Day2 {

    public static void main(String[] args) {

        // Task one
        int radius = 0, depth = 0, product;
        int[] values = new int[2];
        values[0] = radius;
        values[1] = depth;

        // Task two
        int aim = 0;
        int[] valuesTwo = new int[3];
        valuesTwo[0] = radius;
        valuesTwo[1] = depth;
        valuesTwo[2] = aim;

        try {
            // Setup reader
            String pathname = "src/aoc2021/day2/day2.txt";
            Scanner reader = new Scanner(new File(pathname));
            StringBuilder line = new StringBuilder();

            // Read the file
            while (reader.hasNextLine()) {

                // Read 1 line
                line.setLength(0);
                line.append(reader.nextLine());
                String[] lineArgs = line.toString().split("\\s+");

                // Fill Arraylists of both tasks with the correct values
                String direction = lineArgs[0];
                int value = Integer.valueOf(lineArgs[1]);
                taskOne(values, direction, value);
                taskTwo(valuesTwo, direction, value);
            }
            reader.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        // calculate product of task one
        radius = values[0];
        depth = values[1];
        product = radius * depth;
        int taskNumber = 1;
        String format = "Task %d: %-7d(Horizontal position) * %-5d(Depth) = %-11d(product)\n";
        System.out.printf(format, taskNumber, depth, radius, product);

        // calculate product of task two
        radius = valuesTwo[0];
        depth = valuesTwo[1];
        product = radius * depth;
        taskNumber = 2;
        System.out.printf(format, taskNumber, depth, radius, product);
    }


    public static void taskOne(int[] values, String direction, int value) {
        // Change depth or radius based on direction
        if (direction.equals(forward.toString())) {
            // forward
            int newRadius = values[0] + value;
            values[0] = newRadius;
        } else if (direction.equals(up.toString())) {
            // up
            int newDepth = values[1] - value;
            values[1] = newDepth;
        } else if (direction.equals(down.toString())) {
            // down
            int newDepth = values[1] + value;
            values[1] = newDepth;
        }
    }

    public static void taskTwo(int[] values, String direction, int value) {

        // Change depth or radius based on direction
        if (direction.equals(forward.toString())) {
            // forward
            int newRadius = values[0] + value;
            int newDepth = values[1] + values[2] * value;
            values[0] = newRadius;
            values[1] = newDepth;
        } else if (direction.equals(up.toString())) {
            // up
            int newAim = values[2] - value;
            values[2] = newAim;
        } else if (direction.equals(down.toString())) {
            // down
            int newAim = values[2] + value;
            values[2] = newAim;
        }
    }

}

