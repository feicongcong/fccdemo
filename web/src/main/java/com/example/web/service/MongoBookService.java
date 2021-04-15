package com.example.web.service;

import com.example.web.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class MongoBookService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Book> findAll() {
        return mongoTemplate.findAll(Book.class);
    }

    public void saveObj(String name) {
        Book book = new Book();
        book.setName(name);
        book.setVersion(0);
        mongoTemplate.save(book);
    }

    public Book getBookById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Book.class);
    }

    public String updateBook(Book book) {
        Query query = new Query(Criteria.where("_id").is(book.getId()));
        Update update = new Update().set("name", book.getName());
        // updateFirst 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, Book.class);
        // updateMulti 更新查询返回结果集的全部
        // mongoTemplate.updateMulti(query,update,Book.class);
        // upsert 更新对象不存在则去添加
        // mongoTemplate.upsert(query,update,Book.class);
        return "success";
    }

    public String deleteBook(Book book) {
        mongoTemplate.remove(book);
        return "success";
    }

    public String deleteBookById(String id) {
        // findOne
        Book book = getBookById(id);
        // delete
        deleteBook(book);
        return "success";
    }

    public Book getBookByName(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, Book.class);
    }

    public List<Book> findByLikes(String search) {
        Query query = new Query();
        Criteria criteria = new Criteria();
        //criteria.where("name").regex(search);
        Pattern pattern = Pattern.compile("^.*" + search + ".*$", Pattern.CASE_INSENSITIVE);
        criteria.where("name").regex(pattern);
        List<Book> lists = mongoTemplate.findAllAndRemove(query, Book.class);
        return lists;
    }
}