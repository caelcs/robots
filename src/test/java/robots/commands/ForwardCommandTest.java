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
    public void init() {
        forwardCommand = new ForwardCommand();
    }

    @ParameterizedTest
    @CsvSource(value = {"3,4,N", "3,2,S", "4,3,E", "2,3,W"})
    public void shouldMoveForward(Integer x, Integer y, Orientation orientation) {
        //Given
        Position position = Position.builder().x(3).y(3).orientation(orientation).build();
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(Position.builder().x(x).y(y).orientation(position.getOrientation()).isLost(false).build());
    }

    @ParameterizedTest
    @CsvSource(value = {"3,5,N", "3,0,S", "5,3,E", "0,3,W"})
    public void shouldCreateAScentWhenThereIsNoScentOnCurrentPosition(Integer x, Integer y, Orientation orientation) {
        Position position = Position.builder().x(x).y(y).orientation(orientation).build();
        Coordinate fieldSize = new Coordinate(5, 5);
        HashSet<Position> scents = new HashSet<>();

        //When
        Position result = forwardCommand.execute(position, fieldSize, scents);

        //Then
        assertThat(result.getX()).isEqualTo(position.getX());
        assertThat(result.getY()).isEqualTo(position.getY());
        assertThat(result.getOrientation()).isEqualTo(position.getOrientation());
        assertThat(result.isLost()).isTrue();

        assertThat(scents).contains(position);
    }

    @Test
    public void shouldIgnoreCommandWhenThereIsAScent() {
        Position position = Position.builder().x(5).y(5).orientation(Orientation.N).build();
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