package solutions.day05;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

final class LineSegment {

    private final Point a;
    private final Point b;

    LineSegment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    Set<Point> getPoints() {
        Set<Point> points = new HashSet<>();
        if (a.x == b.x) {
            int x = a.x;
            int minY = Math.min(a.y, b.y);
            int maxY = Math.max(a.y, b.y);
            for (int i = minY; i <= maxY; i++) {
                points.add(new Point(x, i));
            }
        } else {
            int y = a.y;
            int minX = Math.min(a.x, b.x);
            int maxX = Math.max(a.x, b.x);
            for (int i = minX; i <= maxX; i++) {
                points.add(new Point(i, y));
            }
        }
        return points;
    }

    @Override
    public String toString() {
        return "LineSegment{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
