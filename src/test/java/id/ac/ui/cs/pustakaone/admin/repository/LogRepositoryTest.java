package id.ac.ui.cs.pustakaone.admin.repository;

import id.ac.ui.cs.pustakaone.admin.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LogRepositoryTest {

    private LogRepository logRepository;

    @BeforeEach
    void setUp() {
        logRepository = new LogRepository();
    }

    @Test
    void testCreateLog() {
        Log log = new Log("Review dengan id 6 berhasil dihapus", "2024-04-24T12:00:00Z");
        Log createdLog = logRepository.createLog(log);
        assertNotNull(createdLog);
        assertEquals(log.getAction(), createdLog.getAction());
        assertEquals(log.getDate(), createdLog.getDate());
    }

    @Test
    void testAllLog() {
        Log log = new Log("Review dengan id 8 berhasil dihapus", "2024-04-24T12:00:00Z");
        Log log2 = new Log("Review dengan id 20 berhasil dihapus", "2024-04-24T12:00:00Z");
        logRepository.createLog(log);
        logRepository.createLog(log2);
        List<Log> allLogs = logRepository.allLog();
        assertEquals(2, allLogs.size());
        assertTrue(allLogs.contains(log));
        assertTrue(allLogs.contains(log2));
    }
}
