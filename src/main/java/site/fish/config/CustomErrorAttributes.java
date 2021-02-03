package site.fish.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: [CustomErrorAttributes]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/1 20:38
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        Object timestamp = errorAttributes.get("timestamp");
        if (timestamp == null) {
            errorAttributes.put("timestamp", formatter.format(LocalDateTime.now()));
        } else {
            errorAttributes.put("timestamp", formatter.format(((Date) timestamp).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()));
        }
        /*
        if (!ObjectUtils.isEmpty(errorAttributes.get("errors"))) {
            Map<String, String> errors = new HashMap<>(2);
            ((List<ObjectError>) errorAttributes.get("errors"))
                    .forEach((error) -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                    });
            errorAttributes.put("errors",errors);
        }
         */
        return errorAttributes;
    }
}
