package com.distribuida.books.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private Integer id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private String authorName;

}
