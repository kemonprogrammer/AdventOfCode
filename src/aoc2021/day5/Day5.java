package aoc2021.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {

    public static void main(String[] args) {

        // read each line as int array and put it in the list
        //   e.g.: [521, 35, 521, 235]
        List<int[]> coordinates = new ArrayList<>();
        String path = "src/aoc2021/day5/day5.txt";
        try (Scanner reader = new Scanner(new File(path))) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] splitLine = line.split("[ ,>-]+");
                int[] oneCoor = new int[splitLine.length];
                for (int i = 0; i < splitLine.length; i++) {
                    oneCoor[i] = Integer.valueOf(splitLine[i]);
                }
                coordinates.add(oneCoor);
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        int[][] grid = new int[1000][1000];
        int[][] grid2 = new int[1000][1000];
        int resultOne, resultTwo;
        resultOne = partOne(cloneList(coordinates), grid);
        resultTwo = partTwo(cloneList(coordinates), grid2);
        System.out.printf("resultOne: %s\n", resultOne);
        System.out.printf("resultTwo: %s", resultTwo);
    }

    public static int partOne(List<int[]> coordinates, int[][] grid) {
        // remove non-horizontal/vertical lines
        // and insert lines other lines in grid
        Iterator<int[]> iter = coordinates.iterator();
        while (iter.hasNext()) {
            int[] line = iter.next();
            int x1, y1, x2, y2;
            x1 = line[0];
            y1 = line[1];
            x2 = line[2];
            y2 = line[3];
            if (x1 != x2 && y1 != y2) {
                iter.remove();
                continue;
            }
            insertLine(grid, x1, y1, x2, y2);
        }

        return countLines(grid);
    }


    private static int partTwo(List<int[]> coordinates, int[][] grid) {
        // insert lines
        Iterator<int[]> iter = coordinates.iterator();
        while (iter.hasNext()) {
            int[] line = iter.next();
            int x1, y1, x2, y2;
            x1 = line[0];
            y1 = line[1];
            x2 = line[2];
            y2 = line[3];
            insertLine(grid, x1, y1, x2, y2);
        }
        return countLines(grid);
    }

    private static int countLines(int[][] grid) {
        int total = 0;
        for (int[] row : grid) {
            for (int elem : row) {
                total += (elem > 1) ? 1 : 0;
            }
        }
        return total;
    }

    public static void insertLine(int[][] grid, int x1, int y1, int x2, int y2) {
        int horizontalDistance = Math.abs(x2 - x1);
        int verticalDistance = Math.abs(y2 - y1);

        // go right and down (1)
        // go left and up (-1)
        int xDirection = (x2 - x1 >= 0) ? 1 : -1;
        int yDirection = (y2 - y1 >= 0) ? 1 : -1;

        // start with -1 because you need to increase the counters and
        // insert into the grid before the loop stops (when row=y2 and col=x2)
        int x = -1; // counter for columns
        int y = -1; // counter for rows
        int row, col;
        do {
            if (x < horizontalDistance) {
                x++;
            }
            if (y < verticalDistance) {
                y++;
            }

            row = y1 + yDirection * y;
            col = x1 + xDirection * x;
            grid[row][col]++;

            // stop when both counters have crossed the horizontal-/vertical distance
        } while (x < horizontalDistance || y < verticalDistance);
    }

    public static List<int[]> cloneList(List<int[]> list) {
        // only shallow copy, because you only remove the references
        // to the int arrays, not the elements in the arrays
        List<int[]> clonedList = new ArrayList<>();
        for (int[] obj : list) {
            clonedList.add(obj);
        }
        return clonedList;
    }
}
