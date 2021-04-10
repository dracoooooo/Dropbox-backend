package com.dropbox.service.impl;

import com.dropbox.common.Constants;
import com.dropbox.vo.FileVO;
import com.dropbox.mapper.UserMapper;
import com.dropbox.service.FileService;
import com.dropbox.utils.FileUtil;
import com.dropbox.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVO upload(MultipartFile[] files, String username) {
        if(files == null) return ResponseVO.Failure("upload failed");
        for(MultipartFile file : files) {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
//                Long size = file.getSize();
//                Date time = new Date();
                try {
                    InputStream input = file.getInputStream();

                    String path = Constants.filePath  + File.separator + username + File.separator + name;

                    file.transferTo(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseVO.Failure("upload failed");
                }
            }
        }
        return ResponseVO.Success("upload success");

    }

    @Override
    public ResponseEntity<Resource> download(String username, String filePath) {

        String path = Constants.filePath + File.separator + username + File.separator + filePath;

        File file = new File(path);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "File-Name");
//        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        try {
            header.add("File-Name", URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path1 = Paths.get(file.getAbsolutePath());
        try {
            ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path1));
            return ResponseEntity.ok()
                    .headers(header)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ResponseVO delete() {
        return null;
    }

    @Override
    public ResponseVO allFiles(String username) {
        String path = Constants.filePath + File.separator + username;
        File file = new File(path);
        File[] files = file.listFiles();
        ArrayList<FileVO> ret = new ArrayList<>();
        if(files == null) return ResponseVO.Success("no file");
        for(File f : files){
            ret.add(FileUtil.File2FileVO(f));
        }

        return ResponseVO.Success("get all file success", ret);
    }

//    public static void main(String[] args){
//        File f = new File("D:\\java\\dropbox\\src\\main\\resources\\file\\draco\\18t9e9m4m3.png");
//        System.out.println(f.length());
//    }
}
