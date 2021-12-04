package solutions.day04;

import util.ResourceReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Day04 {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(74, 79, 46, 2, 19, 27, 31, 90, 21, 83, 94, 77, 0, 29, 38, 72, 42, 23, 6, 62, 45, 95, 41, 55, 93, 69, 39, 17, 12, 1, 20, 53, 49, 71, 61, 13, 88, 25, 87, 26, 50, 58, 28, 51, 89, 64, 3, 80, 36, 65, 57, 92, 52, 86, 98, 78, 9, 33, 44, 63, 16, 34, 97, 60, 40, 66, 75, 4, 7, 84, 22, 43, 11, 85, 91, 32, 48, 14, 18, 76, 8, 47, 24, 81, 35, 30, 82, 67, 37, 70, 15, 5, 73, 59, 54, 68, 56, 96, 99, 10);
        List<BingoBoard> bingoBoards = populateBoards(ResourceReader.asString("day04.txt"));
        List<Integer> winnerResults = playBingo(numbers, bingoBoards);
        System.out.println(part1(winnerResults));
        System.out.println(part2(winnerResults));
    }

    private static int part1(List<Integer> winnerResults) {
        return winnerResults.get(0);
    }

    private static int part2(List<Integer> winnerResults) {
        return winnerResults.get(winnerResults.size()-1);
    }

    private static List<Integer> playBingo(List<Integer> numbers, List<BingoBoard> bingoBoards) {
        List<Integer> winnerResults = new ArrayList<>();
        IntStream.of(numbers.stream().mapToInt(e -> e).toArray())
                .forEach(number -> bingoBoards.forEach(bingoBoard -> {
                    bingoBoard.mark(number);
                    if (bingoBoard.isWinner()) winnerResults.add(bingoBoard.getScore());
                }));
        return winnerResults;
    }

    private static List<BingoBoard> populateBoards(List<String> input) {
        List<BingoBoard> bingoBoards = new ArrayList<>();
        List<List<BingoNumber>> bingoNumbers = new ArrayList<>();
        for (String line : input) {
            if (line.isEmpty()) continue;
            bingoNumbers.add(Arrays.stream(line.trim().split("\\s+"))
                    .map(Integer::parseInt)
                    .map(BingoNumber::new)
                    .collect(Collectors.toList()));
            if (bingoNumbers.size() == 5) {
                bingoBoards.add(new BingoBoard(bingoNumbers));
                bingoNumbers = new ArrayList<>();
            }
        }
        return bingoBoards;
    }
}
