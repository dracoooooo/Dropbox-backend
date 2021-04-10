package com.dropbox.service;

import com.dropbox.vo.ResponseVO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public ResponseVO upload(MultipartFile[] files, String username);

    public ResponseEntity<Resource> download(String username, String filePath);

    public ResponseVO delete();

    public ResponseVO allFiles(String username);

}
