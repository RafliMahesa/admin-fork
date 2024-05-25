package id.ac.ui.cs.pustakaone.admin.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.pustakaone.admin.dto.CreateUpdateBookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.pustakaone.admin.repository.AdminRepository;

import java.util.concurrent.CompletableFuture;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private LogDeleteService logDelService;

    @Autowired
    private LogUpdateService logUpService;

    @Autowired
    private LogCreateBookService logCreateBookService;

    @Autowired
    private LogUpdateBookService logUpdateBookService;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public CompletableFuture<ResponseEntity<String>> retrievePaymentList() {
        return adminRepository.retrievePaymentList();
    }

    @Override
    public CompletableFuture<ResponseEntity<String>> retrieveUsers(){
        return adminRepository.retrieveUsers();
    }

    @Override
    public ResponseEntity<String> updatePayment(Long idCart){
        logUpService.createLog(idCart);
        return adminRepository.updatePayment(idCart);
    }

    @Override
    public ResponseEntity<String> deleteReview(Long idReview){
        logDelService.createLog(idReview);
        return adminRepository.deleteReview(idReview);
    }

    @Override
    public ResponseEntity<String> createBook(CreateUpdateBookDTO createBookDto) {
        ResponseEntity<String> createdBookResponse = adminRepository.createBook(createBookDto);
        if (createdBookResponse.getStatusCode() == HttpStatus.OK) {
            try {
                JsonNode root = objectMapper.readTree(createdBookResponse.getBody());
                Long bookId = root.path("bookId").asLong();
                logCreateBookService.createLog(bookId);
            } catch (Exception e) {
                return new ResponseEntity<>("Failed to process response: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return createdBookResponse;
    }

    @Override
    public ResponseEntity<String> updateBook(Long idBook, CreateUpdateBookDTO updateBookDto){
        logUpdateBookService.createLog(idBook);
        return adminRepository.updateBook(idBook, updateBookDto);
    }
}