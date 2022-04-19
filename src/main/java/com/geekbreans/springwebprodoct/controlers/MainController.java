package com.geekbreans.springwebprodoct.controlers;


import com.geekbreans.springwebprodoct.data.Product;
import com.geekbreans.springwebprodoct.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;


    @GetMapping("/products")
    public String showStudentsPage(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_page";
    }


    // поиск продукта по id
    @GetMapping("/products/{id}")
    public String showStudentPage(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product_info_page";
    }

}
