package com.dropbox.service;

import com.dropbox.entity.User;
import com.dropbox.vo.ResponseVO;

public interface UserService {

    public ResponseVO login(User user);

    public ResponseVO register(User user);

    public ResponseVO check(User user);

    public ResponseVO checkToken();
}
