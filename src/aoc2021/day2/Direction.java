package aoc2021.day2;


public enum Direction {
    forward("forward"), up("up"), down("down");

    private String asString;
    private Direction(String string){
        asString = string;
    }

    @Override
    public String toString() {
        return asString;
    }
}