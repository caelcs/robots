package robots;

import robots.domain.Coordinate;
import robots.domain.Position;
import robots.domain.Robot;
import robots.factories.CommandFactory;
import robots.factories.CoordinateFactory;
import robots.factories.PositionFactory;

import java.util.*;

public class RobotMissionControl {

    List<Robot> robots;
    Coordinate fieldSize;
    List<Position> results;
    Set<Position> scents;

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
        results = new ArrayList<>();
        scents = new HashSet<>();
    }

    public void setFieldSize(String size) {
        fieldSize = coordinateFactory.getInstance(size);
    }

    public void addRobot(String position, String commands) {
        robots.add(new Robot(positionFactory.getInstance(position),
                commandFactory.getInstance(commands)));
    }

    public void execute() {
        robots.forEach(it -> {
            Position result = processRobot(it);
            results.add(result);
        });
    }

    private Position processRobot(Robot robot) {
        Deque<Position> positions = new ArrayDeque<>();
        positions.add(robot.getInitialPosition());

        robot.getCommands().forEach(command -> {
            Position lastPosition = positions.getLast();
            Position newPosition = command.execute(lastPosition, fieldSize, scents);
            positions.add(newPosition);
        });

        return positions.getLast();
    }
}
