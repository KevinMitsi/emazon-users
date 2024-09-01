package com.kevin.emazon_users.infraestructure.security.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;



public class UtilSecurityClass {
    private UtilSecurityClass(){}
    public static final SecretKey SECRET_KEY = Jwts.SIG.HS384.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final int MILLIS_TIME_EXPIRATION = 3600000;
    public static final String CONTENT_TYPE = "application/json";


}