package ch.soreco.devday2014.cleancode.cleancodesession;

import org.junit.Test;
import static org.junit.Assert.*;
import static ch.soreco.devday2014.cleancode.cleancodesession.CalendarTestConstants.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 * Be aware that the class under test uses the term CNY (=Chinese New Year) for Tet
 */
public class TetCalculationTest {

    private final Calculator worktimeCalendar = new Calculator("VN");

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void providingNullThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        worktimeCalendar.isCNY(null);
    }

    @Test
    public void providingIllegalDateStringFormatThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        worktimeCalendar.isCNY("11.11.2011");
    }

    @Test
    public void checkingTetDatesBefore1930ThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        worktimeCalendar.isCNY("1919-12-31");
    }

    @Test
    public void checkingTetDatesAfter2030ThrowsException() {
        thrown.expect(IllegalArgumentException.class);
        worktimeCalendar.isCNY("2031-12-31");
    }

    @Test
    public void tetDatesBetween1930And2030ShouldBeRecognized() {
        for (String tetDateString : TET_DATE_STRINGS_1930_TO_2030) {
            assertTrue(tetDateString + " is not recognized as Tet date!", worktimeCalendar.isCNY(tetDateString));
        }
    }

    @Test
    public void whenTetIsOnMonday_TheWholeWeekIsHoliday() {

        assertFalse(worktimeCalendar.isCNY("2012-01-22"));
        assertTrue(worktimeCalendar.isCNY("2012-01-23"));
        assertTrue(worktimeCalendar.isCNY("2012-01-24"));
        assertTrue(worktimeCalendar.isCNY("2012-01-25"));
        assertTrue(worktimeCalendar.isCNY("2012-01-26"));
        assertTrue(worktimeCalendar.isCNY("2012-01-27"));
        assertFalse(worktimeCalendar.isCNY("2012-01-28"));

    }

    @Test
    public void whenTetIsOnTuesday_HolidayEndsNextMonday() {

        assertFalse(worktimeCalendar.isCNY("1999-02-15"));
        assertTrue(worktimeCalendar.isCNY("1999-02-16"));
        assertTrue(worktimeCalendar.isCNY("1999-02-17"));
        assertTrue(worktimeCalendar.isCNY("1999-02-18"));
        assertTrue(worktimeCalendar.isCNY("1999-02-19"));
        assertTrue(worktimeCalendar.isCNY("1999-02-22"));
        assertFalse(worktimeCalendar.isCNY("1999-02-23"));

    }

    @Test
    public void whenTetIsOnSaturday_HolidayEndsNextFriday() {

        assertFalse(worktimeCalendar.isCNY("2003-01-31"));
        assertTrue(worktimeCalendar.isCNY("2003-02-03"));
        assertTrue(worktimeCalendar.isCNY("2003-02-04"));
        assertTrue(worktimeCalendar.isCNY("2003-02-05"));
        assertTrue(worktimeCalendar.isCNY("2003-02-06"));
        assertTrue(worktimeCalendar.isCNY("2003-02-07"));
        assertFalse(worktimeCalendar.isCNY("2003-02-10"));

    }

    @Test
    public void whenTetIsOnSunday_HolidayEndsNextFriday() {

        assertFalse(worktimeCalendar.isCNY("2010-02-13"));
        assertTrue(worktimeCalendar.isCNY("2010-02-15"));
        assertTrue(worktimeCalendar.isCNY("2010-02-16"));
        assertTrue(worktimeCalendar.isCNY("2010-02-17"));
        assertTrue(worktimeCalendar.isCNY("2010-02-18"));
        assertTrue(worktimeCalendar.isCNY("2010-02-19"));
        assertFalse(worktimeCalendar.isCNY("2010-02-22"));

    }

    @Test
    public void nonTetDatesShouldBeRecognized() {
        assertFalse(worktimeCalendar.isCNY("2014-11-25"));
        assertFalse(worktimeCalendar.isCNY("2012-12-31"));
        assertFalse(worktimeCalendar.isCNY("2015-01-01"));
    }
}
