package solutions.day02;

import util.ResourceReader;

import java.util.List;

final class Day02 {

    public static void main(String[] args) {
        List<String> input = ResourceReader.asString("day02.txt");
        System.out.println(part1(input));
        System.out.println(part2(input));
    }

    public static long part1(List<String> commands) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commands.forEach(commandExecutor::executeSimpleCommand);
        return commandExecutor.position();
    }

    public static long part2(List<String> commands) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commands.forEach(commandExecutor::executeComplexCommand);
        return commandExecutor.position();
    }
}
