package com.finalproj5003.libarysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Record {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer bookId;
    private String userName;
    private String bookName;
    private String action;
    private String date;
    private String state;

    public Record(Integer bookId,Integer userId,String userName,String bookName,String action,String date,String state)
    {
        setBookId(bookId);
        setUserId(userId);
        setUserName(userName);
        setBookName(bookName);
        setAction(action);
        setDate(date);
        setState(state);
    }
}
