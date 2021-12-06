package solutions.day06;

import util.ResourceReader;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day06 {

    public static void main(String[] args) {
        List<Fish> fish = Arrays.stream(ResourceReader.asString("day06.txt")
                .get(0).split(",")).map(e -> new Fish(Integer.parseInt(e))).collect(Collectors.toList());

        System.out.println(simulateLanternfish(fish, 80));
    }

    private static long simulateLanternfish(List<Fish> fish, int days) {
        for (int i = 0; i < days; i++) {
            fish = fish.stream()
                    .map(Fish::tick)
                    .flatMap(Collection::stream)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
        return fish.size();
    }
}
