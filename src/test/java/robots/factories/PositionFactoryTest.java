package robots.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import robots.domain.Position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static robots.domain.Orientation.E;

class PositionFactoryTest {

    private PositionFactory positionFactory;

    @BeforeEach
    void init() {
        positionFactory = new PositionFactory();
    }

    @Test
    void shouldCreatePosition() {
        //Given
        String position = "1 3 E";

        //When
        Position result = positionFactory.getInstance(position);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.x()).isEqualTo(1);
        assertThat(result.y()).isEqualTo(3);
        assertThat(result.orientation()).isEqualTo(E);

    }

    @ParameterizedTest
    @ValueSource(strings = {"102","12 12","", "2 3 E 5"})
    void shouldThrowExceptionWhenFormatIsInvalid(String position) {
        //When
        assertThatThrownBy(() -> positionFactory.getInstance(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid Position");
    }

    @Test
    void shouldThrowExceptionWhenOrientationIsInvalid() {
        //When
        assertThatThrownBy(() -> positionFactory.getInstance("1 2 T"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("No enum constant robots.domain.Orientation.T");
    }
}