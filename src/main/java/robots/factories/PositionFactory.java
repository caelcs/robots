package robots.factories;

import robots.domain.Orientation;
import robots.domain.Position;

public class PositionFactory {

    public Position getInstance(String position) {
        String[] parts = position.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Position");
        }

        return new Position(Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Orientation.valueOf(parts[2]),
                false);
    }
}
