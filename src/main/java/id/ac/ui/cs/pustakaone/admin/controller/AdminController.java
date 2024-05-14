package id.ac.ui.cs.pustakaone.admin.controller;
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
        return service.retrievePaymentList();
    }

    @GetMapping("/users")
    public ResponseEntity<String> getUsers() {
        return service.retrieveUsers();
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
}
