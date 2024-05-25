package id.ac.ui.cs.pustakaone.admin.controller;
import id.ac.ui.cs.pustakaone.admin.dto.CreateUpdateBookDTO;
import id.ac.ui.cs.pustakaone.admin.service.AdminService;
import id.ac.ui.cs.pustakaone.admin.service.LogDeleteService;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.pustakaone.admin.model.*;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;
    private final LogDeleteService logService;

    public AdminController(AdminService service, LogDeleteService logService) {
        this.service = service;
        this.logService = logService;
    }

    @GetMapping("/payments")
    public ResponseEntity<String> getPayments() {
        ResponseEntity<String> result;
        try {
            result = service.retrievePaymentList().get();
        } catch (Exception e) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        ResponseEntity<String> result;
        try {
            result = service.retrieveUsers().get();
        } catch (Exception e) {
            result = ResponseEntity.badRequest().build();
        }
        return result;
    }

    @GetMapping("/logs")
    public List<Log> getLogs() {
        return logService.getAllLog();
    }

    @PostMapping("/update-payment")
    public ResponseEntity<String> updatePayment(@RequestBody HashMap<String, String> body) {
        Long idCart = Long.valueOf(body.get("id"));
        return service.updatePayment(idCart);
    }

    @PostMapping("/delete-review")
    public ResponseEntity<String> deleteReview(@RequestBody HashMap<String, String> body) {
        Long idReview = Long.valueOf(body.get("id"));
        return service.deleteReview(idReview);
    }

    @PostMapping("/create-book")
    public ResponseEntity<String> createBook(@RequestBody CreateUpdateBookDTO createBookDto) {
        return service.createBook(createBookDto);
    }
}
