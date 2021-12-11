package solutions.day10;

import util.ResourceReader;

import java.util.*;
import java.util.stream.Collectors;

final class Day10 {

    public static void main(String[] args) {
        List<List<String>> input = ResourceReader.asStringList("day10.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
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

    private static long part2(List<List<String>> input) {
        List<Long> results = new ArrayList<>();
        Deque<String> stack;
        for (List<String> strings : input) {
            stack = new ArrayDeque<>();
            boolean skip = false;
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
                            skip = true;
                            break inner;
                        }
                        break;
                    case "]":
                        if (stack.peek().equals("[")) {
                            stack.pop();
                        } else {
                            skip = true;
                            break inner;
                        }
                        break;
                    case "}":
                        if (stack.peek().equals("{")) {
                            stack.pop();
                        } else {
                            skip = true;
                            break inner;
                        }
                        break;
                    case ">":
                        if (stack.peek().equals("<")) {
                            stack.pop();
                        } else {
                            skip = true;
                            break inner;
                        }
                        break;
                }
            }
            if (!skip) {
                long result = 0;
                while (!stack.isEmpty()) {
                    String popped = stack.pop();
                    if (popped.equals("(")) {
                        result = result * 5 + 1;
                    } else if (popped.equals("[")) {
                        result = result * 5 + 2;
                    } else if (popped.equals("{")) {
                        result = result * 5 + 3;
                    } else if (popped.equals("<")) {
                        result = result * 5 + 4;
                    } else {
                        throw new RuntimeException("OFF");
                    }
                }
                results.add(result);
            }
        }
        return results.stream().sorted().collect(Collectors.toList()).get(results.size() / 2);
    }

    private static void register(Map<String, Integer> counts, String k) {
        counts.put(k, counts.getOrDefault(k, 0) + 1);
    }
}
