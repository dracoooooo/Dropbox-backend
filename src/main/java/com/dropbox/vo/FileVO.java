package com.dropbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

    private boolean isDir;

    private String name;

//    byte
    private Long size;

    private String time;
}
