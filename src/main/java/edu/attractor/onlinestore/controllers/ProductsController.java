package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.FilterDto;
import edu.attractor.onlinestore.dtos.OrderDto;
import edu.attractor.onlinestore.dtos.ProductDto;
import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.services.ClientService;
import edu.attractor.onlinestore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductService productService;
    private final ModelMapper modelMapper = new ModelMapper();
    private final ClientService clientService;

    @GetMapping
    public String getProducts(Model model, @PageableDefault(size = 4) Pageable pageable, Authentication auth, HttpSession session){
        if (session.getAttribute("cart") == null){
            session.setAttribute("cart", new ArrayList<>());
        }
        Optional<Authentication> authOptional = Optional.ofNullable(auth);
        this.clientService.isClientOnline(authOptional, model);
        Page<Product> products = this.productService.findAll(pageable);
        model.addAttribute("products", products.getContent().stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()));
        model.addAttribute("pages", products.getPageable());
        return "products";
    }

    @GetMapping("/{productId}")
    public String getInfoAboutProduct(Model model, @PathVariable Integer productId, Authentication auth) throws FileNotFoundException{
        Optional<Authentication> authOptional = Optional.ofNullable(auth);
        this.clientService.isClientOnline(authOptional, model);
        Optional<Product> product = this.productService.getProductInfo(productId);
        if (product.isEmpty()) throw new FileNotFoundException(String.format( "Product with productId %s not found", productId));
        model.addAttribute("product", modelMapper.map(product.get(), ProductDto.class));
        return "product_info";
    }

    @GetMapping("/filter")
    public String getPageForSearch(Model model, Authentication auth){
        Optional<Authentication> authOptional = Optional.ofNullable(auth);
        this.clientService.isClientOnline(authOptional, model);
        model.addAttribute("products", List.of());
        model.addAttribute("firstSearch", true);
        return "search";
    }

    @GetMapping("/filter/result")
    public String findWithFilter(Model model,@ModelAttribute("filter") FilterDto filter, Authentication auth){
        Optional<Authentication> authOptional = Optional.ofNullable(auth);
        this.clientService.isClientOnline(authOptional, model);
        List<ProductDto> products = this.productService.findAllWithFilter(filter).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        model.addAttribute("products", products);
        model.addAttribute("firstSearch", false);
        return "search";
    }


}
