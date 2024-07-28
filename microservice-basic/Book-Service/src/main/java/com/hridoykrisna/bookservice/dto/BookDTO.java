package com.hridoykrisna.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO {
    private long bookId;
    @NotBlank(message = "Book Name is Mandatory")
    private String bookName;
    @NotBlank(message = "Author Name is Mandatory")
    private String authorName;
    @NotNull(message = "Publishing Year Name is Mandatory")
    private int publishingYear;
    @NotNull(message = "User Id is Mandatory")
    private long userId;
}
