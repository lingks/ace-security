package com.github.wxiaoqi.blog.admin.mapper;

import com.github.wxiaoqi.blog.admin.entity.FlashNews;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface FlashNewsMapper extends Mapper<FlashNews> {

    List<FlashNews> selectByCrtTime(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("title")String title);
    int selectCountByCrtTime(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("title")String title);
}