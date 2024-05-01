package id.ac.ui.cs.pustakaone.admin.service;

import id.ac.ui.cs.pustakaone.admin.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepositoryMock;

    @Mock
    private LogDeleteService logDeleteServiceMock;

    @Mock
    private LogUpdateService logUpdateServiceMock;

    @InjectMocks
    private AdminServiceImpl adminService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testRetrievePaymentList() {
        // Arrange
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Payment List", HttpStatus.OK);
        when(adminRepositoryMock.retrievePaymentList()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminService.retrievePaymentList();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Payment List", result.getBody());
    }

    @Test
    public void testRetrieveUsers() {
        // Arrange
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("User List", HttpStatus.OK);
        when(adminRepositoryMock.retrieveUsers()).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminService.retrieveUsers();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("User List", result.getBody());
    }

    @Test
    public void testUpdatePayment() {
        // Arrange
        String idCart = "123";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Payment Updated", HttpStatus.OK);
        when(adminRepositoryMock.updatePayment(idCart)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminService.updatePayment(idCart);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Payment Updated", result.getBody());
    }

    @Test
    public void testDeleteReview() {
        // Arrange
        String idReview = "456";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Review Deleted", HttpStatus.OK);
        when(adminRepositoryMock.deleteReview(idReview)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminService.deleteReview(idReview);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Review Deleted", result.getBody());
    }
}
