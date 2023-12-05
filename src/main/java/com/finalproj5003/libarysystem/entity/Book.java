package com.finalproj5003.libarysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book
{
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String bookName;
    private String description;
    private String author;
    private String edition;
    private Integer stock;


    public Book(String bookName, String description, Integer stock, String author,String edition)
    {
        setBookName(bookName);
        setDescription(description);
        setStock(stock);
        setAuthor(author);
        setEdition(edition);
    }
}
