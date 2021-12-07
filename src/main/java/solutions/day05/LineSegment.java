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
            if (a.x < b.x && a.y > b.y) {
                for (int i = 0; i <= b.x-a.x; i++) {
                    points.add(new Point(a.x+i, a.y-i));
                }
            } else if (a.x > b.x && a.y < b.y) {
                for (int i = 0; i <= a.x-b.x; i++) {
                    points.add(new Point(a.x-i, a.y+i));
                }
            } else if (a.x < b.x && a.y < b.y) {
                for (int i = 0; i <= b.x-a.x; i++) {
                    points.add(new Point(a.x+i, a.y+i));
                }
            } else if (a.x > b.x && a.y > b.y) {
                for (int i = 0; i <= a.x-b.x; i++) {
                    points.add(new Point(a.x-i, a.y-i));
                }
            }
        }
        return points;
    }
}
