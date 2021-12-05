package solutions.day05;

import util.ResourceReader;

import java.awt.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day05 {

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day05_test.txt");

        List<LineSegment> allLineSegments = input.stream().map(e -> {
            String[] splitLine = e.split(" -> ");
            String[] pair1 = splitLine[0].split(",");
            String[] pair2 = splitLine[1].split(",");
            Point pointA = new Point(Integer.parseInt(pair1[0]), Integer.parseInt(pair1[1]));
            Point pointB = new Point(Integer.parseInt(pair2[0]), Integer.parseInt(pair2[1]));
            return new LineSegment(pointA, pointB);
        }).collect(Collectors.toList());

        List<LineSegment> horVerLineSegments = allLineSegments.stream()
                .filter(e -> e.getA().x == e.getB().x || e.getA().y == e.getB().y)
                .collect(Collectors.toList());

        System.out.println(part1(horVerLineSegments));
        System.out.println(part2(allLineSegments));
    }

    private static long part1(List<LineSegment> lines) {
        return lines.stream()
                .flatMap(e -> e.getHorVerPoints().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1).count();
    }

    private static long part2(List<LineSegment> lines) {
        return lines.stream()
                .flatMap(e -> e.getAllPoints().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() > 1).count();
    }
}
