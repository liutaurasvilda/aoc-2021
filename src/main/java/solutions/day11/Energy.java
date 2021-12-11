package solutions.day11;

final class Energy {

    private final int level;
    private final boolean discharged;

    Energy(int level) {
        this(level, false);
    }

    Energy(int level, boolean discharged) {
        this.level = level;
        this.discharged = discharged;
    }

    int getLevel() {
        return level;
    }

    boolean discharged() {
        return discharged;
    }

    Energy discharge() {
        return new Energy(this.level, true);
    }
}
