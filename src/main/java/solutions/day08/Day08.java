package solutions.day08;

import util.ResourceReader;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final class Day08 {

    private static final int one = 2;
    private static final int four = 4;
    private static final int seven = 3;
    private static final int eight = 7;

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day08.txt");

        List<List<String>> inputValues = input.stream()
                .map(e -> Arrays.asList(e.split(" \\| ")[0].split(" ")))
                .collect(Collectors.toList());

        List<List<String>> outputValues = input.stream()
                .map(e -> Arrays.asList(e.split(" \\| ")[1].split(" ")))
                .collect(Collectors.toList());

        System.out.println(part1(outputValues));
        System.out.println(part2(inputValues, outputValues));
    }

    private static long part1(List<List<String>> outputValues) {
        return outputValues.stream().flatMap(Collection::stream)
                .filter(e -> e.length() == one || e.length() == four ||
                        e.length() == seven || e.length() == eight).count();
    }

    private static long part2(List<List<String>> inputValues, List<List<String>> outputValues) {
        List<Map<String, String>> ssd = inputValues.stream()
                .map(Day08::doSSDMapping).collect(Collectors.toList());
        final AtomicLong sum = new AtomicLong();
        IntStream.range(0, outputValues.size()).forEach(i ->
                sum.getAndAdd(decode(ssd.get(i), outputValues.get(i)))
        );
        return sum.longValue();
    }

    private static Map<String, String> doSSDMapping(List<String> inputValues) {
        Map<String, Set<String>> ssd = new HashMap<>();
        List<Set<String>> inputSetValues = inputValues.stream()
                .map(Day08::toSet).collect(Collectors.toList());
        inputSetValues.forEach(e -> {
            if (e.size() == one) {
                ssd.put("1", e);
            } else if (e.size() == four) {
                ssd.put("4", e);
            }
            else if (e.size() == seven) {
                ssd.put("7", e);
            }
            else if (e.size() == eight) {
                ssd.put("8", e);
            }
        });
        while (ssd.size() != 7) {
            inputSetValues.forEach(e -> {
                if (e.size() == 6) {
                    // 0 6 9
                    if (!e.containsAll(ssd.get("1"))) {
                        ssd.put("6", e);
                    } else if (e.containsAll(ssd.get("1")) && e.containsAll(ssd.get("4"))) {
                        ssd.put("9", e);
                    } else {
                        ssd.put("0", e);
                    }
                }
            });
        }
        while (ssd.size() != 10) {
            inputSetValues.forEach(e -> {
                if (e.size() == 5) {
                    // 2 3 5
                    if (e.containsAll(ssd.get("1"))) {
                        ssd.put("3", e);
                    } else if (!e.containsAll(ssd.get("1")) && ssd.get("9").containsAll(e)) {
                        ssd.put("5", e);
                    } else {
                        ssd.put("2", e);
                    }
                }
            });
        }
        return ssd.entrySet().stream()
                .collect(Collectors.toMap(p ->
                        sort(String.join("", p.getValue())), Map.Entry::getKey));
    }

    private static Set<String> toSet(String s) {
        return new HashSet<>(Arrays.asList(s.split("")));
    }

    private static long decode(Map<String, String> ssd, List<String> outputValues) {
        return Long.parseLong(outputValues.stream()
                .map(Day08::sort)
                .map(ssd::get)
                .collect(Collectors.joining()));
    }

    private static String sort(String s) {
        return Stream.of(s.split(""))
                .sorted()
                .collect(Collectors.joining());
    }
}
