package robots.domain;

import robots.commands.Command;

import java.util.Deque;

public record Robot(Position initialPosition, Deque<Command> commands) {
}
