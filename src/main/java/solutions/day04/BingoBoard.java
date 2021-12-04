package solutions.day04;

import java.util.List;
import java.util.stream.IntStream;

final class BingoBoard {

    private final List<List<BingoNumber>> numbers;
    private int lastNumber;
    private boolean wereWinner;

    BingoBoard(List<List<BingoNumber>> numbers) {
        this.numbers = numbers;
    }

    void mark(int number) {
        numbers.forEach(row -> row.forEach(bingoNumber -> {
            if (bingoNumber.getNumber() == number) bingoNumber.mark();
        }));
        lastNumber = number;
    }

    boolean isWinner() {
        boolean isWinner = (rowWinner() || columnWinner()) && !wereWinner;
        if (isWinner) wereWinner = true;
        return isWinner;
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
        return numbers.stream().mapToInt(row -> row.stream()
                .filter(bingoNumber -> !bingoNumber.isMarked())
                .mapToInt(BingoNumber::getNumber).sum()
        ).sum() * lastNumber;
    }
}
