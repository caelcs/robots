package robots;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import robots.commands.Command;
import robots.commands.CreateDimentionCommand;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RobotMissionControlTest {

    @Mock
    private CommandFactory commandFactory;

    @InjectMocks
    private RobotMissionControl robotMissionControl;

    @Test
    public void shouldAddDimension() {
        //Given
        String instruction = "10 12";

        Command createDimensionCommand = new CreateDimentionCommand();
        when(commandFactory.createInstance(instruction)).thenReturn(createDimensionCommand);

        //When
        robotMissionControl.addInstruction(instruction);

        //Then
        assertThat(robotMissionControl.commands).contains(createDimensionCommand);
    }
}
