package aoc2021.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day3 {
    /*
    Link: https://adventofcode.com/2021/day/3

    Notes:
    the epsilon rate doesn't have to be determined by
    going through the list again, but can be inferred from the gamma rate
    2^12-1 - gamma rate = epsilon rate

    110001010110 (12)
    Implementation:
    Going through
     */
    public static void main(String[] args) {
        int bitLength = 12;
        int[] bitCount = new int[bitLength];
        int lineCount = 0;

        try {
            String pathname = "src/aoc2021/day3/day3.txt";
            Scanner reader = new Scanner(new File(pathname));
            while (reader.hasNextLine()){
//                int line = reader.nextInt();
                String line = reader.nextLine();
                // count each 1 in the line
                for (int i = 0; i < bitLength; i++) {
                    int bit = Character.digit(line.charAt(i),16);
                    if (bit == 1){
                        bitCount[i]++;
                    }
                }
                lineCount++;

            }
            reader.close();
        } catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }

        // gamma rate
        int[] gammaRate = new int[bitLength];
        for (int i = 0; i < bitLength; i++) {
            gammaRate[i] = (bitCount[i]>=lineCount/2) ? 1: 0;
        }

        // gamma rate to decimal number
        int two = 0;
        int gammaRateDec = 0;
        for (int i = 0; i < bitLength; i++) {
            gammaRateDec += gammaRate[bitLength-i-1] * Math.pow(2,i);
        }

        // epsilon rate and consumption
        int epsilonRateDec = (int) (Math.pow(2,bitLength)-1-gammaRateDec);
        int consumption = gammaRateDec * epsilonRateDec;
        System.out.printf("gr:%d,er:%d,c:%d", gammaRateDec, epsilonRateDec, consumption);
    }
}
