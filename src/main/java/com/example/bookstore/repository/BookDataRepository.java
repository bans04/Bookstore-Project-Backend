package com.example.bookstore.repository;
import com.example.bookstore.entity.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDataRepository extends JpaRepository<BookData, Long> {
}
