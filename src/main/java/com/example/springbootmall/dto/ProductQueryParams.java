package com.example.springbootmall.dto;

import com.example.springbootmall.constant.ProductCategory;
import lombok.Data;

@Data
public class ProductQueryParams {
    private ProductCategory category;
    private String search;
}
