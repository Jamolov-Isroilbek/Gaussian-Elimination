package time;

import static check.CheckThat.Condition.*;
import org.junit.jupiter.api.*;
import check.CheckThat;

public class TimeStructureTest {
    // // Force the language if the system default is not suitable.
    // @BeforeAll
    // public static void beforeAll() {
    //     CheckThat.setLang("EN");
    // }

    @Test
    public void fieldHour() {
        CheckThat.theClass("time.Time")
            .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
            .hasFieldOfType("hour", int.class)
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
                .thatHas(GETTER, SETTER);
    }

    @Test
    public void fieldMin() {
        CheckThat.theClass("time.Time")
            .hasFieldOfType("min", int.class)
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, MODIFIABLE, VISIBLE_TO_NONE)
                .thatHas(GETTER, SETTER);
    }

    @Test
    public void constructor() {
        CheckThat.theClass("time.Time")
            .hasConstructorWithParams(int.class, int.class)
                .thatIs(VISIBLE_TO_ALL);
    }

    @Test
    public void methodGetEarlier() {
        CheckThat.theClass("time.Time")
            .hasMethodWithParams("getEarlier", "Time")
                .thatIs(FULLY_IMPLEMENTED, INSTANCE_LEVEL, VISIBLE_TO_ALL)
                .thatReturns("Time");
    }
}
