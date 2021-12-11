package solutions.day11;

import util.ResourceReader;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

final class Day11 {

    public static void main(String[] args) {
        List<List<Integer>> input = ResourceReader.asIntList("day11.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    private static long part1(List<List<Integer>> input) {
        Map<Location, Energy> energyLevels = getEnergyLevels(input);
        AtomicInteger flashCount = new AtomicInteger();
        AtomicInteger stepsCount = new AtomicInteger();
        while (stepsCount.getAndIncrement() < 100) {
            step(energyLevels, flashCount);
        }
        return flashCount.longValue();
    }

    private static long part2(List<List<Integer>> input) {
        Map<Location, Energy> energyLevels = getEnergyLevels(input);
        AtomicInteger flashCount = new AtomicInteger();
        AtomicInteger stepsCount = new AtomicInteger();
        while (!energyLevels.values().stream().allMatch(e -> e.getLevel() == 0)) {
            step(energyLevels, flashCount);
            stepsCount.getAndIncrement();
        }
        return stepsCount.get();
    }

    private static void step(Map<Location, Energy> energyLevels, AtomicInteger flashCounts) {
        increaseEnergy(energyLevels);
        while (overEnergy(energyLevels)) {
            flash(energyLevels, flashCounts);
            increaseIlluminated(energyLevels);
        }
    }

    private static boolean overEnergy(Map<Location, Energy> energyLevels) {
        return energyLevels.values().stream().anyMatch(e -> e.getLevel() > 9);
    }

    private static void flash(Map<Location, Energy> energyLevels, AtomicInteger flashCount) {
        energyLevels.entrySet().stream()
                .filter(e -> e.getValue().getLevel() > 9)
                .forEach(e -> { resetEnergy(energyLevels, e.getKey());
                    flashCount.getAndIncrement();
                });
    }

    private static void increaseIlluminated(Map<Location, Energy> energyLevels) {
        energyLevels.entrySet().stream()
                .filter(e -> !e.getValue().discharged() && e.getValue().getLevel() == 0)
                .forEach(e -> { e.getKey().neighbourhood().stream()
                        .filter(energyLevels::containsKey)
                        .filter(l -> energyLevels.get(l).getLevel() != 0)
                        .forEach(l -> increaseEnergy(energyLevels, l));
                    discharge(energyLevels, e.getKey());
                });
    }

    private static void increaseEnergy(Map<Location, Energy> energyLevels) {
        energyLevels.keySet().forEach(k -> increaseEnergy(energyLevels, k));
    }

    private static void increaseEnergy(Map<Location, Energy> energyLevels, Location loc) {
        energyLevels.put(loc, new Energy(energyLevels.getOrDefault(loc, new Energy(0)).getLevel() + 1));
    }

    private static void resetEnergy(Map<Location, Energy> energyLevels, Location loc) {
        energyLevels.put(loc, new Energy(0));
    }

    private static void discharge(Map<Location, Energy> energyLevels, Location loc) {
        energyLevels.put(loc, energyLevels.get(loc).discharge());
    }

    private static Map<Location, Energy> getEnergyLevels(List<List<Integer>> input) {
        Map<Location, Energy> energyLevels = new LinkedHashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).size(); j++) {
                energyLevels.put(Location.of(i, j), new Energy(input.get(i).get(j)));
            }
        }
        return energyLevels;
    }
}
