package solutions.day01;

import util.ResourceReader;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Day01 {

    public static void main(String[] args) {
        List<Integer> input = ResourceReader.asInt("day01.txt");
        System.out.println(part1(input));
        System.out.println(part1(part2(input)));
    }

    private static long part1(List<Integer> input) {
        return IntStream.range(0, input.size()-1)
                .filter(i -> input.get(i+1) > input.get(i))
                .count();
    }

    private static List<Integer> part2(List<Integer> input) {
        return IntStream.range(0, input.size()-2)
                .map(i -> input.get(i) + input.get(i+1) + input.get(i+2))
                .boxed()
                .collect(Collectors.toList());
    }
}
