package site.fish.entity.log;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Description: [日志Entity]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 14:42
 */
@Entity()
@Table(name = "logging_event")
@Data
public class LoggingEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;
    private Long timestmp;
    @Column(name = "formatted_message")
    private String formattedMessage;
    @Column(name = "logger_name")
    private String loggerName;
    @Column(name = "level_string")
    private String levelString;
    @Column(name = "thread_name")
    private String threadName;
    @Column(name = "reference_flag")
    private Long referenceFlag;
    private String arg0;
    private String arg1;
    private String arg2;
    private String arg3;
    @Column(name = "caller_filename")
    private String callerFilename;
    @Column(name = "caller_class")
    private String callerClass;
    @Column(name = "caller_method")
    private String callerMethod;
    @Column(name = "caller_line")
    private String callerLine;
    @Transient
    private Date dateTime;
}
