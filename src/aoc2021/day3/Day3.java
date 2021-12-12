package aoc2021.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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

    public final static int bitLength = 12;
    public static int lineCount = 0;

    public static void main(String[] args) {
        int[] bitOneCount = new int[bitLength];
        List<String> bitLines = new ArrayList<>();

        try {
            String pathname = "src/aoc2021/day3/day3.txt";
            Scanner reader = new Scanner(new File(pathname));

            while (reader.hasNextLine()) {
                String line = reader.nextLine();

                // count each 1 in the line
                for (int i = 0; i < bitLength; i++) {
                    int bit = line.charAt(i) - '0';
                    if (bit == 1) {
                        bitOneCount[i]++;
                    }
                }
                bitLines.add(line);
                lineCount++;

            }
            reader.close();
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        // determine more bits
        System.out.println("lineCount: " + lineCount);
        System.out.println("bitOneCount: " + Arrays.toString(bitOneCount));
        int[] majorityBits = majorityBits(bitOneCount);


        partOne(majorityBits);
        partTwo(bitLines, bitOneCount);

    }

    public static void partOne(int[] majorityBits) {
        // gamma rate
        int[] gammaRate = majorityBits.clone();

        // gamma rate binary to decimal number
        int gammaRateDec = binaryToDecimal(gammaRate);

        // epsilon rate and consumption
        int epsilonRateDec = (int) (Math.pow(2, bitLength) - 1 - gammaRateDec);
        int consumption = gammaRateDec * epsilonRateDec;
        System.out.printf("gr:%d,er:%d,c:%d\n", gammaRateDec, epsilonRateDec, consumption);
    }

    public static void partTwo(List<String> bitLines, int[] bitOneCount) {

        // fill O2 and CO2
        List<String> O2 = new ArrayList<>();
        List<String> CO2 = new ArrayList<>();
        for (String line : bitLines) {
            O2.add(line);
            CO2.add(line);
        }

        System.out.println("with o2:");
        O2 = filterNotFitting(O2, true);
        System.out.println("with co2:");
        CO2 = filterNotFitting(CO2, false);


        int o2Rating = binaryToDecimal(charArrayToBinaryArray(O2.get(0).toCharArray()));
        int co2Rating = binaryToDecimal(charArrayToBinaryArray(CO2.get(0).toCharArray()));
        int rating = o2Rating * co2Rating;
        System.out.println("Part Two");
//        System.out.println("majorityBits: " + Arrays.toString(majorityBits));
        System.out.printf("O2: %s\n" +
                        "CO2: %s\n" +
                        "o2: %d\n" +
                        "co2: %d\n" +
                        "rating: %d\n",
                O2, CO2, o2Rating, co2Rating, rating);
    }


    public static List<String> filterNotFitting(List<String> air, boolean isO2) {
        // go through each bit
        for (int i = 0; i < bitLength; i++) {

            int length = air.size();
            int bitOneCount = countBitOneAt(air, i);
            int bitZeroCount = length - bitOneCount;

            int bitToKeep;
            if (bitOneCount >= bitZeroCount) {
                bitToKeep = 1;
            } else {
                bitToKeep = 0;
            }

            if (!isO2) {
                if (bitOneCount >= bitZeroCount) {
                    bitToKeep = 0;
                } else {
                    bitToKeep = 1;
                }
            }

//            Debug
//            System.out.printf("count1s: %-4d, count0s: %-4d keep: %d\n", bitOneCount, bitZeroCount, bitToKeep);

            // go through all lines
            Iterator<String> airIterator = air.iterator();
            while (airIterator.hasNext()) {
                if (air.size() == 1) {
                    break;
                }
                String line = airIterator.next();
                int currentBit = line.charAt(i) - '0';

                if (currentBit != bitToKeep) {
                    airIterator.remove();
                }
            }
            if (air.size() == 1) {
                break;
            }
        }

        return air;
    }

    // Helper methods
    public static int countBitOneAt(List<String> bits, int pos) {
        int count = 0;
        for (String line : bits) {
            if (line.charAt(pos) - '0' == 1) {
                count++;
            }
        }
        return count;

    }

    public static int binaryToDecimal(int[] binary) {
        int decimal = 0;
        for (int i = 0; i < bitLength; i++) {
            decimal += binary[bitLength - i - 1] * Math.pow(2, i);
        }
        return decimal;
    }

    public static int[] charArrayToBinaryArray(char[] charArray) {
        int[] intArray = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            intArray[i] = charArray[i] - '0';
        }
        return intArray;
    }

    public static int[] majorityBits(int[] bitCount) {
        int[] majorityBits = new int[bitCount.length];
        for (int i = 0; i < bitLength; i++) {
            majorityBits[i] = (bitCount[i] >= lineCount / 2) ? 1 : 0;
        }
        return majorityBits;
    }

}
