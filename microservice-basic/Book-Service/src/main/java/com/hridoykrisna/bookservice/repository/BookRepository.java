package com.hridoykrisna.bookservice.repository;

import com.hridoykrisna.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("from Book where bookId=?1 and isActive=true")
    Optional<Book> findByBookIdAndActiveTrue(long bookId);

    @Query("from Book where isActive=true")
    List<Book> findAllAndActiveTrue();

    @Query("from Book where userId=?1 and isActive=true")
    List<Book> findByUserIdAndActiveTrue(long userId);
}
