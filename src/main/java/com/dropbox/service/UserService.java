package com.dropbox.service;

import com.dropbox.entity.User;
import com.dropbox.vo.Response;

public interface UserService {

    public Response login(User user);

    public Response register(User user);

    public Response logout(User user);

    public Response check(User user);
}
