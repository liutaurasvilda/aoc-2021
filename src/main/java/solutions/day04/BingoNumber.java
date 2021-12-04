package solutions.day04;

final class BingoNumber {

    private final int num;
    private boolean marked;

    BingoNumber(int num) {
        this.num = num;
        this.marked = false;
    }

    int getNum() {
        return num;
    }

    boolean isMarked() {
        return marked;
    }

    void mark() {
        marked = true;
    }
}
