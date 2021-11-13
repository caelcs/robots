package robots.commands;

import robots.domain.Position;

public interface Command {
    Position execute(Position position);
}
