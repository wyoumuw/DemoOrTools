package com.youmu.maven;

import com.youmu.maven.dao.impl.BookDaoImpl;
import com.youmu.maven.dao.utils.MyBatisUtil;
import com.youmu.maven.entity.Book;
import com.youmu.maven.support.DocumentConverter;
import com.youmu.maven.lucene.Indexer;
import com.youmu.maven.utils.Environment;

import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class IndexCreatorApp {


    public static void main(String[] args) throws Exception {
        Indexer indexer=new Indexer();
        MyBatisUtil.initialize();
        final List<Book> books=new BookDaoImpl().queryAllBooks();
        System.out.println("=============start to create indexes=============");
        indexer.createIndexs(books, Environment.STORE_DIRECTORY_PATH, new DocumentConverter() {
            @Override
            public String[] getFieldNames() {
                return new String[]{"id:test:true","name:text:true","description:store"};
            }
        });
        System.out.println("=============successfully create indexes=============");
    }
}
