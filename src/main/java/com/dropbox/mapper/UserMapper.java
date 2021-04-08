package com.dropbox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dropbox.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    int tmp = 1;
}
