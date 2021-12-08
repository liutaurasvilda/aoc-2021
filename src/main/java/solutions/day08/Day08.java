package solutions.day08;

import util.ResourceReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Day08 {

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day08.txt");
        List<List<String>> outputValues = input.stream()
                .map(e -> Arrays.asList(e.split(" \\| ")[1].split(" ")))
                .collect(Collectors.toList());

        System.out.println(part1(outputValues));
    }

    private static long part1(List<List<String>> input) {
        int one = 2; int four = 4; int seven = 3; int eight = 7;
        return input.stream().flatMap(Collection::stream)
                .filter(e -> e.length() == one || e.length() == four ||
                        e.length() == seven || e.length() == eight).count();
    }
}
