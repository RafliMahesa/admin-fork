package id.ac.ui.cs.pustakaone.admin.controller;

import id.ac.ui.cs.pustakaone.admin.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.admin.model.Log;
import id.ac.ui.cs.pustakaone.admin.service.AdminService;
import id.ac.ui.cs.pustakaone.admin.service.LogDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdminService adminServiceMock;

    @Mock
    private LogDeleteService logDeleteServiceMock;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testGetPayments() {
        String expectedResponse = "payment data";
        CompletableFuture<ResponseEntity<String>> future = CompletableFuture.completedFuture(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        when(adminServiceMock.retrievePaymentList()).thenReturn(future);

        ResponseEntity<String> response = adminController.getPayments();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testGetPayments_Exception() {
        CompletableFuture<ResponseEntity<String>> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Error"));
        when(adminServiceMock.retrievePaymentList()).thenReturn(future);

        ResponseEntity<String> response = adminController.getPayments();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void testGetUsers_Exception() {
        CompletableFuture<ResponseEntity<String>> future = new CompletableFuture<>();
        future.completeExceptionally(new RuntimeException("Error"));
        when(adminServiceMock.retrieveUsers()).thenReturn(future);

        ResponseEntity<String> response = adminController.getUsers();

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void testGetUsers() {
        String expectedResponse = "user data";
        CompletableFuture<ResponseEntity<String>> future = CompletableFuture.completedFuture(new ResponseEntity<>(expectedResponse, HttpStatus.OK));
        when(adminServiceMock.retrieveUsers()).thenReturn(future);

        ResponseEntity<String> response = adminController.getUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testGetLogs() throws Exception {
        Log log1 = new Log();
        Log log2 = new Log();
        List<Log> logs = Arrays.asList(log1, log2);

        when(logDeleteServiceMock.getAllLog()).thenReturn(logs);


        mockMvc.perform(get("/admin/logs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testUpdatePayment() throws Exception {
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Payment Updated", HttpStatus.OK);
        when(adminServiceMock.updatePayment(123L)).thenReturn(expectedResponse);

        mockMvc.perform(post("/admin/update-payment")
                        .contentType("application/json")
                        .content("{\"id\":\"123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Payment Updated"));
    }

    @Test
    void testDeleteReview() throws Exception {
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Review Deleted", HttpStatus.OK);
        when(adminServiceMock.deleteReview(456L)).thenReturn(expectedResponse);

        mockMvc.perform(post("/admin/delete-review")
                        .contentType("application/json")
                        .content("{\"id\":\"456\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Review Deleted"));
    }

    @Test
    void testCreateBookSuccessful() throws Exception {

        String requestBody = "{\"title\":\"Test Book\", \"author\":\"Test Author\"}";
        String responseBody = "{\"bookId\": 123}";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);

        when(adminServiceMock.createBook(any(CreateUpdateBookDTO.class))).thenReturn(responseEntity);

        mockMvc.perform(post("/admin/create-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(responseBody));
    }

    @Test
    void testCreateBookFailure() throws Exception {

        String requestBody = "{\"title\":\"Test Book\", \"author\":\"Test Author\"}";
        String responseBody = "Failed to create book: Connection refused";
        ResponseEntity<String> responseEntity = new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

        when(adminServiceMock.createBook(any(CreateUpdateBookDTO.class))).thenReturn(responseEntity);


        mockMvc.perform(post("/admin/create-book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(responseBody));
    }

    @Test
    void testUpdateBookSuccessful() throws Exception {

        Long idBook = 123L;
        String updateBookDtoJson = "{\"title\":\"Updated Title\"}"; // Adjust the JSON according to the fields in CreateUpdateBookDTO
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Book updated successfully", HttpStatus.OK);

        when(adminServiceMock.updateBook(eq(idBook), any(CreateUpdateBookDTO.class))).thenReturn(expectedResponse);


        mockMvc.perform(put("/admin/update-book/{id}", idBook)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBookDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Book updated successfully"));
    }

    @Test
    void testUpdateBookFailure() throws Exception {

        Long idBook = 123L;
        String updateBookDtoJson = "{\"title\":\"Updated Title\"}"; // Adjust the JSON according to the fields in CreateUpdateBookDTO
        String errorMessage = "Connection refused";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Failed to update book: " + errorMessage, HttpStatus.BAD_REQUEST);

        when(adminServiceMock.updateBook(eq(idBook), any(CreateUpdateBookDTO.class)))
                .thenReturn(expectedResponse);


        mockMvc.perform(put("/admin/update-book/{id}", idBook)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateBookDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Failed to update book: " + errorMessage));
    }
}

