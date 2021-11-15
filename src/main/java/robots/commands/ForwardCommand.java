package robots.commands;

import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.Set;

public class ForwardCommand implements Command {

    @Override
    public Position execute(Position position, Coordinate fieldSize, Set<Position> scents) {
        if (scents.stream().anyMatch((it) -> it.equals(position))) {
            return Position.builder().x(position.getX()).y(position.getY()).orientation(position.getOrientation()).build();
        }

        if (position.getOrientation() == Orientation.N && position.getY() == fieldSize.getY()) {
            scents.add(position);
            return Position.builder().x(position.getX()).y(position.getY()).orientation(position.getOrientation()).isLost(true).build();
        }

        if (position.getOrientation() == Orientation.S && position.getY() == 0) {
            scents.add(position);
            return Position.builder().x(position.getX()).y(position.getY()).orientation(position.getOrientation()).isLost(true).build();
        }

        if (position.getOrientation() == Orientation.E && position.getX() == fieldSize.getX()) {
            scents.add(position);
            return Position.builder().x(position.getX()).y(position.getY()).orientation(position.getOrientation()).isLost(true).build();
        }

        if (position.getOrientation() == Orientation.W && position.getX() == 0) {
            scents.add(position);
            return Position.builder().x(position.getX()).y(position.getY()).orientation(position.getOrientation()).isLost(true).build();
        }

        int x = switch (position.getOrientation()) {
            case E -> position.getX() + 1;
            case W -> position.getX() - 1;
            default -> position.getX();
        };

        int y = switch (position.getOrientation()) {
            case N -> position.getY() + 1;
            case S -> position.getY() - 1;
            default -> position.getY();
        };

        return Position.builder()
                .x(x).y(y)
                .orientation(position.getOrientation()).build();
    }
}
