package com.scaler.productservicemod.Dto;

import com.scaler.productservicemod.Models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private double price;
    private String category;
    private String discription;
    private String image;

}
