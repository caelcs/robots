package robots.commands;

import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.Map;
import java.util.Set;

public class TurnLeftCommand implements Command {

    Map<Orientation, Orientation> turnTo = Map.of(
            Orientation.E, Orientation.N,
            Orientation.N, Orientation.W,
            Orientation.W, Orientation.S,
            Orientation.S, Orientation.E);

    @Override
    public Position execute(Position position, Coordinate fieldSize, Set<Position> scents) {
        return new Position(position.x(), position.y(), turnTo.get(position.orientation()), false);
    }
}
