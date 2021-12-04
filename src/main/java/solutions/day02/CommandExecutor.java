package solutions.day02;

final class CommandExecutor {

    private long xPosition;
    private long yPosition;
    private long aim;

    public void executeSimpleCommand(String input) {
        Command command = new Command(input);
        switch (command.direction) {
            case "forward":
                xPosition += command.distance;
                break;
            case "down":
                yPosition += command.distance;
                break;
            case "up":
                yPosition -= command.distance;
                break;
        }
    }

    public void executeComplexCommand(String input) {
        Command command = new Command(input);
        switch (command.direction) {
            case "forward":
                xPosition += command.distance;
                yPosition += aim * command.distance;
                break;
            case "down":
                aim += command.distance;
                break;
            case "up":
                aim -= command.distance;
                break;
        }
    }

    public long position() {
        return this.xPosition * this.yPosition;
    }

    private static final class Command {
        private final String direction;
        private final long distance;

        public Command(String input) {
            this.direction = input.split(" ")[0];
            this.distance = Long.parseLong(input.split(" ")[1]);
        }
    }
}
