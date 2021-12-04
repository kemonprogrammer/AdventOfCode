package aoc2021.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {

    public static String[] commands = new String[]{"forward","up","down"};

    public static void main(String[] args) {

        // Task one
        int radius = 0, depth = 0, product;
        ArrayList<Integer> values = new ArrayList<>();
        values.add(radius);
        values.add(depth);

        // Task two
        int aim = 0;
        ArrayList<Integer> valuesTwo = new ArrayList<>();
        valuesTwo.add(radius);
        valuesTwo.add(depth);
        valuesTwo.add(aim);

        try {
            // Setup reader
            String pathname = "src/aoc2021/day2/day2.txt";
            File input = new File(pathname);
            Scanner reader = new Scanner(input);
            StringBuilder line = new StringBuilder();

            // Read the file
            while (reader.hasNextLine()){
                // Read 1 line
                line.setLength(0);
                line.append(reader.nextLine());
                PilotData commandLine = new PilotData(line.toString());

                // Fill Arraylists of task one and two
                String direction = commandLine.getCommand();
                int value = commandLine.getValue();
                taskOne(values, direction, value);
                taskTwo(valuesTwo, direction, value);
            }
        } catch (FileNotFoundException FNFE){
            System.out.println(FNFE.getMessage());
        }

        // calculate product and output it of task one
        radius = values.get(0);
        depth = values.get(1);
        product = radius * depth;
        System.out.printf("Task 1: horizontal position(%d)*Depth(%d)=product(%d)\n",depth,radius,product);

        // calculate product and output it of task two
        radius = valuesTwo.get(0);
        depth = valuesTwo.get(1);
        product = radius * depth;
        System.out.printf("Task 2: horizontal position(%d)*Depth(%d)=product(%d)\n",depth,radius,product);


    }


    public static void taskOne(ArrayList<Integer> values, String direction, int value){
        // Change depth or radius based on direction
        if (direction.equals(commands[0])){
            // forward
            int newRadius = values.get(0) + value;
            values.set(0,newRadius);
        } else if (direction.equals(commands[1])){
            // up
            int newDepth = values.get(1) - value;
            values.set(1, newDepth);
        } else if (direction.equals(commands[2])){
            // down
            int newDepth = values.get(1) + value;
            values.set(1, newDepth);
        }
    }

    public static void taskTwo(ArrayList<Integer> values, String direction, int value){

        // Change depth or radius based on direction
        if (direction.equals(commands[0])){
            // forward
            int newRadius = values.get(0) + value;
            int newDepth = values.get(1) + values.get(2) * value;
            values.set(0,newRadius);
            values.set(1,newDepth);
        } else if (direction.equals(commands[1])){
            // up
            int newAim = values.get(2) - value;
            values.set(2, newAim);
        } else if (direction.equals(commands[2])){
            // down
            int newAim = values.get(2) + value;
            values.set(2, newAim);
        }
    }

}

