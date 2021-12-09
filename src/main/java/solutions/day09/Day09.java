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
        System.out.println(part2(heights));
    }

    private static long part1(List<List<Integer>> heights) {
        Map<Location, Integer> heightMap = buildHeightMap(heights);
        Map<Location, Integer> lowPoints = getLowPoints(heightMap);
        return lowPoints.values().stream().mapToInt(integer -> integer + 1).sum();
    }

    private static long part2(List<List<Integer>> heights) {
        Map<Location, Integer> heightMap = buildHeightMap(heights);
        Map<Location, Integer> lowPoints = getLowPoints(heightMap);
        List<Integer> basins = lowPoints.keySet().stream()
                .map(lowPoint -> walk(lowPoint, heightMap, new HashSet<>()))
                .sorted().collect(Collectors.toList());
        return basins.subList(basins.size()-3, basins.size()).stream().reduce(1, (a, b) -> a * b);
    }

    private static int walk(Location current, Map<Location, Integer> heightmap, Set<Location> visited) {
        visited.add(current);
        current.neighbourhood().forEach(neighbour -> {
            if (heightmap.get(neighbour) == null || heightmap.get(neighbour) == 9 || visited.contains(neighbour)) {
                return;
            }
            if (heightmap.get(current) + 1 == heightmap.get(neighbour)) {
                walk(neighbour, heightmap, visited);
            }
        });
        return visited.size();
    }

    private static Map<Location, Integer> getLowPoints(Map<Location, Integer> heightMap) {
        return heightMap.entrySet()
                .stream()
                .filter(e -> e.getKey().neighbourhood().stream()
                        .map(heightMap::get)
                        .filter(Objects::nonNull)
                        .allMatch(n -> e.getValue() < n))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
