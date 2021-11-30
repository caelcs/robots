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

class ForwardCommandTest {

    ForwardCommand forwardCommand;

    @BeforeEach
    void init() {
        forwardCommand = new ForwardCommand();
    }

    @ParameterizedTest
    @CsvSource(value = {"3,4,N", "3,2,S", "4,3,E", "2,3,W"})
    void shouldMoveForward(Integer x, Integer y, Orientation orientation) {
        //Given
        Position position = new Position(3, 3, orientation, false);
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isEqualTo(new Position(x, y, position.orientation(), false));
    }

    @ParameterizedTest
    @CsvSource(value = {"3,5,N", "3,0,S", "5,3,E", "0,3,W"})
    void shouldCreateAScentWhenThereIsNoScentOnCurrentPosition(Integer x, Integer y, Orientation orientation) {
        Position position = new Position(x, y, orientation, false);
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result.x()).isEqualTo(position.x());
        assertThat(result.y()).isEqualTo(position.y());
        assertThat(result.orientation()).isEqualTo(position.orientation());
        assertThat(result.isLost()).isTrue();

        assertThat(scents).contains(position);
    }

    @Test
    void shouldIgnoreCommandWhenThereIsAScent() {
        Position position = new Position(5, 5, Orientation.N, false);
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();
        scents.add(position);

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isEqualTo(position);
        assertThat(scents).contains(position);
    }
}