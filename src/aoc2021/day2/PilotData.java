package aoc2021.day2;

import java.util.ArrayList;
import java.util.List;

public class PilotData {
    private List<String> data;

    public PilotData(String input) {
        data = new ArrayList<>();
        for (String word : input.split("\\s+")) {
            data.add(word);
        }
    }

    public String getCommand(){
        return data.get(0);
    }

    public int getValue(){
        return Integer.parseInt(data.get(1));
    }
}
