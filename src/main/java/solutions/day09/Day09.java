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
        Map<Location, Integer> basins = new HashMap<>();

        for (Map.Entry<Location, Integer> lowPoint : lowPoints.entrySet()) {
            walk(lowPoint.getKey(), lowPoint.getValue(), lowPoint.getKey().neighborhood(), basins, heightMap, new HashSet<>());
        }

        List<Integer> topBasins = new ArrayList<>(basins.values()).stream().sorted().collect(Collectors.toList());
        int topBasinsSize = topBasins.size();
        return (long)topBasins.get(topBasinsSize-1) * topBasins.get(topBasinsSize-2) * topBasins.get(topBasinsSize-3);
    }

    private static void walk(Location lowPointLoc, int num, List<Location> neighbours,
                             Map<Location, Integer> basins, Map<Location, Integer> heightMap, Set<Location> visited) {
        incrementBasin(lowPointLoc, basins);
        for (Location neighbour : neighbours) {
            if (heightMap.get(neighbour) == null || heightMap.get(neighbour) == 9 || visited.contains(neighbour)) {
                continue;
            }
            if (num+1 == heightMap.get(neighbour)) {
                visited.add(neighbour);
                walk(lowPointLoc, heightMap.get(neighbour), neighbour.neighborhood(), basins, heightMap, visited);
            }
        }
    }

    private static void incrementBasin(Location lowPointLoc, Map<Location, Integer> basins) {
        if (basins.containsKey(lowPointLoc)) {
            basins.put(lowPointLoc, basins.get(lowPointLoc) + 1);
        } else {
            basins.put(lowPointLoc, 1);
        }
    }

    private static Map<Location, Integer> getLowPoints(Map<Location, Integer> heightMap) {
        Map<Location, Integer> lowPoints = new HashMap<>();
        for (Map.Entry<Location, Integer> current : heightMap.entrySet()) {
            if (current.getKey().neighborhood().stream()
                    .map(heightMap::get)
                    .filter(Objects::nonNull)
                    .allMatch(neighbour -> current.getValue() < neighbour)) {
                lowPoints.put(current.getKey(), current.getValue());
            }
        }
        return lowPoints;
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
