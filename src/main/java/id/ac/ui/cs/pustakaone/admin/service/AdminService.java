package id.ac.ui.cs.pustakaone.admin.service;

import org.springframework.http.ResponseEntity;


public interface AdminService {
    ResponseEntity<String> retrievePaymentList();
    ResponseEntity<String> retrieveUsers();
    ResponseEntity<String> updatePayment(String idCart);
    ResponseEntity<String> deleteReview(String idReview);
}