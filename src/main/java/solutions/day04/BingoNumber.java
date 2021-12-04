package solutions.day04;

final class BingoNumber {

    private final int number;
    private boolean marked;

    BingoNumber(int number) {
        this.number = number;
        this.marked = false;
    }

    int getNumber() {
        return number;
    }

    boolean isMarked() {
        return marked;
    }

    void mark() {
        marked = true;
    }
}
