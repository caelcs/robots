package robots.factories;

import robots.domain.Coordinate;

public class CoordinateFactory {

    public Coordinate getInstance(String instruction) {
        String[] coordinates = instruction.split(" ");
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Invalid field size");
        }
        return new Coordinate(Integer.parseInt(coordinates[0]),
                Integer.parseInt(coordinates[1]));
    }

}
