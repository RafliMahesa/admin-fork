package id.ac.ui.cs.pustakaone.admin.controller;
import id.ac.ui.cs.pustakaone.admin.service.AdminService;
import id.ac.ui.cs.pustakaone.admin.service.LogDeleteService;
import org.springframework.web.bind.annotation.*;
import id.ac.ui.cs.pustakaone.admin.model.*;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
public class AdminController {

    @Autowired
    private AdminService service;

    @Autowired
    private LogDeleteService logService;

    @RequestMapping(value = "/admin/payments", method = RequestMethod.GET)
    public ResponseEntity<String> getPayments() {
        return service.retrievePaymentList();
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<String> getUsers() {
        return service.retrieveUsers();
    }

    @RequestMapping(value = "/admin/logs", method = RequestMethod.GET)
    public List<Log> getLogs() {
        return logService.getAllLog();
    }

    @RequestMapping(value = "/admin/update-payment", method = RequestMethod.POST)
    public ResponseEntity<String> updatePayment(@RequestBody HashMap<String, String> body) {
        String idCart = body.get("id");
        return service.updatePayment(idCart);
    }

    @RequestMapping(value = "/admin/delete-review", method = RequestMethod.POST)
    public ResponseEntity<String> deleteReview(@RequestBody HashMap<String, String> body) {
        String idReview = body.get("id");
        return service.deleteReview(idReview);
    }
}