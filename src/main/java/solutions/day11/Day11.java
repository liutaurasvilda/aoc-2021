package solutions.day11;

import util.ResourceReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

final class Day11 {

    public static void main(String[] args) {
        List<List<Integer>> input = ResourceReader.asIntList("day11_test.txt");
        System.out.println(part1(input, 1));
    }

    private static long part1(List<List<Integer>> input, int steps) {
        Map<Location, Integer> energyLevels = mapEnergyLevels(input);
        AtomicLong flashes = new AtomicLong();
        IntStream.range(0, steps).forEach(i -> step(energyLevels, flashes));
        return flashes.longValue();
    }

    private static void step(Map<Location, Integer> energyLevels, AtomicLong flashes) {
        increaseEnergyLevels(energyLevels);
    }

    private static void increaseEnergyLevels(Map<Location, Integer> energyLevels) {
        energyLevels.keySet().forEach(k -> increase(energyLevels, k));
    }

    private static void increase(Map<Location, Integer> energy, Location loc) {
        energy.put(loc, energy.getOrDefault(loc, 0) + 1);
    }

    private static Map<Location, Integer> mapEnergyLevels(List<List<Integer>> input) {
        Map<Location, Integer> energyLevels = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                energyLevels.put(Location.of(i, j), input.get(i).get(j));
            }
        }
        return energyLevels;
    }
}
