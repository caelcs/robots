package robots.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robots.commands.Command;

import java.util.Deque;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class CommandFactoryTest {

    @Mock
    private Command forwardCommand;

    @Mock
    private Command leftCommand;

    @Mock
    private Command rightCommand;

    private CommandFactory commandFactory;

    @BeforeEach
    public void init() {
        commandFactory = new CommandFactory(forwardCommand, leftCommand, rightCommand);
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
        assertThat(commands.getLast()).isEqualTo(leftCommand);
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
        assertThat(commands.getLast()).isEqualTo(rightCommand);
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
        assertThat(commands.getLast()).isEqualTo(forwardCommand);
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

    @Test
    public void shouldThrowExceptionWhenCommandFormatIsInvalid() {
        //Given
        String instructions = "L F";

        //When
        assertThatThrownBy(() -> commandFactory.getInstance(instructions))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No space allowed between instructions");
    }

    @Test
    public void shouldCreateMultipleCommandsWithRightOrder() {
        //Given
        String instructions = "FLR";

        //When
        Deque<Command> commands = commandFactory.getInstance(instructions);

        //Then
        assertThat(commands).isNotEmpty();
        assertThat(commands).hasSize(3);
        assertThat(commands.pollFirst()).isEqualTo(forwardCommand);
        assertThat(commands.pollFirst()).isEqualTo(leftCommand);
        assertThat(commands.pollFirst()).isEqualTo(rightCommand);
    }

    @Test
    public void shouldThrowExceptionWhenCommandLengthIsGreaterThan100() {
        //Given
        String instructions = "L".repeat(101);

        //When
        assertThatThrownBy(() -> commandFactory.getInstance(instructions))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("commands size should be less than 100");
    }
}