package solutions.day05;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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

    List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        if (a.x == b.x) {
            int x = a.x;
            int minY = Math.min(a.y, b.y);
            int maxY = Math.max(a.y, b.y);
            for (int i = minY; i <= maxY; i++) {
                points.add(new Point(x, i));
            }
        } else if (a.y == b.y) {
            int y = a.y;
            int minX = Math.min(a.x, b.x);
            int maxX = Math.max(a.x, b.x);
            for (int i = minX; i <= maxX; i++) {
                points.add(new Point(i, y));
            }
        }
        else {
            if (a.x == a.y && b.x == b.y && a.x < b.x) { // 1,1 -> 3,3
                for (int i = a.x; i <= b.y; i++) {
                    points.add(new Point(i, i));
                }
            }
            else if (a.x == a.y && b.x == b.y && a.x > b.y) { // 3,3 -> 1,1
                for (int i = a.x; i >= b.y; i--) {
                    points.add(new Point(i, i));
                }
            }
            else if (a.x != a.y && b.x != b.y && a.x < b.x && a.y < b.y) { // 1,2 -> 3,4
                for (int i = 0; i <= b.x-a.x; i++) {
                    points.add(new Point(a.x+i, a.y+i));
                }
            }
            else if (a.x != a.y && b.x != b.y && a.x > b.x && a.y > b.y) { // 3,4 -> 1,2
                for (int i = 0; i <= a.x-b.x; i++) {
                    points.add(new Point(a.x-i, a.y-i));
                }
            }
            else if (a.x > b.x && a.y < b.y) { // 9,7 -> 7,9
                for (int i = 0; i <= a.x-b.x; i++) {
                    points.add(new Point(a.x-i, a.y+i));
                }
            }
            else if (a.x < b.x && a.y > b.y) { // 7,9 -> 9,7
                for (int i = 0; i <= b.x-a.x; i++) {
                    points.add(new Point(a.x+i, a.y-i));
                }
            }
        }
        return points;
    }
}
