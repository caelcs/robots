package robots.factories;

import robots.domain.Coordinate;

public class CoordinateFactory {

    public Coordinate getInstance(String instruction) {
        String[] coordinates = instruction.split(" ");
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Invalid field size");
        }

        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        validateCoordinate(x, y);

        return new Coordinate(x, y);
    }

    private void validateCoordinate(int x, int y) {
        if (x > 50 || y > 50) {
            throw new IllegalArgumentException("Max coordinate is 50");
        }
    }

}
