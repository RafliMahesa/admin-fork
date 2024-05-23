package id.ac.ui.cs.pustakaone.admin.repository;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AdminRepository {

    
    private RestTemplate restTemplate = new RestTemplate();

    @Async
    public CompletableFuture<ResponseEntity<String>> retrievePaymentList() {
        String url = "http://localhost:8081/shop/cart/getCarts";
        return CompletableFuture.supplyAsync(() -> restTemplate.exchange(url, HttpMethod.GET, null, String.class));
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> retrieveUsers() {
        String url = "https://identity.pustakaone.my.id/auth/getAllUser";
        return CompletableFuture.supplyAsync(() -> restTemplate.exchange(url, HttpMethod.GET, null, String.class));
    }

    public ResponseEntity<String> updatePayment(Long idCart) {
        String url = "http://localhost:8081/shop/cart/finishPayments";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(createJsonBody(idCart), headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> deleteReview(Long idReview){
        String url2 = String.format("http://localhost:8081/review/%s/delete", idReview);
        return restTemplate.exchange(url2, HttpMethod.DELETE, null, String.class);
    }

    public String createJsonBody(Long idCart) {
        return "{\"idCart\":" + idCart + "}";
    }
}