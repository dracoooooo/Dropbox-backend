package com.dropbox.utils;

import com.dropbox.vo.FileVO;

import java.io.File;
import java.text.SimpleDateFormat;

public class FileUtil {
    public static FileVO File2FileVO(File f){
        FileVO ret = new FileVO();
        ret.setDir(f.isDirectory());
        ret.setName(f.getName());
        if(!f.isDirectory())
            ret.setSize(f.length());
        ret.setTime(FileUtil.DateFormatter(f.lastModified()));
        return ret;
    }

    public static String DateFormatter(long time){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formater.format(time);
    }
}
