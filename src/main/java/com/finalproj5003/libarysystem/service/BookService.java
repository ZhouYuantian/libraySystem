package com.finalproj5003.libarysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finalproj5003.libarysystem.dao.mapper.BookDao;
import com.finalproj5003.libarysystem.entity.Book;
import com.finalproj5003.libarysystem.entity.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService
{
    @Autowired
    BookDao bookDao;

    @Autowired
    RecordService recordService;

    public void addBook(Book book)
    {
        bookDao.insert(book);
    }

    public boolean hasStock(Book book)
    {
        return book.getStock()>0;
    }

    public void rentBook(Book book)
    {
        Integer stock=book.getStock();
        Integer id=book.getId();
        book.setStock(stock-1);
        bookDao.update(book,new QueryWrapper<Book>().eq("id",id));
    }


    public void returnBook(Book book)
    {
        Integer stock=book.getStock();
        Integer id=book.getId();
        book.setStock(stock+1);
        bookDao.update(book,new QueryWrapper<Book>().eq("id",id));
    }

    public List<Book> findAll(String keyword)
    {
        QueryWrapper<Book> wrapper=new QueryWrapper<Book>().like("book_name",keyword).or().
                like("description",keyword).or().like("author",keyword).or().like("edition",keyword);
        return bookDao.selectList(wrapper);
    }

    public Book getBookById(Integer bookId)
    {
        return bookDao.selectOne(new QueryWrapper<Book>().eq("id",bookId));
    }

    public List<Book> findAll()
    {
        return bookDao.selectList(new QueryWrapper<Book>());
    }
}
