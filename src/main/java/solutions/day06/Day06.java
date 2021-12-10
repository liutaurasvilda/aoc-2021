package solutions.day06;

import util.ResourceReader;

import java.util.*;
import java.util.stream.Collectors;

final class Day06 {

    public static void main(String[] args) {
        List<Fish> fish = ResourceReader.asIntLine("day06.txt").stream()
                .map(Fish::new).collect(Collectors.toList());

        System.out.println(part1(new ArrayList<>(fish), 80));
        System.out.println(part2(new ArrayList<>(fish), 256));
    }

    private static long part1(List<Fish> fish, int days) {
        for (int i = 0; i < days; i++) {
            fish = fish.stream()
                    .map(Fish::tick)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }
        return fish.size();
    }

    private static long part2(List<Fish> fish, int days) {
        Map<Integer, Long> prevCounts = new HashMap<>();
        Map<Integer, Long> currCounts = new HashMap<>();
        for (Fish f : fish) {
            updateCounts(prevCounts, f.days(), 1L);
        }
        for (int i = 0; i < days; i++) {
            currCounts = new HashMap<>();
            for (Map.Entry<Integer, Long> e : prevCounts.entrySet()) {
                if (e.getKey() == 0) {
                    updateCounts(currCounts, 6, e.getValue());
                    updateCounts(currCounts, 8, e.getValue());
                } else {
                    updateCounts(currCounts, e.getKey()-1, e.getValue());
                }
            }
            prevCounts = currCounts;
        }
        return currCounts.values().stream().mapToLong(Long::valueOf).sum();
    }

    private static void updateCounts(Map<Integer, Long> counts, int k, long v) {
        if (counts.containsKey(k)) {
            counts.put(k, counts.get(k) + v);
        } else {
            counts.put(k, v);
        }
    }
}
