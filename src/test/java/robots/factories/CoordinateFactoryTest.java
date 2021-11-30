package robots.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import robots.domain.Coordinate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoordinateFactoryTest {

    private CoordinateFactory coordinateFactory;

    @BeforeEach
    void initFactory() {
        coordinateFactory = new CoordinateFactory();
    }

    @Test
    void shouldCreateCoordinate() {
        //Given
        String coordinate = "10 5";

        //When
        Coordinate result = coordinateFactory.getInstance(coordinate);

        //Then
        assertThat(result).isEqualTo(new Coordinate(10, 5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"102","12 12 2",""})
    void shouldThrowExceptionWhenFormatIsInvalid(String instruction) {
        //When
        assertThatThrownBy(() -> coordinateFactory.getInstance(instruction))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid field size");

    }

    @Test
    void shouldThrowExceptionWhenXIsGreater() {
        //When
        assertThatThrownBy(() -> coordinateFactory.getInstance("51 1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Max coordinate is 50");
    }

    @Test
    void shouldThrowExceptionWhenYIsGreater() {
        //When
        assertThatThrownBy(() -> coordinateFactory.getInstance("1 51"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Max coordinate is 50");
    }
}