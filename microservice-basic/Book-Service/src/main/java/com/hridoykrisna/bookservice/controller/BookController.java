package com.hridoykrisna.bookservice.controller;

import com.hridoykrisna.bookservice.dto.BookDTO;
import com.hridoykrisna.bookservice.service.BookService;
import com.hridoykrisna.bookservice.dto.Response;
import com.hridoykrisna.bookservice.util.ResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/insert-book")
    public Response insertBookInfo(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBuilder.getFailureMessage(bindingResult, "Field Value Error");
        }
        return bookService.insertBookInfo(bookDTO);
    }
    @PostMapping("/update-book/{bookId}")
    public Response updateUserInfo(@Valid @PathVariable("bookId") long bookId, @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseBuilder.getFailureMessage(bindingResult, "Field Value Error");
        }
        return bookService.updateBookInfo(bookId, bookDTO);
    }

    @GetMapping("/get-all-book")
    public Response getAllBookInfo(){
        return bookService.getAllBookInfo();
    }
    @GetMapping("/get-book/{bookId}")
    public Response getBookInfo(@PathVariable("bookId") long bookId){
        return bookService.getBookInfo(bookId);
    }

    @DeleteMapping("/delete-book/{bookId}")
    public Response deleteBookId(@PathVariable("bookId") long bookId){
        return bookService.deleteBook(bookId);
    }

    @GetMapping("/get-book-by-user/{userId}")
    public Response getBookByUser(@PathVariable("userId") long userId){
        return bookService.getBookListByUser(userId);
    }
}
