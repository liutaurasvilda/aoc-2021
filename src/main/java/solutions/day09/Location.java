package solutions.day09;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

final class Location {

    private final int rowIndex;
    private final int columnIndex;

    private Location(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    static Location of(int rowIndex, int columnIndex) {
        return new Location(rowIndex, columnIndex);
    }

    List<Location> neighbourhood() {
        return Arrays.stream(Direction.values())
                .map(direction -> direction.neighbourOf(this))
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return rowIndex == location.rowIndex &&
                columnIndex == location.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }

    private enum Direction {

        TOP(-1, 0), BOTTOM(+1, 0),
        LEFT(0, -1), RIGHT(0, +1);

        private final int rowOffset;
        private final int columnOffset;

        Direction(int rowOffset, int columnOffset) {
            this.rowOffset = rowOffset;
            this.columnOffset = columnOffset;
        }

        private Location neighbourOf(Location location) {
            return Location.of(location.rowIndex + rowOffset,
                    location.columnIndex + columnOffset);
        }
    }
}