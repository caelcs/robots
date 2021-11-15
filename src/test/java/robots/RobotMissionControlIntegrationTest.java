package robots;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.domain.Orientation;
import robots.domain.Position;
import robots.factories.CommandFactory;
import robots.factories.CoordinateFactory;
import robots.factories.PositionFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class RobotMissionControlIntegrationTest {

    private RobotMissionControl robotMissionControl;

    @BeforeEach
    public void init() {
        CoordinateFactory coordinateFactory = new CoordinateFactory();
        PositionFactory positionFactory = new PositionFactory();
        CommandFactory commandFactory = new CommandFactory();
        robotMissionControl = new RobotMissionControl(coordinateFactory, positionFactory, commandFactory);
    }

    @Test
    public void shouldReturnRightPositionForOneRobot() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results)
                .contains(Position.builder().x(1).y(1).orientation(Orientation.E).build());
    }

    @Test
    public void shouldReturnRightPositionsForLostRobot() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results).hasSize(1);
        assertThat(robotMissionControl.results)
                .contains(Position.builder().x(3).y(3).orientation(Orientation.N).isLost(true).build());
    }

    @Test
    public void shouldReturnRightPositionsForTwoRobots() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");
        robotMissionControl.addRobot("0 3 W", "LLFFFLFLFL");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results).hasSize(3);
        assertThat(robotMissionControl.results)
                .contains(Position.builder().x(1).y(1).orientation(Orientation.E).build())
                .contains(Position.builder().x(3).y(3).orientation(Orientation.N).isLost(true).build())
                .contains(Position.builder().x(2).y(3).orientation(Orientation.S).isLost(false).build());
    }

    @Test
    public void shouldReturnRightPositionsWhenThereIsScentRobots() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");
        robotMissionControl.addRobot("0 3 W", "LLFFFLFLFL");
        robotMissionControl.addRobot("3 3 N", "FLLFF");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results).hasSize(4);
        assertThat(robotMissionControl.results)
                .contains(Position.builder().x(1).y(1).orientation(Orientation.E).build())
                .contains(Position.builder().x(3).y(3).orientation(Orientation.N).isLost(true).build())
                .contains(Position.builder().x(2).y(3).orientation(Orientation.S).isLost(false).build())
                .contains(Position.builder().x(3).y(1).orientation(Orientation.S).isLost(false).build());
    }

    @Test
    public void shouldReturnRightPositionsWhenThereAreMoreScentsRobots() {
        //Given
        robotMissionControl.setFieldSize("5 3");
        robotMissionControl.addRobot("1 1 E", "RFRFRFRF");
        robotMissionControl.addRobot("3 2 N", "FRRFLLFFRRFLL");
        robotMissionControl.addRobot("4 3 N", "FLLFF");
        robotMissionControl.addRobot("3 3 N", "FRFLFR");

        //When
        robotMissionControl.execute();

        //Then
        assertThat(robotMissionControl.results).hasSize(4);
        assertThat(robotMissionControl.results)
                .contains(Position.builder().x(1).y(1).orientation(Orientation.E).build())
                .contains(Position.builder().x(3).y(3).orientation(Orientation.N).isLost(true).build())
                .contains(Position.builder().x(4).y(3).orientation(Orientation.N).isLost(true).build())
                .contains(Position.builder().x(4).y(3).orientation(Orientation.E).isLost(false).build());
    }

}
