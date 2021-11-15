package robots.factories;

import robots.domain.Orientation;
import robots.domain.Position;

public class PositionFactory {

    public Position getInstance(String position) {
        String[] parts = position.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Position");
        }

        return Position.builder()
                .x(Integer.parseInt(parts[0]))
                .y(Integer.parseInt(parts[1]))
                .orientation(Orientation.valueOf(parts[2])).build();
    }
}
