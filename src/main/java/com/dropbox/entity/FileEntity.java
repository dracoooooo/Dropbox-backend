package com.dropbox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    private boolean isDir;

    private String name;

//    byte
    private Long size;

    private String time;
}
