package id.ac.ui.cs.pustakaone.admin.model;

import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String id;
    @Setter
    private int totalPrice;
    @Getter
    private List<BookCart> bookCarts;

    public Cart(String idCart) {
        this.id = idCart;
        this.bookCarts = new ArrayList<>();
    }

}
