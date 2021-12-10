package solutions.day10;

import util.ResourceReader;

import java.util.*;

final class Day10 {

    public static void main(String[] args) {
        List<List<String>> input = ResourceReader.asStringList("day10_test.txt");

        System.out.println(part1(input));
    }

    private static long part1(List<List<String>> input) {
        Deque<String> stack = new ArrayDeque<>();
        Map<String, Integer> counts = new HashMap<>();

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
            if (e.getKey().equals(")")) {
                sum += e.getValue() * 3L;
            } else if (e.getKey().equals("]")) {
                sum += e.getValue() * 57L;
            } else if (e.getKey().equals("}")) {
                sum += e.getValue() * 1197L;
            } else if (e.getKey().equals(">")) {
                sum += e.getValue() * 25137L;
            }
        }
        return sum;
    }

    private static void register(Map<String, Integer> counts, String k) {
        if (counts.containsKey(k)) {
            counts.put(k, counts.get(k) + 1);
        } else {
            counts.put(k, 1);
        }
    }
}
