package fidp.decipherx.citrix.com.fingerprintidp.util;

/**
 * Created by sanketmishra on 8/20/17.
 */

public class UserStatic {

    private static String username;

    private static String imei;

    private static String phoneNumber;

    private static String emailAddress;

    private static String publicKey;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserStatic.username = username;
    }

    public static String getImei() {
        return imei;
    }

    public static void setImei(String imei) {
        UserStatic.imei = imei;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        UserStatic.phoneNumber = phoneNumber;
    }

    public static String getEmailAddress() {
        return emailAddress;
    }

    public static void setEmailAddress(String emailAddress) {
        UserStatic.emailAddress = emailAddress;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public static void setPublicKey(String publicKey) {
        UserStatic.publicKey = publicKey;
    }
}
