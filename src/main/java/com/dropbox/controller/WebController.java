package com.dropbox.controller;

import com.dropbox.service.FileService;
import com.dropbox.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class WebController {

    @Autowired
    private FileService fileService;

//    @GetMapping("/{username}")
//    public Response getFiles(){
//        return null;
//    }

    @ResponseBody
    @PostMapping("/upload")
    public Response upload(@RequestParam("files") MultipartFile[] files){
        return fileService.upload(files);
    }

    @ResponseBody
    @GetMapping("/file/{username}")
    public Response getAllFile(@PathVariable("username") String username){
        return fileService.allFiles(username);
    }


}
