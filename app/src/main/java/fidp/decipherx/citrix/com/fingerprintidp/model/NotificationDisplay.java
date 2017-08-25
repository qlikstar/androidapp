package fidp.decipherx.citrix.com.fingerprintidp.model;

import java.util.Date;

/**
 * Created by sanketmishra on 8/24/17.
 */

public class NotificationDisplay {

    private static String notificationMessage;

    private static String fqdn;

    private static String clientIp;

    private static String browser;

    private static String timestamp;

    public static String getNotificationMessage() {
        return notificationMessage;
    }

    public static void setNotificationMessage(String notificationMessage) {
        NotificationDisplay.notificationMessage = notificationMessage;
    }

    public static String getFqdn() {
        return fqdn;
    }

    public static void setFqdn(String fqdn) {
        NotificationDisplay.fqdn = fqdn;
    }

    public static String getClientIp() {
        return clientIp;
    }

    public static void setClientIp(String clientIp) {
        NotificationDisplay.clientIp = clientIp;
    }

    public static String getBrowser() {
        return browser;
    }

    public static void setBrowser(String browser) {
        NotificationDisplay.browser = browser;
    }

    public static String getTimestamp() {
        return timestamp;
    }

    public static void setTimestamp(String timestamp) {
        NotificationDisplay.timestamp = timestamp;
    }

    public NotificationDisplay() {
    }


}
