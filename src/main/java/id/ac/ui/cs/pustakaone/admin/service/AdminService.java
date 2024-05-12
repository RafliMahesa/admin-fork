package id.ac.ui.cs.pustakaone.admin.service;

import org.springframework.http.ResponseEntity;


public interface AdminService {
    ResponseEntity<String> retrievePaymentList();
    ResponseEntity<String> retrieveUsers();
    ResponseEntity<String> updatePayment(Long idCart);
    ResponseEntity<String> deleteReview(Long idReview);
}