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

    public Collection<Product> findAllWithFilter(FilterDto filter) {
        Set<Product> products = new HashSet<>();
        if (filter.getName() != null){
            products.addAll(this.productRepository.findAllByNameContaining(filter.getName()));
        }

        if (filter.getBrand()!= null){
            products.addAll(this.productRepository.findAllByBrand_Name(filter.getBrand()));
        }

        if (filter.getType() != null){
            products.addAll(this.productRepository.findAllByType(filter.getType()));
        }

        if (filter.getMaxAmount() != 0){
            products.addAll(this.productRepository.findAllByAmountIsLessThan(filter.getMaxAmount()));
        }

        return products;
    }
}
