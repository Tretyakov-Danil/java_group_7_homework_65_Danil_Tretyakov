package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.ProductDto;
import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public Page<ProductDto> getProducts(Pageable pageable){
        Page<Product> products = this.productService.findAll(pageable);
        return products.map(product -> modelMapper.map(product, ProductDto.class));
    }

    @GetMapping("/{productId}")
    public ProductDto getInfoAboutProduct(@PathVariable Integer productId) throws FileNotFoundException{
        Optional<Product> product = this.productService.getProductInfo(productId);
        if (product.isEmpty()) throw new FileNotFoundException(String.format( "Product with productId %s not found", productId));
        return modelMapper.map(product.get(), ProductDto.class);
    }

    @GetMapping("/{name}")
    public List<ProductDto> findProductsByName(@PathVariable String name){
        return this.productService.findAllByName(name).stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

    // this method had to find products by the prices up to highest
//    @GetMapping("/{price}")
//    public List<ProductDto> findProductsByPrice(@PathVariable String name){
//        return this.productService.findAllByName(name).stream()
//                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
//    }

    @GetMapping("/{type}")
    public List<ProductDto> findProductsByCategory(@PathVariable String type){
        return this.productService.findAllByCategory(type).stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
    }

}
