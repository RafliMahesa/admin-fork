package id.ac.ui.cs.pustakaone.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import id.ac.ui.cs.pustakaone.admin.repository.AdminRepository;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class AdminRepositoryTest {

    @Mock
    public RestTemplate restTemplate;

    @InjectMocks
    private AdminRepository adminRepository;

    @Test
    public void testRetrievePaymentList() {
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Payment List", HttpStatus.OK);

        when(restTemplate.exchange("http://localhost:8080/all-cart", HttpMethod.GET, null, String.class))
                .thenReturn(expectedResponse);

        ResponseEntity<String> result = adminRepository.retrievePaymentList();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Payment List", result.getBody());
    }

    @Test
    public void testRetrieveUsers() {
        // Arrange
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("User List", HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminRepository.retrieveUsers();

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("User List", result.getBody());
    }


    @Test
    public void testUpdatePayment() {
        // Arrange
        String idCart = "123";
        String expectedResponseBody = "{\"status\":\"success\"}";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(expectedResponseBody, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminRepository.updatePayment(idCart);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponseBody, result.getBody());
    }

    @Test
    public void testDeleteReview() {
        // Arrange
        String idReview = "456";
        String expectedResponseBody = "{\"status\":\"success\"}";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(expectedResponseBody, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> result = adminRepository.deleteReview(idReview);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponseBody, result.getBody());
    }

    @Test
    public void testCreateJsonBody() {
        // Arrange
        String idCart = "123";
        String expectedJsonBody = "{\"id\":\"123\"}";

        // Act
        String result = adminRepository.createJsonBody(idCart);

        // Assert
        assertEquals(expectedJsonBody, result);
    }
}

