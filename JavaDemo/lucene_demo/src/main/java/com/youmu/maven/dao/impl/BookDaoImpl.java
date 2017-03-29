package com.youmu.maven.dao.impl;

import com.youmu.maven.dao.BookDao;
import com.youmu.maven.dao.utils.MyBatisUtil;
import com.youmu.maven.entity.Book;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by wyoumuw on 2017/3/28.
 */
public class BookDaoImpl implements BookDao {


    public List<Book> queryAllBooks() {
        SqlSession  session=MyBatisUtil.getSession();
        String statement="book.SELECT";
        return session.selectList(statement);
    }
}
