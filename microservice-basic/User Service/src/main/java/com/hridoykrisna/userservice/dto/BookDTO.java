package com.hridoykrisna.userservice.dto;

import lombok.Data;

@Data
public class BookDTO {
    private long bookId;
    private String bookName;
    private String authorName;
    private int publishingYear;
    private long userId;

}
