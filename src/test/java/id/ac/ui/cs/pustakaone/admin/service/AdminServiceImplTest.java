package id.ac.ui.cs.pustakaone.admin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.pustakaone.admin.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.admin.model.Log;
import id.ac.ui.cs.pustakaone.admin.repository.AdminRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.eq;

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

    @Mock
    private LogUpdateBookService logUpdateBookService;

    @Mock
    private ObjectMapper objectMapperMock;
    @Mock
    private LogCreateBookService logCreateBookServiceMock;

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

    @Test
    void testCreateBookSuccessful() throws Exception {
        // Mocking the dependencies
        CreateUpdateBookDTO createBookDto = new CreateUpdateBookDTO(); // Initialize with required data
        ResponseEntity<String> createdBookResponse = new ResponseEntity<>("{\"bookId\": 123}", HttpStatus.OK);
        when(adminRepositoryMock.createBook(createBookDto)).thenReturn(createdBookResponse);

        // Mock the ObjectMapper behavior
        ObjectMapper realObjectMapper = new ObjectMapper();
        JsonNode rootNode = realObjectMapper.readTree("{\"bookId\": 123}");
        when(objectMapperMock.readTree(anyString())).thenReturn(rootNode);

        // Simulate successful log creation
        when(logCreateBookServiceMock.createLog(123L)).thenReturn(new Log());

        // Test the createBook method
        ResponseEntity<String> result = adminService.createBook(createBookDto);

        // Verify the response status and body
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("{\"bookId\": 123}", result.getBody());
    }

    @Test
    void testCreateBookFailedToProcessResponse() throws Exception {
        // Mocking the dependencies
        CreateUpdateBookDTO createBookDto = new CreateUpdateBookDTO(); // Initialize with required data
        ResponseEntity<String> createdBookResponse = new ResponseEntity<>("Invalid JSON", HttpStatus.OK);
        when(adminRepositoryMock.createBook(createBookDto)).thenReturn(createdBookResponse);

        // Mock the ObjectMapper behavior to throw an exception
        when(objectMapperMock.readTree(any(String.class))).thenThrow(new RuntimeException("Unexpected character ('I' (code 73)): was expecting double-quote to start field name"));

        // Test the createBook method
        ResponseEntity<String> result = adminService.createBook(createBookDto);

        // Verify the response status and body
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
        assertEquals("Failed to process response: Unexpected character ('I' (code 73)): was expecting double-quote to start field name", result.getBody());
    }

    @Test
    void testCreateBookUnsuccessful() throws Exception {
        // Mocking the dependencies
        CreateUpdateBookDTO createBookDto = new CreateUpdateBookDTO(); // Initialize with required data
        ResponseEntity<String> createdBookResponse = new ResponseEntity<>("{\"error\": \"Failed to create book\"}", HttpStatus.BAD_REQUEST);
        when(adminRepositoryMock.createBook(createBookDto)).thenReturn(createdBookResponse);

        // Test the createBook method
        ResponseEntity<String> result = adminService.createBook(createBookDto);

        // Verify the response status and body
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("{\"error\": \"Failed to create book\"}", result.getBody());
    }

    @Test
    void testUpdateBookSuccessful() {
        // Arrange
        Long idBook = 123L;
        CreateUpdateBookDTO updateBookDto = new CreateUpdateBookDTO(); // Initialize with required data
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Book updated successfully", HttpStatus.OK);

        when(adminRepositoryMock.updateBook(eq(idBook), any(CreateUpdateBookDTO.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminService.updateBook(idBook, updateBookDto);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Book updated successfully", result.getBody());
    }
}
