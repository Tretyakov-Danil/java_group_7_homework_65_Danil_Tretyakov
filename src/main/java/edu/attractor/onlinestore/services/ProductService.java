package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.dtos.FilterDto;
import edu.attractor.onlinestore.dtos.ProductDto;
import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.enums.ProductType;
import edu.attractor.onlinestore.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductInfo(Integer productId) {
        return this.productRepository.findById(productId);
    }

    public Page<Product> findAll(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public List<Product> findAllWithFilter(FilterDto filter) {
        List<Product> products = new ArrayList<>();

        if (filter.getMaxAmount() != 0){
            products.addAll(this.productRepository.findAllByAmountIsLessThan(filter.getMaxAmount()));
        }

        if (!"".equals(filter.getName())) {
            products.addAll(this.productRepository.findAllByNameContaining(filter.getName()));
            if ("".equals(filter.getBrand()) || "".equals(filter.getType())) return products;
            return products.stream()
                    .takeWhile(product -> product.getType().toString().equals(filter.getType()))
                    .takeWhile(product -> product.getBrand().getName().equals(filter.getBrand()))
                    .collect(Collectors.toList());
        }

        if (!"".equals(filter.getBrand())){
            products.addAll(this.productRepository.findAllByBrand_Name(filter.getBrand()));
            if ("".equals(filter.getName()) || "".equals(filter.getType())) return products;
            return products.stream()
                    .takeWhile(product -> product.getType().toString().equals(filter.getType()))
                    .collect(Collectors.toList());
        }

        if (!"".equals(filter.getType())){
            products.addAll(this.productRepository.findAllByType(ProductType.valueOf(filter.getType())));
            if ("".equals(filter.getBrand()) || "".equals(filter.getName())) return products;
            return products.stream()
                    .takeWhile(product -> product.getBrand().getName().equals(filter.getBrand()))
                    .collect(Collectors.toList());
        }

        return products;
    }

    public void changeAmountOfProduct(int productId, int amount) {
        this.productRepository.changeAmount(productId, amount);
    }

    public void addAmountToProduct(int productId, int amount) {
        this.productRepository.addAmount(productId, amount);
    }
}
