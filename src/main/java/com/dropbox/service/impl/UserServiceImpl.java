package com.dropbox.service.impl;

import com.dropbox.entity.User;
import com.dropbox.common.Constants;
import com.dropbox.service.UserService;
import com.dropbox.utils.JWTUtil;
import com.dropbox.vo.ResponseVO;
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

    public ResponseVO login(User user){
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("username", user.getUsername());
        columnMap.put("password", user.getPassword());

        List<User> userList = userMapper.selectByMap(columnMap);
        if (userList.size() == 1){
//            session.setAttribute("username", user.getUsername());
            String userPath = Constants.filePath + File.separator + user.getUsername();
            File dir = new File(userPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
//            生成jwt token
            Map<String, String> payload = new HashMap<>();
            payload.put("id", String.valueOf(userList.get(0).getId()));
            payload.put("username", userList.get(0).getUsername());
            String token = JWTUtil.getToken(payload);
            return ResponseVO.Success("login success", token);
        }
        else{
            return ResponseVO.Failure("login failed");
        }
    }

    public ResponseVO register(User user){

        userMapper.insert(user);

        String userPath = Constants.filePath + File.separator + user.getUsername();
        File dir = new File(userPath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        return ResponseVO.Success("register success");
    }

    @Override
    public ResponseVO logout(User user) {
        session.removeAttribute("username");
        return ResponseVO.Success("logout success");
    }

    @Override
    public ResponseVO check(User user) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("username", user.getUsername());

        List<User> userList = userMapper.selectByMap(columnMap);
        if(userList.isEmpty()){
            return ResponseVO.Success();
        }
        else return ResponseVO.Failure("duplicated username");
    }

    @Override
    public ResponseVO checkToken() {
        return ResponseVO.Success();
    }


}
