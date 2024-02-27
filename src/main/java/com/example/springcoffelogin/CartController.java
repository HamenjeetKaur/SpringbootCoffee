package com.example.springcoffelogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/cart")

public class CartController {
    @GetMapping("/")
    public String viewCart(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", calculateTotal(cartItems));
        return "cart";
    }



    @PostMapping("/remove")
    public String removeFromCart(@RequestParam int index, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>)
                session.getAttribute("cartItems");
        if (cartItems != null && index >= 0 && index < cartItems.size()) {
            cartItems.remove(index);
        }
        return "redirect:/cart/";
    }


    private double calculateTotal(List<CartItem> cartItems) {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getPp();
        }
        return total;
    }


    @GetMapping("/item")
    public String itemCart(HttpSession session, Model model) {
        List<CartItem> cartItems = (List<CartItem>)
                session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", calculateTotal(cartItems));
        return "cart-item";
    }


}
