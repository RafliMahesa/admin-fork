package id.ac.ui.cs.pustakaone.admin.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LogTest {

    @Test
    void testLogCreation() {
        String action = "Review dengan id 5 berhasil dihapus";
        String date = "2024-04-24T12:00:00Z";

        Log log = new Log(action, date);

        assertEquals(action, log.getAction());
        assertEquals(date, log.getDate());
    }

    @Test
    void testSetters() {
        String action = "Review dengan id 6 berhasil dihapus";
        String date = "2024-04-24T12:00:00Z";
        String newAction = "User Logout";
        String newDate = "2024-04-25T12:00:00Z";

        Log log = new Log(action, date);

        log.setAction(newAction);
        log.setDate(newDate);

        assertEquals(newAction, log.getAction());
        assertEquals(newDate, log.getDate());
    }
}