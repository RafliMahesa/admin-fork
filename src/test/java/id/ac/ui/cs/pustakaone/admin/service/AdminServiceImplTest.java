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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SpringBootTest
class AdminServiceImplTest {

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
    void testRetrievePaymentList() throws ExecutionException, InterruptedException {
        String expectedResponse = "payment list";
        CompletableFuture<ResponseEntity<String>> mockedFuture = CompletableFuture.completedFuture(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        when(adminRepositoryMock.retrievePaymentList()).thenReturn(mockedFuture);

        CompletableFuture<ResponseEntity<String>> future = adminService.retrievePaymentList();
        ResponseEntity<String> response = future.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testRetrieveUsers() throws ExecutionException, InterruptedException {
        String expectedResponse = "user list";
        CompletableFuture<ResponseEntity<String>> mockedFuture = CompletableFuture.completedFuture(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        when(adminRepositoryMock.retrieveUsers()).thenReturn(mockedFuture);

        CompletableFuture<ResponseEntity<String>> future = adminService.retrieveUsers();
        ResponseEntity<String> response = future.get();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testUpdatePayment() {
        Long idCart = Long.valueOf(123);
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Payment Updated", HttpStatus.OK);
        when(adminRepositoryMock.updatePayment(idCart)).thenReturn(expectedResponse);

        ResponseEntity<String> result = adminService.updatePayment(idCart);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Payment Updated", result.getBody());
    }

    @Test
    void testDeleteReview() {
        Long idReview = Long.valueOf(456);
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Review Deleted", HttpStatus.OK);
        when(adminRepositoryMock.deleteReview(idReview)).thenReturn(expectedResponse);

        ResponseEntity<String> result = adminService.deleteReview(idReview);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Review Deleted", result.getBody());
    }
}
