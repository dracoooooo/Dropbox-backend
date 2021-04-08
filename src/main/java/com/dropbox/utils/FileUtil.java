package com.dropbox.utils;

import com.dropbox.entity.FileEntity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static FileEntity File2FileEntity(File f){
        FileEntity ret = new FileEntity();
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
