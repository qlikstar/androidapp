package fidp.decipherx.citrix.com.fingerprintidp.model;

/**
 * Created by sanketmishra on 8/19/17.
 */

/*
{
  "aud": "Tuppu",
  "sub": "QR Code JWT Token",
  "clientId": "cb2f8195-fc67-4b22-aebb-c9459ae66e55",
  "iss": "https://fidp.citrix.com",
  "exp": 1503186191
}

 */

public class JWTClaimSet {

    private String aud;

    private String sub;

    private String clientId;

    private String iss;

    private String exp;

    public JWTClaimSet() {
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }
}
