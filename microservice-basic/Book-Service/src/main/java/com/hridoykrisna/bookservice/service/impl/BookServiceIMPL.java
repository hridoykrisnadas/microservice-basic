package com.hridoykrisna.bookservice.service.impl;

import com.hridoykrisna.bookservice.dto.BookDTO;
import com.hridoykrisna.bookservice.dto.Response;
import com.hridoykrisna.bookservice.model.Book;
import com.hridoykrisna.bookservice.repository.BookRepository;
import com.hridoykrisna.bookservice.service.BookService;
import com.hridoykrisna.bookservice.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceIMPL implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public Response insertBookInfo(BookDTO bookDTO) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Book book = modelMapper.map(bookDTO, Book.class);
        book = bookRepository.save(book);
        if (book != null)
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "Book Created Successfully", book);
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response updateBookInfo(long bookId, BookDTO bookDTO) {
        Optional<Book> bookOptional = bookRepository.findByBookIdAndActiveTrue(bookId);
        if (bookOptional.isEmpty())
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Book Not Found");

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Book book = modelMapper.map(bookDTO, Book.class);
        book.setBookId(bookId);
        book.setActive(true);
        book = bookRepository.save(book);
        if (book != null)
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Book Update Successfully", book);
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response getAllBookInfo() {
        List<Book> bookList = bookRepository.findAllAndActiveTrue();
        List<BookDTO> bookDTOList = getBookDTOList(bookList);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Data Fetched Successfully,", bookDTOList);
    }

    @Override
    public Response getBookInfo(long bookId) {
        Optional<Book> bookOptional = bookRepository.findByBookIdAndActiveTrue(bookId);
        if (bookOptional.isEmpty())
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Book Not Found");

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        BookDTO bookDTO = modelMapper.map(bookOptional.get(), BookDTO.class);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Product Fetched Successfully", bookDTO);
    }

    @Override
    public Response deleteBook(long bookId) {
        Optional<Book> bookOptional = bookRepository.findByBookIdAndActiveTrue(bookId);
        if (bookOptional.isEmpty())
            return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Book Not Found");
        bookOptional.get().setActive(false);
        bookRepository.save(bookOptional.get());
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Book Deleted Successfully");
    }

    @Override
    public Response getBookListByUser(long userId) {
        List<Book> bookList = bookRepository.findByUserIdAndActiveTrue(userId);
        List<BookDTO> bookDTOList = getBookDTOList(bookList);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Data Fetched Successfully,", bookDTOList);

    }

    private List<BookDTO> getBookDTOList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        bookList.forEach(book -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
            bookDTOList.add(bookDTO);
        });
        return bookDTOList;
    }
}
