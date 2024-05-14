package id.ac.ui.cs.pustakaone.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
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

        String url = "http://localhost:8081/shop/cart/getCarts";
        when(restTemplate.exchange(url, HttpMethod.GET, null, String.class))
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
        Long idCart = Long.valueOf(123);
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
    public void deleteReviewTest_Success() {
        // Arrange
        Long idReview = 1L;
        String mockResponse = "{\"idBook\":123}";
        ResponseEntity<String> mockEntity = new ResponseEntity<>(mockResponse, HttpStatus.OK);
        when(restTemplate.exchange("http://localhost:8080/testing", HttpMethod.GET, null, String.class))
            .thenReturn(mockEntity);
        when(restTemplate.exchange("http://localhost:8080/review/123/1/delete", HttpMethod.DELETE, null, String.class))
            .thenReturn(new ResponseEntity<>("Deleted", HttpStatus.OK));

        // Act
        ResponseEntity<String> response = adminRepository.deleteReview(idReview);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());
    }

    @Test
    public void deleteReviewTest_Failure() {
        // Arrange
        Long idReview = 1L;
        when(restTemplate.exchange("http://localhost:8080/testing", HttpMethod.GET, null, String.class))
            .thenReturn(new ResponseEntity<>(HttpStatus.BAD_REQUEST));

        // Act
        ResponseEntity<String> response = adminRepository.deleteReview(idReview);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testCreateJsonBody() {
        // Arrange
        Long idCart = Long.valueOf(123);
        String expectedJsonBody = "{\"idCart\":" + 123 + "}";

        // Act
        String result = adminRepository.createJsonBody(idCart);

        // Assert
        assertEquals(expectedJsonBody, result);
    }
}

