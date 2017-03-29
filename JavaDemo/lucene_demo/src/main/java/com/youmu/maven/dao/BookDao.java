package com.youmu.maven.dao;

import com.youmu.maven.entity.Book;

import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public interface BookDao {
    List<Book> queryAllBooks();
}
