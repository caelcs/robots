package robots.factories;

import robots.commands.Command;
import robots.commands.ForwardCommand;
import robots.commands.TurnLeftCommand;
import robots.commands.TurnRightCommand;
import robots.domain.Movement;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CommandFactory {

    public Deque<Command> getInstance(String commands) {
        if (commands.contains(" ")) {
            throw new IllegalArgumentException("No space allowed between instructions");
        }

        if (commands.length() > 99) {
            throw new IllegalArgumentException("commands size should be less than 100");
        }

        char[] charArray = commands.toCharArray();
        return IntStream.range(0, charArray.length)
                .mapToObj(it -> create(String.valueOf(charArray[it])))
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Command create(String aChar) {
        Movement movement = Movement.valueOf(aChar);
        return switch (movement) {
            case F -> new ForwardCommand();
            case L -> new TurnLeftCommand();
            case R -> new TurnRightCommand();
        };
    }

}
