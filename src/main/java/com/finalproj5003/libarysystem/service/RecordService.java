package com.finalproj5003.libarysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finalproj5003.libarysystem.dao.mapper.RecordDao;
import com.finalproj5003.libarysystem.entity.Book;
import com.finalproj5003.libarysystem.entity.Record;
import com.finalproj5003.libarysystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class RecordService {
    @Autowired
    RecordDao recordDao;

    public void addRecord(Book book,User user, String action)
    {
        String state=action.equals("rent")? "renting":"returned";
        Integer bookId=book.getId();
        Integer userId=user.getId();
        String bookName=book.getBookName();
        String userName= user.getUserName();
        String date= LocalDate.now().toString();
        Record record=new Record(bookId,userId,userName,bookName,action,date,state);
        recordDao.insert(record);

        if(action.equals("return")) setReturned(book,user);
    }

    public void setReturned(Book book,User user)
    {
        QueryWrapper<Record> wrapper=new QueryWrapper<Record>().eq("user_id",user.getId()).
                eq("book_id",book.getId()).eq("state","renting");
        Record record=recordDao.selectOne(wrapper);

        record.setState("returned");
        recordDao.updateById(record);
    }
    public boolean isRenting(User user, Book book)
    {
        QueryWrapper<Record> wrapper=new QueryWrapper<Record>().eq("user_id",user.getId()).
                eq("book_id",book.getId()).eq("state","renting");
        int rentCount=recordDao.selectCount(wrapper);

        return rentCount==1;
    }

    public List<Record> findAll(String keyword)
    {
        QueryWrapper<Record> wrapper=new QueryWrapper<Record>().like("user_id",keyword).or().
                like("book_id",keyword).or().like("user_name",keyword).or().
                like("book_name",keyword).or().like("action",keyword).or().like("date",keyword);
        return recordDao.selectList(wrapper);
    }

    public List<Record> findAll()
    {
        return recordDao.selectList(new QueryWrapper<Record>());
    }

    public List<Record> getRentingListByUserName(String userName)
    {
        return recordDao.selectList(new QueryWrapper<Record>().eq("user_name",userName).eq("state","renting"));
    }

    public List<Record> getRecordsByUserName(String userName)
    {
        return recordDao.selectList(new QueryWrapper<Record>().eq("user_name",userName));
    }

}
