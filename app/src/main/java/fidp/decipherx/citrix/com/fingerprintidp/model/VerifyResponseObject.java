package fidp.decipherx.citrix.com.fingerprintidp.model;

/**
 * Created by sanketmishra on 8/21/17.
 */

public class VerifyResponseObject {

    private static String fqdn;

    private static  Boolean verified;

    public VerifyResponseObject() {
    }

    public static String getFqdn() {
        return fqdn;
    }

    public static void setFqdn(String fqdn) {
        VerifyResponseObject.fqdn = fqdn;
    }

    public static Boolean getVerified() {
        return verified;
    }

    public static void setVerified(Boolean verified) {
        VerifyResponseObject.verified = verified;
    }

    public static void nullify(){
        fqdn = null;
        verified = false;
    }

}
