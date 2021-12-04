package solutions.day04;

import java.util.List;
import java.util.stream.IntStream;

final class BingoBoard {

    private final List<List<BingoNumber>> numbers;
    private int lastMarked;

    BingoBoard(List<List<BingoNumber>> numbers) {
        this.numbers = numbers;
    }

    void mark(int num) {
        numbers.forEach(row -> row.forEach(bingoNum -> {
            if (bingoNum.getNum() == num) bingoNum.mark();
        }));
        lastMarked = num;
    }

    boolean isWinner() {
        return rowWinner() || columnWinner();
    }

    boolean rowWinner() {
        return numbers.stream().anyMatch(
                row -> row.stream().allMatch(BingoNumber::isMarked)
        );
    }

    boolean columnWinner() {
        return IntStream.range(0, numbers.size()).anyMatch(i ->
                numbers.stream().allMatch(column -> column.get(i).isMarked())
        );
    }

    int getScore() {
        return sumOfUnmarked() * lastMarked;
    }

    private int sumOfUnmarked() {
        return numbers.stream().mapToInt(row ->
                row.stream().filter(bingoNum -> !bingoNum.isMarked()).mapToInt(BingoNumber::getNum).sum()
        ).sum();
    }
}
