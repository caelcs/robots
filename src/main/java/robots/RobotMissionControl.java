package robots;

import robots.commands.Command;

import java.util.ArrayList;
import java.util.List;

public class RobotMissionControl {

    List<Command> commands;

    private final CommandFactory commandFactory;

    public RobotMissionControl(CommandFactory commandFactory) {
        this.commandFactory = commandFactory;
        this.commands = new ArrayList<>();
    }

    public void addInstruction(String instruction) {
        commands.add(commandFactory.createInstance(instruction));
    }
}
