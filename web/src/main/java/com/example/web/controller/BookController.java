package com.example.web.controller;

import com.example.web.entity.Book;
import com.example.web.service.MongoBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    @Autowired
    private MongoBookService mongoBookService;

    @GetMapping("findAll")
    public List<Book> findAll() {
        return mongoBookService.findAll();
    }

    @GetMapping("create")
    public String create(@RequestParam(name = "name") String name) {
        mongoBookService.saveObj(name);
        return "success";
    }

    @PostMapping("update")
    public String update(@RequestBody Book book) {
        mongoBookService.updateBook(book);
        return "success";
    }
}
