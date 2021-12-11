package solutions.day11;

final class Energy {

    private int level;
    private boolean flashed;

    Energy(int level) {
        this(level, false);
    }

    Energy(int level, boolean flashed) {
        this.level = level;
        this.flashed = flashed;
    }

    public int getLevel() {
        return level;
    }

    public boolean isFlashed() {
        return flashed;
    }

    public Energy setFlashed() {
        return new Energy(this.level, true);
    }
}
