package solutions.day05;

import util.ResourceReader;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day05 {

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day05_test.txt");

        List<LineSegment> lines = input.stream().map(e -> {
            String[] splitLine = e.split(" -> ");
            String[] pair1 = splitLine[0].split(",");
            String[] pair2 = splitLine[1].split(",");
            Point pointA = new Point(Integer.parseInt(pair1[0]), Integer.parseInt(pair1[1]));
            Point pointB = new Point(Integer.parseInt(pair2[0]), Integer.parseInt(pair2[1]));
            return new LineSegment(pointA, pointB);
        }).collect(Collectors.toList());

        List<LineSegment> horizontalVertical = lines.stream()
                .filter(e -> e.getA().x == e.getB().x || e.getA().y == e.getB().y)
                .collect(Collectors.toList());

        System.out.println(part1(horizontalVertical));
    }

    private static long part1(List<LineSegment> lines) {
        List<Set<Point>> points = lines.stream()
                .map(LineSegment::getPoints)
                .collect(Collectors.toList());

        int totalSize = points.stream().mapToInt(Set::size).sum();

        Set<Point> union = new HashSet<>();
        IntStream.range(0, points.size()).forEach(i -> union.addAll(points.get(i)));

        return totalSize - union.size();
    }
}
