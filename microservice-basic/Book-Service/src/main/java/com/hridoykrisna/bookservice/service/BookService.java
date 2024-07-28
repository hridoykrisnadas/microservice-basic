package com.hridoykrisna.bookservice.service;

import com.hridoykrisna.bookservice.dto.BookDTO;
import com.hridoykrisna.bookservice.dto.Response;
import com.hridoykrisna.bookservice.dto.BookDTO;

public interface BookService {

    Response insertBookInfo(BookDTO bookDTO);

    Response updateBookInfo(long bookId, BookDTO bookDTO);

    Response getAllBookInfo();

    Response getBookInfo(long bookId);

    Response deleteBook(long bookId);

    Response getBookListByUser(long userId);
}
