package id.ac.ui.cs.pustakaone.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LogUpdateServiceTest {

    @Test
    void testAction() {
        LogUpdateService logUpdateService = new LogUpdateService();
        String id = "10";
        String expectedAction = "Cart dengan id 10 berhasil diupdate";
        assertEquals(expectedAction, logUpdateService.action(id));
    }
}


