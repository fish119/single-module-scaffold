package site.fish.config;

/**
 * Description: [异常相关常量类]
 * Copyright  : Copyright (c) 2021
 * Company    : 沈阳云创工业智能技术有限公司
 *
 * @author : Morphling
 * @version : 1.0
 * @date : 2021/2/1 15:47
 */
public class ExceptionMessage {
    public final static String USERNAME_NOT_FOUND = "用户不存在";
    public final static String BAD_CREDENTIALS = "认证失败，请登录后重试";
    public final static String FORBIDDEN="没有访问权限，请联系管理员";
    public final static String CREDENTIALS_EXPIRED="认证超时，请重新登录";
    public final static String ARGUMENT_NOT_VALID = "参数校验失败";
    public final static String SERVER_ERROR = "服务器异常，请稍后重试";
    public final static String TOKEN_EXPIRED = "认证过期，Token无法刷新，请重新登录";
    public final static String REFRESH_TOKEN_BY_GUEST = "未登录用户请求刷新Token";
    public final static String WRONG_PASSWORD = "密码输入错误，请确认后重试";
}
