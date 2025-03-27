package com.scaler.productservicemod.Services;

import com.scaler.productservicemod.Models.Product;
import com.scaler.productservicemod.exeptions.ProductNotExistsException;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long id) throws ProductNotExistsException;
    List<Product>getAllProducts();
    Product updateProduct(Long id, Product product);
    Product replaceProduct(Long id, Product product);
    Product addNewProduct(Product product);
    boolean deleteProduct(Long id);
}
