package solutions.day10;

import util.ResourceReader;

import java.util.*;

final class Day10 {

    public static void main(String[] args) {
        List<List<String>> input = ResourceReader.asStringList("day10.txt");
        System.out.println(part1(input));
    }

    private static long part1(List<List<String>> input) {
        Map<String, Integer> counts = new HashMap<>();
        Deque<String> stack;
        for (List<String> strings : input) {
            stack = new ArrayDeque<>();
            inner:
            for (String brace : strings) {
                switch (brace) {
                    case "(":
                    case "[":
                    case "{":
                    case "<":
                        stack.push(brace);
                        break;
                    case ")":
                        if (stack.peek().equals("(")) {
                            stack.pop();
                        } else {
                            register(counts, ")");
                            break inner;
                        }
                        break;
                    case "]":
                        if (stack.peek().equals("[")) {
                            stack.pop();
                        } else {
                            register(counts, "]");
                            break inner;
                        }
                        break;
                    case "}":
                        if (stack.peek().equals("{")) {
                            stack.pop();
                        } else {
                            register(counts, "}");
                            break inner;
                        }
                        break;
                    case ">":
                        if (stack.peek().equals("<")) {
                            stack.pop();
                        } else {
                            register(counts, ">");
                            break inner;
                        }
                        break;
                }
            }
        }
        long sum = 0;
        for (Map.Entry<String, Integer> e : counts.entrySet()) {
            switch (e.getKey()) {
                case ")":
                    sum += e.getValue() * 3L;
                    break;
                case "]":
                    sum += e.getValue() * 57L;
                    break;
                case "}":
                    sum += e.getValue() * 1197L;
                    break;
                case ">":
                    sum += e.getValue() * 25137L;
                    break;
            }
        }
        return sum;
    }

    private static long part2() {
        return 0;
    }

    private static void register(Map<String, Integer> counts, String k) {
        if (counts.containsKey(k)) {
            counts.put(k, counts.get(k) + 1);
        } else {
            counts.put(k, 1);
        }
    }
}
