package site.fish.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * Description: [ApiError]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/4 12:59
 */
@Data
public class ApiError implements Serializable {
    private String timestamp;
    private int status;
    private String message;
    private Map<String, String> errors;
    private String path;

    public ApiError() {
    }

    public ApiError(int status, String message) {
        this.status = status;
        this.message = message;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = formatter.format(LocalDateTime.now());
    }
}
