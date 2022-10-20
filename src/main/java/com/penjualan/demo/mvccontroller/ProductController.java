package com.penjualan.demo.mvccontroller;

import com.penjualan.demo.dto.product.ProductGridDTO;
import com.penjualan.demo.dto.transaction.CartDTO;
import com.penjualan.demo.dto.transaction.CartDetailDTO;
import com.penjualan.demo.service.ProductService;
import com.penjualan.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private TransactionService transactionService;

    private CartDTO cart = new CartDTO();

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue="") String name,
                        Model model){


        List<ProductGridDTO> grid = productService.getProductGrid(page, name);
        long totalPages = productService.getTotalPages(name);
        model.addAttribute("grid", grid);
        model.addAttribute("currentPage", page);
        model.addAttribute("name", name);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("breadCrumbs", "Product Index");
        model.addAttribute("cart", cart);



        return "product/product-index";
    }

    @GetMapping("/buy")
    public String buyProduct(@RequestParam String code,
                             Model model){

        if (cart.getDetail().isEmpty()){
            productService.addProductToCart(code, cart);
        }
        else {
            if(cart.getDetail().stream()
                    .noneMatch(cartDetailDTO -> cartDetailDTO.getProductCode().equals(code))){
                productService.addProductToCart(code, cart);
            } else{
                productService.addProductQuantityToCart(code, cart);
            }
        }

        return "redirect:/";
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        cart.setTotal(new BigDecimal(0));

        if(cart.getDetail().isEmpty()){
            model.addAttribute("breadCrumbs", "Your Cart is Empty");
            return "transaction/empty-cart";
        }

        List<CartDetailDTO> dto = cart.getDetail();
        double totalPrice = 0;
        for (CartDetailDTO details: dto) {
            totalPrice = totalPrice + details.getSubTotal().doubleValue();
        }
        cart.setTotal(BigDecimal.valueOf(totalPrice));

        model.addAttribute("cartDetail", dto);
        model.addAttribute("cart", cart);
        model.addAttribute("breadCrumbs", "Your Cart");

        return "transaction/checkout-form";
    }

    @GetMapping("/emptyCart")
    public String emptyCart(Model model){

        cart.getDetail().clear();
        cart.setTotal(new BigDecimal(0));

        return "redirect:/product/checkout";
    }

    @GetMapping("/confirm")
    public String confirmCheckout(Model model, HttpServletRequest request){

        String currentUser = request.getUserPrincipal().getName();

        transactionService.saveTransaction(cart, currentUser);

        cart = new CartDTO();

        return "redirect:/";
    }

}
