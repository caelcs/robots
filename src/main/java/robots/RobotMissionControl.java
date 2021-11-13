package robots;

import robots.domain.Coordinate;
import robots.domain.Robot;
import robots.factories.CommandFactory;
import robots.factories.CoordinateFactory;
import robots.factories.PositionFactory;

import java.util.ArrayList;
import java.util.List;

public class RobotMissionControl {

    List<Robot> robots;
    Coordinate fieldSize;

    private final CoordinateFactory coordinateFactory;
    private final PositionFactory positionFactory;
    private final CommandFactory commandFactory;

    public RobotMissionControl(CoordinateFactory coordinateFactory,
                               PositionFactory positionFactory,
                               CommandFactory commandFactory) {
        this.coordinateFactory = coordinateFactory;
        this.positionFactory = positionFactory;
        this.commandFactory = commandFactory;
        robots = new ArrayList<>();
    }

    public void setFieldSize(String size) {
        fieldSize = coordinateFactory.getInstance(size);
    }

    public void addRobot(String position, String commands) {
        robots.add(new Robot(positionFactory.getInstance(position),
                commandFactory.getinstance(commands)));
    }
}
