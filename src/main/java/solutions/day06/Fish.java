package solutions.day06;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

final class Fish {

    private int days;

    Fish(int days) {
        this.days = days;
    }

    List<Optional<Fish>> tick() {
        if (days == 0) {
            days = 6;
            return Arrays.asList(
                    Optional.of(new Fish(6)),
                    Optional.of(new Fish(8))
            );
        }
        return Collections.singletonList(Optional.of(new Fish(--days)));
    }

    int days() {
        return days;
    }
}
