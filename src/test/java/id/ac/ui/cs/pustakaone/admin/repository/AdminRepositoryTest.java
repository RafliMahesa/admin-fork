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
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("User List", HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(expectedResponse);

        ResponseEntity<String> result = adminRepository.retrieveUsers();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("User List", result.getBody());
    }

    @Test
    public void testDeleteReview() {
        Long reviewId = 5L;
        String url = String.format("http://localhost:8081/review/%s/delete", reviewId);
        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Review Deleted", HttpStatus.OK);

        when(restTemplate.exchange(url, HttpMethod.DELETE, null, String.class))
                .thenReturn(expectedResponse);

        ResponseEntity<String> result = adminRepository.deleteReview(reviewId);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Review Deleted", result.getBody());
    }

    @Test
    public void testUpdatePayment() {
        Long idCart = 123L;
        String expectedResponseBody = "{\"status\":\"success\"}";
        ResponseEntity<String> expectedResponse = new ResponseEntity<>(expectedResponseBody, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(), any(), any(Class.class))).thenReturn(expectedResponse);

        ResponseEntity<String> result = adminRepository.updatePayment(idCart);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResponseBody, result.getBody());
    }


    @Test
    public void testCreateJsonBody() {
        Long idCart = 123L;
        String expectedJsonBody = "{\"idCart\":" + 123 + "}";

        String result = adminRepository.createJsonBody(idCart);

        assertEquals(expectedJsonBody, result);
    }
}

