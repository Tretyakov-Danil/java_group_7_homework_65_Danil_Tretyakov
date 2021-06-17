package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.FilterDto;
import edu.attractor.onlinestore.dtos.ProductDto;
import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model, @PageableDefault(size = 4) Pageable pageable){
        Page<Product> products = this.productService.findAll(pageable);
        model.addAttribute("products", products.getContent().stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList()));
        model.addAttribute("pages", products.getPageable());
        return "products";
    }

    @GetMapping("/{productId}")
    public String getInfoAboutProduct(Model model, @PathVariable Integer productId) throws FileNotFoundException{
        Optional<Product> product = this.productService.getProductInfo(productId);
        if (product.isEmpty()) throw new FileNotFoundException(String.format( "Product with productId %s not found", productId));
        model.addAttribute("product", modelMapper.map(product.get(), ProductDto.class));
        return "product_info";
    }

    @GetMapping("/filter")
    public String getPageForSearch(Model model){
        model.addAttribute("products", List.of());
        return "search";
    }

    @GetMapping("/filter/result")
    public String findWithFilter(Model model,@ModelAttribute("filter") FilterDto filter){
        List<ProductDto> products = this.productService.findAllWithFilter(filter).stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        model.addAttribute("products", products);
        return "search";
    }


    // this method had to find products by the prices up to highest
//    @GetMapping("/{price}")
//    public List<ProductDto> findProductsByPrice(@PathVariable String name){
//        return this.productService.findAllByName(name).stream()
//                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
//    }

}
