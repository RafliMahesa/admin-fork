package id.ac.ui.cs.pustakaone.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LogDeleteServiceTest {

    @Test
    void testAction() {
        LogDeleteService logDeleteService = new LogDeleteService();
        String id = "8";
        String expectedAction = "Review dengan id 8 berhasil dihapus";
        assertEquals(expectedAction, logDeleteService.action(id));
    }
}

