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
        for (Integer finish : dedup) {
            long current = 0;
            for (Integer start : input) {
                current += Math.abs(finish - start);
            }
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    private static long part2(List<Integer> input, List<Integer> dedup) {
        long min = Integer.MAX_VALUE;
        for (Integer finish : dedup) {
            long current = 0;
            for (Integer start : input) {
                for (int k = 1; k <= Math.abs(finish - start); k++) {
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
