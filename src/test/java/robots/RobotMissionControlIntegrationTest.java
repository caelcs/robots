package robots;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.commands.Command;
import robots.commands.ForwardCommand;
import robots.commands.TurnLeftCommand;
import robots.commands.TurnRightCommand;
import robots.domain.Orientation;
import robots.domain.Position;
import robots.factories.CommandFactory;
import robots.factories.CoordinateFactory;
import robots.factories.PositionFactory;

import static org.assertj.core.api.Assertions.assertThat;

class RobotMissionControlIntegrationTest {

    private RobotMissionControl robotMissionControl;

    @BeforeEach
    void init() {
        Command forwardCommand = new ForwardCommand();
        Command leftCommand = new TurnLeftCommand();
        Command rightCommand = new TurnRightCommand();
        CoordinateFactory coordinateFactory = new CoordinateFactory();
        PositionFactory positionFactory = new PositionFactory();
        CommandFactory commandFactory = new CommandFactory(forwardCommand, leftCommand, rightCommand);
        robotMissionControl = new RobotMissionControl(coordinateFactory, positionFactory, commandFactory);
    }

    @Test
    void shouldReturnRightPositionForOneRobot() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results)
                .contains(new Position(1, 1, Orientation.E, false));
    }

    @Test
    void shouldReturnRightPositionsForLostRobot() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results)
                .hasSize(1)
                .contains(new Position(3, 3, Orientation.N,true));
    }

    @Test
    void shouldReturnRightPositionsForTwoRobots() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");
        robotMissionControl.addRobot("0 3 W", "LLFFFLFLFL");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results)
                .hasSize(3)
                .contains(new Position(1, 1, Orientation.E, false))
                .contains(new Position(2, 3, Orientation.S, false));
    }

    @Test
    void shouldReturnRightPositionsWhenThereIsScentRobots() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");
        robotMissionControl.addRobot("0 3 W", "LLFFFLFLFL");
        robotMissionControl.addRobot("3 3 N", "FLLFF");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results)
                .hasSize(4)
                .contains(new Position(1,1, Orientation.E, false))
                .contains(new Position(3,3, Orientation.N, true))
                .contains(new Position(2,3, Orientation.S, false))
                .contains(new Position(3,1, Orientation.S, false));
    }

    @Test
    void shouldReturnRightPositionsWhenThereAreMoreScentsRobots() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");
        robotMissionControl.addRobot("4 3 N", "FLLFF");
        robotMissionControl.addRobot("3 3 N", "FRFLFR");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results)
                .hasSize(4)
                .contains(new Position(1,1, Orientation.E, false))
                .contains(new Position(3,3, Orientation.N,true))
                .contains(new Position(4,3, Orientation.N,true))
                .contains(new Position(4,3, Orientation.E,false));
    }

}
