package pl.natekrank.helpers;

import java.security.SecureRandom;

public class SurveyHelper {
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
    private static int keyLength = 8;

    public static String generateToken() {
        StringBuilder sb = new StringBuilder();
        for( int i = 0; i < keyLength; i++ )
            sb.append(AB.charAt( rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
