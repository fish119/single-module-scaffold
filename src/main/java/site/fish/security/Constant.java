package site.fish.security;

/**
 * Description: [Constant]
 * Copyright  : Copyright (c) 2021

 * @author : Morphling
 * @version : 1.0
 * @date : 2021/1/30 18:17
 */
public class Constant {
    public static final String TOKEN_HEADER = "Authorization";
        public static final String SECRET = "7945fb2b22546bf6e9290a68312cb033";
    public static final String TOKEN_PREFIX = "fish119";
    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final long EXPIRATION = 30*24*60*60*1000L;
    public static final String API_URL_PREFIX = "/api/";
}
