package solutions.day06;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class Fish {

    private final int days;

    Fish(int days) {
        this.days = days;
    }

    List<Fish> tick() {
        if (days == 0) {
            return Arrays.asList(
                    new Fish(6),
                    new Fish(8)
            );
        }
        return Collections.singletonList(new Fish(days - 1));
    }

    int days() {
        return days;
    }
}
