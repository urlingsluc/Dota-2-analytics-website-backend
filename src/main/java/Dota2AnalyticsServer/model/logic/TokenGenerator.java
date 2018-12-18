package Dota2AnalyticsServer.model.logic;

import org.apache.commons.lang3.RandomStringUtils;

public class TokenGenerator {

    private static final int COUNT = 32;

    private TokenGenerator(){
        //private for static class
    }

    /**
     *
     * @return
     */
    public static String createToken() {
        String token = RandomStringUtils.randomAlphanumeric(COUNT);
        return token;
    }
}
