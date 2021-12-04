package solutions.day03;

import util.ResourceReader;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Day03 {

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day03.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    private static long part1(List<String> input) {
        String gamma = gammaRate(input, (a, b) -> a > b ? "1" : "0");
        String epsilon = flipBits(gamma);
        return binaryToDecimal(gamma) * binaryToDecimal(epsilon);
    }

    private static long part2(List<String> input) {
        String oxy = dynamicGammaRate(input, e -> gammaRate(e, (a, b) -> a >= b ? "1" : "0"));
        String co2 = dynamicGammaRate(input, e -> gammaRate(e, (a, b) -> a < b ? "1" : "0"));
        return binaryToDecimal(oxy) * binaryToDecimal(co2);
    }

    private static String dynamicGammaRate(List<String> input, Function<List<String>, String> f) {
        for (int i = 0; input.size() != 1; i++) {
            input = filter(input, f.apply(input), i);
        }
        return input.get(0);
    }

    private static String gammaRate(List<String> binaries, BiFunction<Integer, Integer, String> f) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, binaries.get(0).length()).forEach(i -> {
            AtomicInteger bit1 = new AtomicInteger();
            binaries.forEach(b -> { if (b.charAt(i) == '1') bit1.getAndIncrement(); });
            sb.append(f.apply(bit1.get(), binaries.size() - bit1.get()));
        });
        return sb.toString();
    }

    private static List<String> filter(List<String> input, String gammaRate, int i) {
        return input.stream().filter(e -> gammaRate.charAt(i) == e.charAt(i))
                .collect(Collectors.toList());
    }

    private static String flipBits(String binary) {
        return binary.chars().mapToObj(e -> e == '1' ? "0" : "1")
                .collect(Collectors.joining());
    }

    private static long binaryToDecimal(String binary) {
        return Integer.parseInt(binary,2);
    }
}
