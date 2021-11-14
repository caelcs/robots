package robots.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.commands.Command;
import robots.commands.ForwardCommand;
import robots.commands.TurnLeftCommand;
import robots.commands.TurnRightCommand;

import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommandFactoryTest {

    private CommandFactory commandFactory;

    @BeforeEach
    public void init() {
        commandFactory = new CommandFactory();
    }

    @Test
    public void shouldCreateTurnLeftCommand() {
        //Given
        String instructions = "L";

        //When
        Deque<Command> commands = commandFactory.getInstance(instructions);

        //Then
        assertThat(commands).isNotEmpty();
        assertThat(commands).hasSize(1);
        assertThat(commands.getLast()).isInstanceOf(TurnLeftCommand.class);
    }

    @Test
    public void shouldCreateTurnRightCommand() {
        //Given
        String instructions = "R";

        //When
        Deque<Command> commands = commandFactory.getInstance(instructions);

        //Then
        assertThat(commands).isNotEmpty();
        assertThat(commands).hasSize(1);
        assertThat(commands.getLast()).isInstanceOf(TurnRightCommand.class);
    }

    @Test
    public void shouldCreateForwardCommand() {
        //Given
        String instructions = "F";

        //When
        Deque<Command> commands = commandFactory.getInstance(instructions);

        //Then
        assertThat(commands).isNotEmpty();
        assertThat(commands).hasSize(1);
        assertThat(commands.getLast()).isInstanceOf(ForwardCommand.class);
    }

    @Test
    public void shouldThrowExceptionWhenCommandIsNotSupported() {
        //Given
        String instructions = "Z";

        //When
        assertThatThrownBy(() -> commandFactory.getInstance(instructions))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No enum constant robots.domain.Movement.Z");
    }
}