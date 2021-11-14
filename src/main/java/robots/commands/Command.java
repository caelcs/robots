package robots.commands;

import robots.domain.Coordinate;
import robots.domain.Position;

import java.util.Set;

public interface Command {
    Position execute(Position position, Coordinate fieldSize, Set<Position> sents);
}
