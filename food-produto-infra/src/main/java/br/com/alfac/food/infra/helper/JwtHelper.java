package br.com.alfac.food.infra.helper;

import com.auth0.jwt.JWT;

public class JwtHelper {

    public static Long getID(String jwt) {
        return JWT.decode(getToken(jwt))
                .getClaim("id")
                .asLong();
    }

    public static String getWho(String jwt) {
        return JWT.decode(getToken(jwt))
                .getClaim("who")
                .asString();
    }

    private static String getToken(String jwt) {
        return jwt.replace("Bearer ", "");
    }

}