package robots.commands;

import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.Map;
import java.util.Set;

public class TurnRightCommand implements Command {

    Map<Orientation, Orientation> turnTo = Map.of(
            Orientation.E, Orientation.S,
            Orientation.S, Orientation.W,
            Orientation.W, Orientation.N,
            Orientation.N, Orientation.E);

    @Override
    public Position execute(Position position, Coordinate fieldSize, Set<Position> scents) {
        return new Position(position.x(), position.y(), turnTo.get(position.orientation()), false);
    }
}
