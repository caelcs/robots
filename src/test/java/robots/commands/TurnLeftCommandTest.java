package robots.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

class TurnLeftCommandTest {

    TurnLeftCommand turnLeftCommand;

    @BeforeEach
    public void init() {
        turnLeftCommand = new TurnLeftCommand();
    }

    @ParameterizedTest
    @CsvSource(value = {"E,N", "N,W", "W,S", "S,E"})
    public void shouldTurnLeft(Orientation actual, Orientation expected) {
        //Given
        Position position = Position.builder().x(3).y(3).orientation(actual).build();
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = turnLeftCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(Position.builder().x(3).y(3).orientation(expected).build());
    }

}