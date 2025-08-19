package com.arthur.ecommerceapi.login.dtos.response;

public record LoginResponse (
        String accessToken ,
        Long expireToken
) { }
