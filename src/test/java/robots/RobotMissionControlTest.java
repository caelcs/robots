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
import robots.domain.Robot;
import robots.factories.CommandFactory;
import robots.factories.CoordinateFactory;
import robots.factories.PositionFactory;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RobotMissionControlTest {

    @Mock
    private Command command;

    @Mock
    private Command command2;

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
        when(positionFactory.getInstance(position)).thenReturn(Position.builder().x(10).y(3).orientation(Orientation.E).build());
        Deque<Command> expectedCommands = new ArrayDeque<>();
        expectedCommands.add(command);
        when(commandFactory.getInstance(commands)).thenReturn(expectedCommands);

        //When
        robotMissionControl.addRobot(position, commands);

        //Then
        assertThat(robotMissionControl.robots).isNotEmpty();
    }

    @Test
    public void shouldExecuteOneCommand() {
        //Given
        ArrayDeque<Command> commands = new ArrayDeque<>();
        commands.add(command);
        Position position = Position.builder().x(5).y(5).orientation(Orientation.E).build();
        robotMissionControl.robots.add(new Robot(position, commands));
        robotMissionControl.fieldSize = new Coordinate(10, 10);

        //And
        Position expectedFinalPosition = Position.builder().x(6).y(5).orientation(Orientation.E).build();
        when(command.execute(position, robotMissionControl.fieldSize, robotMissionControl.scents)).thenReturn(expectedFinalPosition);

        //When
        robotMissionControl.execute();
        assertThat(robotMissionControl.results).hasSize(1);
        assertThat(robotMissionControl.results).contains(expectedFinalPosition);
    }

    @Test
    public void shouldExecuteMultipleCommand() {
        //Given
        ArrayDeque<Command> commands = new ArrayDeque<>();
        commands.add(command);
        commands.add(command2);
        Position position = Position.builder().x(5).y(5).orientation(Orientation.E).build();;
        robotMissionControl.robots.add(new Robot(position, commands));
        robotMissionControl.fieldSize = new Coordinate(10, 10);

        //And
        Position expectedIntermediatePosition = Position.builder().x(6).y(5).orientation(Orientation.E).build();;
        when(command.execute(position, robotMissionControl.fieldSize, robotMissionControl.scents)).thenReturn(expectedIntermediatePosition);

        //And
        Position expectedFinalPosition = Position.builder().x(7).y(5).orientation(Orientation.E).build();;
        when(command2.execute(expectedIntermediatePosition, robotMissionControl.fieldSize, robotMissionControl.scents)).thenReturn(expectedFinalPosition);

        //When
        robotMissionControl.execute();
        assertThat(robotMissionControl.results).hasSize(1);
        assertThat(robotMissionControl.results).contains(expectedFinalPosition);
    }

    @Test
    public void shouldExecuteMultipleRobots() {
        //Given
        ArrayDeque<Command> commands = new ArrayDeque<>();
        commands.add(command);
        Position position = Position.builder().x(5).y(5).orientation(Orientation.E).build();
        robotMissionControl.robots.add(new Robot(position, commands));

        //And
        ArrayDeque<Command> commands2 = new ArrayDeque<>();
        commands2.add(command2);
        Position position2 = Position.builder().x(7).y(7).orientation(Orientation.E).build();;
        robotMissionControl.robots.add(new Robot(position2, commands2));

        robotMissionControl.fieldSize = new Coordinate(10, 10);

        //And
        Position expectedFinalPosition = Position.builder().x(6).y(5).orientation(Orientation.E).build();;
        when(command.execute(position, robotMissionControl.fieldSize, robotMissionControl.scents)).thenReturn(expectedFinalPosition);

        //And
        Position expectedFinalPosition2 = Position.builder().x(8).y(8).orientation(Orientation.E).build();
        when(command2.execute(position2, robotMissionControl.fieldSize, robotMissionControl.scents)).thenReturn(expectedFinalPosition2);

        //When
        robotMissionControl.execute();
        assertThat(robotMissionControl.results).hasSize(2);
        assertThat(robotMissionControl.results).containsExactly(expectedFinalPosition, expectedFinalPosition2);
    }
}
