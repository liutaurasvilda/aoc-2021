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
        System.out.println(part1(input, 100));
    }

    private static long part1(List<List<Integer>> input, int steps) {
        Map<Location, Integer> energyLevels = mapEnergyLevels(input);
        AtomicLong flashCounts = new AtomicLong();
        IntStream.range(0, steps).forEach(i -> step(energyLevels, flashCounts));
        return flashCounts.longValue();
    }

    private static void step(Map<Location, Integer> energyLevels, AtomicLong flashCounts) {
        increaseLevels(energyLevels);
        while (fullEnergy(energyLevels)) {
            flash(energyLevels, flashCounts);
            increaseAffectedLevels(energyLevels);
        }
    }

    private static void increaseAffectedLevels(Map<Location, Integer> energyLevels) {
        energyLevels.forEach((k, v) -> {
            if (v == 0) {
                k.neighbourhood().stream()
                        .filter(e -> energyLevels.get(e) != null)
                        .filter(e -> energyLevels.get(e) != 0)
                        .forEach(e -> increaseLevel(energyLevels, e));
            }
        });
    }

    private static void flash(Map<Location, Integer> energyLevels, AtomicLong flashCounts) {
        energyLevels.forEach((k, v) -> {
            if (v >= 9) {
                resetLevel(energyLevels, k);
                flashCounts.getAndIncrement();
            }
        });
    }

    private static boolean fullEnergy(Map<Location, Integer> energyLevels) {
        return energyLevels.values().stream().anyMatch(e -> e == 9);
    }

    private static void increaseLevels(Map<Location, Integer> energyLevels) {
        energyLevels.keySet().forEach(k -> increaseLevel(energyLevels, k));
    }

    private static void increaseLevel(Map<Location, Integer> energy, Location loc) {
        energy.put(loc, energy.getOrDefault(loc, 0) + 1);
    }

    private static void resetLevel(Map<Location, Integer> energy, Location loc) {
        energy.put(loc, 0);
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
