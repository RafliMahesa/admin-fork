package id.ac.ui.cs.pustakaone.admin.repository;

import id.ac.ui.cs.pustakaone.admin.service.LogDeleteService;
import id.ac.ui.cs.pustakaone.admin.service.LogUpdateService;

import java.util.HashMap;

import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class AdminRepository {

    
    private RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> retrievePaymentList() {
        String url = "http://localhost:8081/shop/cart/getCarts";
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<String> retrieveUsers() {
        String url = "https://identity.pustakaone.my.id/auth/getAllUser";
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<String> updatePayment(Long idCart) {
        String url = "http://localhost:8080/all-logs";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(createJsonBody(idCart), headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> deleteReview(Long idReview){
        ObjectMapper mapper = new ObjectMapper();
        String url = "http://localhost:8080/testing";
        ResponseEntity<String> idBookResponse = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        Long idBook;
        
        try {
            HashMap<String, Long> map = mapper.readValue(idBookResponse.getBody(), new TypeReference<HashMap<String, Long>>() {});
            idBook = map.get("idBook");
    
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        String url2 = String.format("http://localhost:8080/review/%s/%s/delete", idBook, idReview);
        return restTemplate.exchange(url2, HttpMethod.DELETE, null, String.class);
    }

    public String createJsonBody(Long idCart) {
        return "{\"id\":\"" + idCart + "\"}";
    }
}