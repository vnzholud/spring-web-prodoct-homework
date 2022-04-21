package com.geekbreans.springwebprodoct.controlers;


import com.geekbreans.springwebprodoct.data.Product;
import com.geekbreans.springwebprodoct.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductRepository productRepository;


    @GetMapping("/products")
    public String showProductsPage(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_page";
    }


    // поиск продукта по id добавляем на вторую ветку в удаленном репозитории
    @GetMapping("/products/{id}")
    public String showProductsPage(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product_info_page";
    }

    @GetMapping("/products_show")
    public String showPage() {

        return "simple_form";
    }
    @GetMapping("/product_add")
    public String addProduct(@RequestParam Long id, @RequestParam String name, @RequestParam Long price) {

        Product product = new Product(id, name, price);
        productRepository.add(product);

        return "redirect:/products";
    }

}
