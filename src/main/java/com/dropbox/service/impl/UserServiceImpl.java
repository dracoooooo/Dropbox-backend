package com.dropbox.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dropbox.entity.Usage;
import com.dropbox.entity.User;
import com.dropbox.common.Constants;
import com.dropbox.service.UserService;
import com.dropbox.vo.Response;
import com.dropbox.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

   @Autowired
   private HttpSession session;

    public Response login(User user){
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("username", user.getUsername());
        columnMap.put("password", user.getPassword());

        List<User> userList = userMapper.selectByMap(columnMap);
        if (userList.size() == 1){
            session.setAttribute("username", user.getUsername());
            String userPath = Constants.filePath + File.separator + user.getUsername();
            File dir = new File(userPath);
            if(!dir.exists()){
                dir.mkdirs();
            }

            return Response.Success("login success");
        }
        else{
            return Response.Failure("login failed");
        }
    }

    public Response register(User user){

        userMapper.insert(user);

        String userPath = Constants.filePath + File.separator + user.getUsername();
        File dir = new File(userPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        return Response.Success("register success");
    }

    @Override
    public Response logout(User user) {
        session.removeAttribute("username");
        return Response.Success("logout success");
    }

    @Override
    public Response check(User user) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("username", user.getUsername());

        List<User> userList = userMapper.selectByMap(columnMap);
        if(userList.isEmpty()){
            return Response.Success();
        }
        else return Response.Failure("duplicated username");
    }


}
