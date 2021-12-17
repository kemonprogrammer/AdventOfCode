package aoc2021.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day4 {
    public static void main(String[] args) {

        List<String> input = new ArrayList<>();
        try {
            String pathname = "src/aoc2021/day4/day4.txt";
            Scanner reader = new Scanner(new File(pathname));
            while (reader.hasNextLine()) {
                input.add(reader.nextLine().trim());
            }
            reader.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        // converting input into numbers and boards

        // First line are the numbers
        Integer[] numbers = {};

        /* boards data structure:

         List<List<Map<Integer,Boolean>>>
          |    |    |    +-> one field
          |    |    +-> one row
          |    +-> one board
          +-> all boards
        */
        List<List<Map<Integer, Boolean>>> boards = new ArrayList<>();
        List<Map<Integer, Boolean>> board = new ArrayList<>();
        int boardStart = 2;
        int linesPerBoard = 6;
        Iterator<String> iter = input.iterator();
        int i = 0;
        while (iter.hasNext()) {
            String line = iter.next();
            if (i == 0) {
                numbers = stringToIntegerArray(line, ",");
                i++;
                continue;
            }
            if (i == 1) {
                i++;
                continue;
            }

            int boardLine = (i - boardStart) % linesPerBoard;

            if (boardLine == 0) {
                board = new ArrayList<>();
            }
            if (boardLine <= 4) {
                Map<Integer, Boolean> row = new TreeMap<>();
                Integer[] rowArr = stringToIntegerArray(line, "[ ]+");
                for (Integer num : rowArr) {
                    row.put(num, false);
                }
                board.add(row);
            }
            if (boardLine == 4) {
                boards.add(board);
            }
            i++;
        }
//        System.out.println(Arrays.toString(numbers));
        System.out.println(Arrays.deepToString(boards.toArray()));

        int finalScore;
        finalScore = partOne(numbers, boards);
        System.out.printf("Final score: %d\n", finalScore);
    }

    public static int partOne(Integer[] numbers, List<List<Map<Integer, Boolean>>> boards) {


        return 0;
    }

    public static enum Direction{
        LEFT,
        RIGHT,
    }

    public static boolean isDirectionFilled(List<Map<Integer, Boolean>> board, int startRow, int startCol, Direction direction) {
//        // row 1
//        Map<Integer,Boolean> row = board.get(0);
//        Set<Map.Entry<Integer, Boolean>> rowSet = row.entrySet();
//        for (Map.Entry<Integer, Boolean> entry: rowSet) {
//            if (!entry.getValue()){
//                return false;
//            }
//        }
//        // col 1
//        Set<Map.Entry<Integer, Boolean>> colSet =
//        for (Map<Integer, Boolean> rowMap :
//                board) {
//
//        }

        return true;
    }

    public static boolean isRowFilled(List<Map<Integer, Boolean>> board, int row) {
//        isDirectionFilled(board, 0, 0, Direction.LEFT);
        Map<Integer,Boolean> rowMap = board.get(row);
        Set<Map.Entry<Integer, Boolean>> rowSet = rowMap.entrySet();
        for (Map.Entry<Integer, Boolean> entry: rowSet) {
            if (!entry.getValue()){
                return false;
            }
        }
        return true;
    }

    public static boolean isColFilled(List<Map<Integer, Boolean>> board, int col){
        // col 1
        Map<Integer, Boolean> colList = new HashMap<>();
        for (Map<Integer, Boolean> row :
                board) {
//            colList.add()
            Set<Map.Entry<Integer, Boolean>> rowSet = row.entrySet();
//            rowSet.
        }
        return true;
    }

    private static Integer[][] stringArrayToIntegerArray(String[] stringBoard) {
        Integer[][] board = new Integer[5][];
        for (int i = 0; i < 5; i++) {
            board[i] = stringToIntegerArray(stringBoard[i], "[ ]+");
        }
        return board;
    }

    // helper methods
    public static Integer[] stringToIntegerArray(String string, String splitRegex) {
        String[] stringArr = string.split(splitRegex);
        Integer[] integerArr = new Integer[stringArr.length];
        for (int i = 0; i < stringArr.length; i++) {
            integerArr[i] = Integer.valueOf(stringArr[i]);
        }
        return integerArr;
    }


}
