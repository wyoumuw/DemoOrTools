package com.youmu.maven;

import com.youmu.maven.entity.Book;
import com.youmu.maven.lucene.Selector;
import com.youmu.maven.utils.Environment;

import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class SearchApp {

    public static void main(String[] args) throws Exception {
        Selector selector=new Selector();
        List<Book> bookList=selector.queryTop("id","1", Environment.STORE_DIRECTORY_PATH, Book.class);
        for (Book book:bookList){
            System.out.println(book.getId());
            System.out.println(book.getName());
            System.out.println(book.getDescription());
        }
    }
}
