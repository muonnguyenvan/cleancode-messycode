package ch.soreco.devday2014.cleancode.cleancodesession;

import org.junit.Test;
import static org.junit.Assert.*;

public class WeekendCalculationTest {

    private final Calculator worktimeCalendar = new Calculator("VN");

    @Test
    public void dateOnMondayIsNotRecognizedAsWeekend() {
        assertFalse(worktimeCalendar.isWE("2012-01-23"));
    }

    @Test
    public void dateOnSaturdayIsRecognizedAsWeekend() {
        assertTrue(worktimeCalendar.isWE("2012-01-21"));
    }

    @Test
    public void dateOnSundayIsRecognizedAsWeekend() {
        assertTrue(worktimeCalendar.isWE("2012-01-22"));
    }
}
