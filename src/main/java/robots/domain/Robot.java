package robots.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import robots.commands.Command;

import java.util.Deque;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Robot {
    private Position initialPosition;
    private Deque<Command> commands;
}
