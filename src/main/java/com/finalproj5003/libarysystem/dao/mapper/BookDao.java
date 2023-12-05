package com.finalproj5003.libarysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.finalproj5003.libarysystem.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookDao extends BaseMapper<Book> {
}
