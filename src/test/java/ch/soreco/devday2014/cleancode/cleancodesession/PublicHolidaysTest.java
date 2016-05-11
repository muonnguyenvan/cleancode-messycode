package ch.soreco.devday2014.cleancode.cleancodesession;

import static org.junit.Assert.*;
import org.junit.Test;

public class PublicHolidaysTest {

    private final Calculator worktimeCalendar = new Calculator("VN");

    @Test
    public void secondOfJanuaryIsNotAPublicHoliday() {
        assertFalse(worktimeCalendar.isPH("2014-01-02"));
    }

    @Test
    public void firstOfJanuaryIsAPublicHoliday() {
        assertTrue(worktimeCalendar.isPH("2014-01-01"));
    }

    @Test
    public void victoryDayIsAPublicHoliday() {
        assertTrue(worktimeCalendar.isPH("2014-04-30"));
    }

    @Test
    public void internationalWorkerDayIsAPublicHoliday() {
        assertTrue(worktimeCalendar.isPH("2014-05-01"));
    }

    @Test
    public void NationalDayIsAPublicHoliday() {
        assertTrue(worktimeCalendar.isPH("2014-09-02"));
    }

    @Test
    public void hungVuongKingMemorialDayIsAPublicHoliday() {
        assertTrue(worktimeCalendar.isPH("2014-03-10"));
    }

}
