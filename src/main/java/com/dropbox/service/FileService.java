package com.dropbox.service;

import com.dropbox.vo.Response;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public Response upload(MultipartFile[] files);

    public Response download();

    public Response delete();

    public Response allFiles(String username);

}
