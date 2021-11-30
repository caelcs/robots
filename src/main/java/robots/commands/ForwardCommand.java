package robots.commands;

import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.Set;

public class ForwardCommand implements Command {

    @Override
    public Position execute(Position position, Coordinate fieldSize, Set<Position> scents) {
        if (scents.stream().anyMatch(it -> it.equals(position))) {
            return new Position(position.x(), position.y(), position.orientation(), false);
        }

        if (position.orientation() == Orientation.N && position.y() == fieldSize.y()) {
            scents.add(position);
            return new Position(position.x(), position.y(), position.orientation(), true);
        }

        if (position.orientation() == Orientation.S && position.y() == 0) {
            scents.add(position);
            return new Position(position.x(), 0, position.orientation(), true);
        }

        if (position.orientation() == Orientation.E && position.x() == fieldSize.x()) {
            scents.add(position);
            return new Position(position.x(), position.y(), position.orientation(), true);
        }

        if (position.orientation() == Orientation.W && position.x() == 0) {
            scents.add(position);
            return new Position(0, position.y(), position.orientation(), true);
        }

        int x = switch (position.orientation()) {
            case E -> position.x() + 1;
            case W -> position.x() - 1;
            default -> position.x();
        };

        int y = switch (position.orientation()) {
            case N -> position.y() + 1;
            case S -> position.y() - 1;
            default -> position.y();
        };

        return new Position(x, y, position.orientation(), false);
    }
}
