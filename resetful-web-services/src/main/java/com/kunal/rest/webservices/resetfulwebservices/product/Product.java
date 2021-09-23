package com.kunal.rest.webservices.resetfulwebservices.product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
public class Product {
    @NotNull private Integer id;
    private String name;

}
