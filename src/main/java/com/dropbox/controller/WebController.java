package com.dropbox.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.dropbox.service.FileService;
import com.dropbox.utils.JWTUtil;
import com.dropbox.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class WebController {

    @Autowired
    private FileService fileService;


    @ResponseBody
    @PostMapping("/file/upload")
    public ResponseVO upload(MultipartFile[] files,
                             HttpServletRequest request){
        String username = (String) request.getAttribute("username");
        return fileService.upload(files, username);
    }

    @ResponseBody
    @GetMapping("/file/allfile")
    public ResponseVO getAllFile(HttpServletRequest request){
        String username = (String) request.getAttribute("username");
        return fileService.allFiles(username);
    }

    @ResponseBody
    @PostMapping("/file/newDir")
    public ResponseVO newDir(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        String username = verify.getClaim("username").asString();
        return null;
    }

    @ResponseBody
    @GetMapping("file/download")
    public ResponseEntity<Resource> download(HttpServletRequest request,
                                             @RequestParam("filePath") String filePath){ // 这里其实是个安全漏洞
        String username = (String) request.getAttribute("username");
        return fileService.download(username, filePath);
    }

    @ResponseBody
    @PostMapping("file/star")
    public ResponseVO star(){
        return null;
    }

    @ResponseBody
    @PostMapping("file/remove")
    public ResponseVO remove(){
        return null;
    }

    @ResponseBody
    @PostMapping("file/realremove")
    public ResponseVO realRemove(){
        return null;
    }

    @ResponseBody
    @PostMapping("file/delete")
    public ResponseVO delete(HttpServletRequest request,
                             @RequestParam("filePath") String filePath){
        String username = (String) request.getAttribute("username");
        return fileService.delete(username, filePath);
    }
}
