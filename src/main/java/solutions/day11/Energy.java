package solutions.day11;

final class Energy {

    private final int level;
    private final boolean flashed;

    Energy(int level) {
        this(level, false);
    }

    Energy(int level, boolean flashed) {
        this.level = level;
        this.flashed = flashed;
    }

    int getLevel() {
        return level;
    }

    boolean discharged() {
        return flashed;
    }

    Energy discharge() {
        return new Energy(this.level, true);
    }
}
