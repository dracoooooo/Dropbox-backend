package com.dropbox.service.impl;

import com.dropbox.common.Constants;
import com.dropbox.entity.FileEntity;
import com.dropbox.mapper.UserMapper;
import com.dropbox.service.FileService;
import com.dropbox.utils.FileUtil;
import com.dropbox.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private HttpSession session;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Response upload(MultipartFile[] files) {
        for(MultipartFile file : files) {
            if (!file.isEmpty()) {
                String name = file.getOriginalFilename();
                Long size = file.getSize();
                Date time = new Date();

                try {
                    String path = Constants.filePath + File.separator + session.getAttribute("username") + File.separator + name;
                    file.transferTo(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                    return Response.Failure("upload failed");
                }
            }
        }
        return Response.Success("upload success");

    }

    @Override
    public Response download() {
        return null;
    }

    @Override
    public Response delete() {
        return null;
    }

    @Override
    public Response allFiles(String username) {
        String path = Constants.filePath + File.separator + username;
        File file = new File(path);
        File[] files = file.listFiles();
        ArrayList<FileEntity> ret = new ArrayList<>();
        for(File f : files){
            ret.add(FileUtil.File2FileEntity(f));
        }

        return Response.Success("get all file success", ret);
    }

//    public static void main(String[] args){
//        File f = new File("D:\\java\\dropbox\\src\\main\\resources\\file\\draco\\18t9e9m4m3.png");
//        System.out.println(f.length());
//    }
}
