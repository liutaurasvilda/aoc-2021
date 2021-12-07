package solutions.day07;

import util.ResourceReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day07 {

    public static void main(String[] args) {
        List<Integer> input = ResourceReader.asLineInt("day07.txt");
        List<Integer> dedup = new ArrayList<>(new HashSet<>(input));

        IntSummaryStatistics stats = dedup.stream().mapToInt(e -> e).summaryStatistics();
        List<Integer> enrichedDedup = IntStream.rangeClosed(stats.getMin(), stats.getMax())
                .boxed().collect(Collectors.toList());

        System.out.println(part1(input, dedup));
        System.out.println(part2(input, enrichedDedup));
    }

    private static long part1(List<Integer> input, List<Integer> dedup) {
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < dedup.size(); i++) {
            long current = 0;
            for (int j = 0; j < input.size(); j++) {
                    current += Math.abs(dedup.get(i) - input.get(j));
            }
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    private static long part2(List<Integer> input, List<Integer> dedup) {
        long min = Integer.MAX_VALUE;
        for (int i = 0; i < dedup.size(); i++) {
            long current = 0;
            for (int j = 0; j < input.size(); j++) {
                for (int k = 1; k <= Math.abs(dedup.get(i) - input.get(j)); k++) {
                    current += k;
                }
            }
            if (current < min) {
                min = current;
            }
        }
        return min;
    }
}
