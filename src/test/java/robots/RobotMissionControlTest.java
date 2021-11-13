package robots;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robots.commands.Command;
import robots.domain.Coordinate;
import robots.domain.Orientation;
import robots.domain.Position;
import robots.factories.CommandFactory;
import robots.factories.CoordinateFactory;
import robots.factories.PositionFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RobotMissionControlTest {

    @Mock
    private Command command;

    @Mock
    private CoordinateFactory coordinateFactory;

    @Mock
    private PositionFactory positionFactory;

    @Mock
    private CommandFactory commandFactory;

    @InjectMocks
    private RobotMissionControl robotMissionControl;

    @Test
    public void shouldSetFieldSize() {
        //Given
        String size = "10 6";

        //And
        Coordinate expectedCoordinate = new Coordinate(10, 6);
        when(coordinateFactory.getInstance(size)).thenReturn(expectedCoordinate);

        //When
        robotMissionControl.setFieldSize(size);

        //Then
        assertThat(robotMissionControl.fieldSize).isEqualTo(expectedCoordinate);
    }

    @Test
    public void shouldAddRobot() {
        //Given
        String position = "10 3 E";
        String commands = "RLF";

        //And
        when(positionFactory.getInstance(position)).thenReturn(new Position(10, 3, Orientation.E));
        Deque<Command> expectedCommands = new ArrayDeque<>();
        expectedCommands.add(command);
        when(commandFactory.getinstance(commands)).thenReturn(expectedCommands);

        //When
        robotMissionControl.addRobot(position, commands);

        //Then
        assertThat(robotMissionControl.robots).isNotEmpty();
    }
}
