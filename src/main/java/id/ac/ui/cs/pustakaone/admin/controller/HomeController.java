package id.ac.ui.cs.pustakaone.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class HomeController {

    @GetMapping("/")
    public String getHello() {
        return "Hello admin!";
    }

    @GetMapping("/admin/payments")
    public String getPayments() {
        return "Hello admin!";
    } 

    @PostMapping("/admin/update-payment")
    public String updatePayment() {
        return "Hello admin!";
    } 

    @PostMapping("/admin/delete-review")
    public String deleteReview() {
        return "Hello admin!";
    } 

    @GetMapping("/admin/users")
    public String getUsers() {
        return "Hello admin!";
    } 
}