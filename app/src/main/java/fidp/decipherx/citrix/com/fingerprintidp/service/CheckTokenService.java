package fidp.decipherx.citrix.com.fingerprintidp.service;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;

import java.nio.charset.StandardCharsets;

import fidp.decipherx.citrix.com.fingerprintidp.model.JWTClaimSet;
import fidp.decipherx.citrix.com.fingerprintidp.model.VerifyResponseObject;

/**
 * Created by sanketmishra on 8/19/17.
 */

public class CheckTokenService {

    private static final String ISSUER= "https://fidp.citrix.com";

    private String clientId;

    private String fqdn;

    private Context context;

    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public CheckTokenService(Context context) {
        this.context = context;
    }

    public static String getISSUER() {
        return ISSUER;
    }

    public String getClientId() {
        return clientId;
    }

    public String getFqdn() {
        return fqdn;
    }

    public Boolean checkIfValidTokenScanned(String token) throws JSONException {
        Boolean isTokenValid = false;
        Gson gson = new Gson();

        if (token.length()> 300){
            //Toast.makeText(context, "Token " + token, Toast.LENGTH_LONG).show();
            String[] tokenParts = token.split("\\.");
            if (tokenParts.length == 3) {
                byte[] data = Base64.decode(tokenParts[1], Base64.DEFAULT);
                String text = new String(data, StandardCharsets.UTF_8);

                JWTClaimSet jwtClaimSet = gson.fromJson(text, JWTClaimSet.class);

                clientId = jwtClaimSet.getClientId();
                Log.i("Client Id from JWT : ", clientId);
                fqdn = getFQDNFromRESTCall(token, username);
                isTokenValid = VerifyResponseObject.getVerified();
                return isTokenValid;
            }else{
                return false;
            }
        }
        return false;

    }

    private String getFQDNFromRESTCall(String token, String username) throws JSONException {
        //RestClientService restClientService = new RestClientService(context);
        new RestService(token, username).execute();
        Log.i("Token to POST : " , token);

        String fqdn =  VerifyResponseObject.getFqdn();
        //Log.i("FQDN retrieved : ", fqdn);
        return fqdn;

    }


}
