package com.example.springcoffelogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/cart")

public class CartController {


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);

            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
            int totalQuantity = cartItems != null ? cartItems.stream().mapToInt(CartItem::getQuantity).sum() : 0;
            model.addAttribute("totalQuantity", totalQuantity);

            return "dashboard";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/viewcart")
    public String viewCart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }

        int totalQuantity = cartItems.stream().mapToInt(CartItem::getQuantity).sum();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalQuantity", totalQuantity);
        model.addAttribute("total", calculateTotal(cartItems));
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam String item, @RequestParam double price, @RequestParam String image, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
            session.setAttribute("cartItems", cartItems);
        }

        CartItem existingItem = cartItems.stream().filter(ci -> ci.getP_name().equals(item)).findFirst().orElse(null);
        if (existingItem != null) {
            existingItem.incrementQuantity();
        } else {
            cartItems.add(new CartItem(item, price, image));
        }

        return "redirect:/cart/viewcart";
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
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", calculateTotal(cartItems));
        return "cart";
    }

    @PostMapping("/clear")
    public String clearCart(HttpSession session) {
        session.removeAttribute("cartItems");
        return "redirect:/cart/viewcart";
    }

    @PostMapping("/increase")
    public String increaseQuantity(@RequestParam int index, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems != null && index >= 0 && index < cartItems.size()) {
            cartItems.get(index).incrementQuantity();
        }
        return "redirect:/cart/viewcart";
    }

    @PostMapping("/decrease")
    public String decreaseQuantity(@RequestParam int index, HttpSession session) {
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
        if (cartItems != null && index >= 0 && index < cartItems.size()) {
            CartItem item = cartItems.get(index);
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                cartItems.remove(index);
            }
        }
        return "redirect:/cart/viewcart";
    }


    @PostMapping("/pay")
    public String pay(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);

            List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cartItems");
            int totalQuantity = cartItems != null ? cartItems.stream().mapToInt(CartItem::getQuantity).sum() : 0;
            model.addAttribute("totalQuantity", totalQuantity);
            return "pay";
        } else {
            return "redirect:/";
        }
    }


}
