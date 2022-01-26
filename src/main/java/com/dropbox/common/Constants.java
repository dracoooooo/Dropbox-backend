package com.dropbox.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "constants")
public class Constants {

    private int UserStorage = 1000;

    private int InitialUserUsage = 0;

    private String filePath = "";

}
