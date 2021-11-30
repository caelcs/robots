package robots.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class TurnRightCommandTest {

    TurnRightCommand turnRightCommand;

    @BeforeEach
    public void init() {
        turnRightCommand = new TurnRightCommand();
    }

    @ParameterizedTest
    @CsvSource(value = {"E,S", "S,W", "W,N", "N,E"})
    void shouldTurnRight(Orientation actual, Orientation expected) {
        //Given
        Position position = new Position(3, 3, actual, false);
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = turnRightCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isEqualTo(new Position(3, 3, expected, false));
    }

}