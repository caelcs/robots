package robots.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static robots.domain.Orientation.N;

class ForwardCommandTest {

    ForwardCommand forwardCommand;

    @BeforeEach
    public void init() {
        forwardCommand = new ForwardCommand();
    }

    @ParameterizedTest
    @CsvSource(value = {"3,4,N", "3,2,S", "4,3,E", "2,3,W"})
    public void shouldMoveForward(String x, String y, String orientation) {
        //Given
        Position position = new Position(3, 3, Orientation.valueOf(orientation));
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(new Position(
                Integer.parseInt(x),
                Integer.parseInt(y),
                position.getOrientation()));
    }

    @ParameterizedTest
    @CsvSource(value = {"3,5,N", "3,0,S", "5,3,E", "0,3,W"})
    public void shouldCreateAScentWhenThereIsNoScentOnCurrentPosition(String x, String y, String orientation) {
        Position position = new Position(Integer.parseInt(x), Integer.parseInt(y), Orientation.valueOf(orientation));
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isEqualTo(position);
        assertThat(scents).contains(position);
    }
}