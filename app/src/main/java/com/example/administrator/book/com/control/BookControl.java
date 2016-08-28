package com.example.administrator.book.com.control;

import android.content.Context;
import com.example.administrator.book.com.model.DBAdapter;
import com.example.administrator.book.com.model.Book;
import com.example.administrator.book.com.model.BookSet;

import java.io.File;
import java.io.IOException;

/**
 * Created by Fly Wu on 2016/7/19.
 */
public class BookControl {
    private static DBAdapter stuDB;
    private static BookSet set;
    Context context;

    public BookControl(Context context) {
        this.context = context;
        stuDB = new DBAdapter(context);
        stuDB.open();

    }
//添加单本书
    public boolean addBook(Book s1) {
        stuDB.insertBook(s1);
        stuDB.close();

        return true;
    }
//保存所有初始图书信息
    public void saveAll() {
        set = BookSet.getBookList();
        set.clear();
        set.readFile(context);
        for (int i = 0; i < set.size(); i++) {
            stuDB.insertBook(set.get(i));
        }
        stuDB.close();
    }

    //读文件，批量插入
    public boolean insertFile(File file) throws IOException {

            set = BookSet.getBookList();
            set.insertFile(file);
            for (int i = 0; i < set.size(); i++) {
                stuDB.insertBook(set.get(i));
                stuDB.close();
            }
           return true;

    }

//删除所有图书
    public void deleteAll() {
        stuDB.deleteAllDataBook();
    }
//删除单本书
    public boolean deleteBookByNo(String no) {
        Book s[] = stuDB.getOneByNoBook(no);
        if (s != null) {
            stuDB.deleteBookDatabyNo(no);
            stuDB.close();
            return true;
        }
        return false;
    }
//修改图书信息
    public boolean updataByNo(Book e) {
        String no = e.getBookno();
        Book s[] = stuDB.getOneByNoBook(no);
        if (s != null) {
            stuDB.updateByBookno(no, e);
            stuDB.close();

        }

        return true;
    }
//查询书号
    public Book[] QueryOnByNo(String no) {
        return stuDB.getOneByNoBook(no);

    }
//查询（书名，作者，出版社，库存等）
    public Book[] getAttrBook(String attr, String book_attr) {//参数分别是bookinfo表中的字段名，用户输入的book的属性
        return stuDB.getAttrBook(attr, book_attr);
    }


//查询所有图书
    public Book[] getAllBook() {
        return stuDB.getAllBook();
    }
}