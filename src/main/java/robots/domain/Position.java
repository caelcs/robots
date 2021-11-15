package robots.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
    private Orientation orientation;
    private boolean isLost;
}
