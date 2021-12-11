package solutions.day11;

import util.ResourceReader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

final class Day11 {

    public static void main(String[] args) {
        List<List<Integer>> input = ResourceReader.asIntList("day11.txt");
        System.out.println(part1(input, 100));
        System.out.println(part2(input));
    }

    private static long part1(List<List<Integer>> input, int steps) {
        Map<Location, Energy> energyLevels = mapEnergyLevels(input);
        AtomicLong flashCounts = new AtomicLong();
        IntStream.range(0, steps).forEach(i -> step(energyLevels, flashCounts));
        return flashCounts.longValue();
    }

    private static long part2(List<List<Integer>> input) {
        Map<Location, Energy> energyLevels = mapEnergyLevels(input);
        AtomicLong flashCounts = new AtomicLong();
        int stepsCount = 0;
        while (!energyLevels.values().stream().allMatch(e -> e.getLevel() == 0)) {
            step(energyLevels, flashCounts);
            stepsCount++;
        }
        return stepsCount;
    }

    private static void step(Map<Location, Energy> energyLevels, AtomicLong flashCounts) {
        increaseLevels(energyLevels);
        while (overEnergy(energyLevels)) {
            flash(energyLevels, flashCounts);
            increaseAffectedLevels(energyLevels);
        }
    }

    private static boolean overEnergy(Map<Location, Energy> energyLevels) {
        return energyLevels.values().stream().anyMatch(e -> e.getLevel() > 9);
    }

    private static void flash(Map<Location, Energy> energyLevels, AtomicLong flashCounts) {
        energyLevels.forEach((k, v) -> {
            if (v.getLevel() > 9) {
                resetLevel(energyLevels, k);
                flashCounts.getAndIncrement();
            }
        });
    }

    private static void increaseAffectedLevels(Map<Location, Energy> energyLevels) {
        energyLevels.forEach((k, v) -> {
            if (!v.isFlashed() && v.getLevel() == 0) {
                k.neighbourhood().stream()
                        .filter(e -> energyLevels.get(e) != null)
                        .filter(e -> energyLevels.get(e).getLevel() != 0)
                        .forEach(e -> increaseLevel(energyLevels, e));
            }
        });
        setFlashed(energyLevels);
    }

    private static void increaseLevels(Map<Location, Energy> energyLevels) {
        energyLevels.keySet().forEach(k -> increaseLevel(energyLevels, k));
    }

    private static void increaseLevel(Map<Location, Energy> energyLevels, Location loc) {
        energyLevels.put(loc, new Energy(energyLevels.getOrDefault(loc, new Energy(0)).getLevel() + 1));
    }

    private static void resetLevel(Map<Location, Energy> energyLevels, Location loc) {
        energyLevels.put(loc, new Energy(0));
    }

    private static void setFlashed(Map<Location, Energy> energyLevels) {
        energyLevels.forEach((k, v) -> energyLevels.put(k, energyLevels.get(k).setFlashed()));
    }

    private static Map<Location, Energy> mapEnergyLevels(List<List<Integer>> input) {
        Map<Location, Energy> energyLevels = new LinkedHashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                energyLevels.put(Location.of(i, j), new Energy(input.get(i).get(j)));
            }
        }
        return energyLevels;
    }
}
