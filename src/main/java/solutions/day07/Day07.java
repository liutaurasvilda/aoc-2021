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
        List<Integer> startValues = ResourceReader.asLineInt("day07.txt");

        IntSummaryStatistics stats = startValues.stream().mapToInt(e -> e).summaryStatistics();
        List<Integer> finishValues = IntStream.rangeClosed(stats.getMin(), stats.getMax())
                .boxed().collect(Collectors.toList());

        System.out.println(part1(startValues, finishValues));
        System.out.println(part2(startValues, finishValues));
    }

    private static long part1(List<Integer> startValues, List<Integer> finishValues) {
        long min = Integer.MAX_VALUE;
        for (Integer finish : finishValues) {
            long current = 0;
            for (Integer start : startValues) {
                current += Math.abs(finish - start);
            }
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    private static long part2(List<Integer> startValues, List<Integer> finishValues) {
        long min = Integer.MAX_VALUE;
        for (Integer finish : finishValues) {
            long current = 0;
            for (Integer start : startValues) {
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
