package solutions.day09;

import util.ResourceReader;

import java.util.*;
import java.util.stream.Collectors;

public class Day09 {

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day09.txt");

        List<List<Integer>> heights = input.stream().map(e -> Arrays.stream(e.split(""))
                .map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());

        System.out.println(part1(heights));
    }

    private static long part1(List<List<Integer>> heights) {
        Map<Location, Integer> heightmap = buildHeightMap(heights);
        List<Integer> smallest = new ArrayList<>();
        for (Map.Entry<Location, Integer> current : heightmap.entrySet()) {
            if (current.getKey().neighborhood()
                    .map(heightmap::get)
                    .filter(Objects::nonNull)
                    .allMatch(neighbour -> current.getValue() < neighbour)) {
                smallest.add(current.getValue());
            }
        }
        return smallest.stream().mapToInt(e -> e + 1).sum();
    }

    private static long part2() {
        return 0;
    }

    private static Map<Location, Integer> buildHeightMap(List<List<Integer>> heights) {
        Map<Location, Integer> heightmap = new HashMap<>();
        for (int i = 0; i < heights.size(); i++) {
            for (int j = 0; j < heights.get(i).size(); j++) {
                heightmap.put(Location.of(i, j), heights.get(i).get(j));
            }
        }
        return heightmap;
    }
}
