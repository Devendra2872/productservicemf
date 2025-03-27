package com.scaler.productservicemod.Services;

import com.scaler.productservicemod.Dto.FakeStoreProductDto;
import com.scaler.productservicemod.Models.Category;
import com.scaler.productservicemod.Models.Product;
import com.scaler.productservicemod.exeptions.ProductNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductServices")
public class FakeStoreProductServices implements ProductService{
    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductServices(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @org.jetbrains.annotations.NotNull
    private Product convrtfackStoretoProduct(FakeStoreProductDto fakeStoreProduct){
        Product product = new Product();
        product.setId(fakeStoreProduct.getId());
        product.setTitle(fakeStoreProduct.getTitle());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDiscription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());
        return product;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistsException {
        ResponseEntity<FakeStoreProductDto> productDto =
        restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + id ,
                FakeStoreProductDto.class
        );
        if (productDto.getStatusCode() != HttpStatusCode.valueOf(200)) {
            // log this to command line
        }

        if (productDto == null) {
            throw new ProductNotExistsException(
                    "Product with id: " + id + " doesn't exist."
            );
        }
        return convrtfackStoretoProduct(productDto.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto [] responce = restTemplate.getForObject(
                "https://fakestoreapi.com/products" ,
                FakeStoreProductDto[].class
        );
        List<Product>answer = new ArrayList<>();
        for (FakeStoreProductDto dto : responce){
            answer.add(convrtfackStoretoProduct(dto));
        }
        return answer;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDiscription(product.getDescription());
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new FakeStoreProductDto(), FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto responce = restTemplate.execute("'https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convrtfackStoretoProduct(responce);
    }

    @Override
    public Product addNewProduct(Product product) {
        Product p = new Product();
        p.setPrice(p.getPrice());
        return p;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
