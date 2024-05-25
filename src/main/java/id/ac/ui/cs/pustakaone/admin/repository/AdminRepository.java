package id.ac.ui.cs.pustakaone.admin.repository;

import java.util.concurrent.CompletableFuture;

import id.ac.ui.cs.pustakaone.admin.dto.CreateUpdateBookDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class AdminRepository {

    
    private RestTemplate restTemplate = new RestTemplate();

    @Value("${bookshop.url}")
    private String BOOKSHOP_URL;

    @Async
    public CompletableFuture<ResponseEntity<String>> retrievePaymentList() {
        String url = BOOKSHOP_URL + "/shop/cart/getCarts";
        return CompletableFuture.supplyAsync(() -> restTemplate.exchange(url, HttpMethod.GET, null, String.class));
    }

    @Async
    public CompletableFuture<ResponseEntity<String>> retrieveUsers() {
        String url = BOOKSHOP_URL + "/auth/getAllUser";
        return CompletableFuture.supplyAsync(() -> restTemplate.exchange(url, HttpMethod.GET, null, String.class));
    }

    public ResponseEntity<String> updatePayment(Long idCart) {
        String url = BOOKSHOP_URL + "/shop/cart/finishPayments";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(createJsonBody(idCart), headers);

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    public ResponseEntity<String> deleteReview(Long idReview){
        String url2 = String.format("%s/review/%s/delete", BOOKSHOP_URL, idReview);
        return restTemplate.exchange(url2, HttpMethod.DELETE, null, String.class);
    }

    public String createJsonBody(Long idCart) {
        return "{\"idCart\":" + idCart + "}";
    }

    public ResponseEntity<String> createBook(CreateUpdateBookDTO createBookDto) {
        String url = BOOKSHOP_URL + "/book";

        try {
            return restTemplate.postForEntity(url, createBookDto, String.class);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create book: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateBook(Long bookId, CreateUpdateBookDTO updateBookDto) {
        return null;
    }
}