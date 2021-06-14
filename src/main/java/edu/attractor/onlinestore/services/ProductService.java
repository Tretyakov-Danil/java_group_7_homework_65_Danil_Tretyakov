package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.dtos.ProductDto;
import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.enums.ProductType;
import edu.attractor.onlinestore.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Page<Product> findAllByName(String name, Pageable pageable) {
        return this.productRepository.findAllByNameContaining(name, pageable);
    }

//    public List<Product> findAllByPrice(String name) {
//        return this.productRepository.findAllByPrice(name);
//    }

    public Page<Product> findAllByCategory(ProductType type, Pageable pageable) {
        return this.productRepository.findAllByType(type, pageable);
    }

    public Page<Product> findAllByBrand(int brandId, Pageable pageable) {
        return this.productRepository.findAllByBrand_Id(brandId, pageable);
    }
}
