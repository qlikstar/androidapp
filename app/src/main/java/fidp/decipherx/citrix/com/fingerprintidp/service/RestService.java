package fidp.decipherx.citrix.com.fingerprintidp.service;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import fidp.decipherx.citrix.com.fingerprintidp.MainActivity;
import fidp.decipherx.citrix.com.fingerprintidp.model.VerifyResponseObject;

/**
 * Created by sanketmishra on 8/21/17.
 */

public class RestService extends AsyncTask<Object, Object, Void> {

    private static String FIDP_SERVER_URL = "http://fidp.pasdev.net";

    private String token;

    private String fqdn;

    private String username;

    public String getFqdn() {
        return fqdn;
    }

    public RestService(String token, String username) {
        this.token = token;
        this.username = username;
    }

    @Override
    protected Void doInBackground(Object... strings) {
        String URL = FIDP_SERVER_URL + "/api/v1/device/verify";
        //String json = "{\"token\":\"" + token + "\"}";

        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
            jsonObject.put("username", username );

            Log.i("Json" , jsonObject.toString());

            URL url = new URL(URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            OutputStream os = conn.getOutputStream();
            os.write(jsonObject.toString().getBytes("UTF-8"));
            os.close();

            // read the response
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = IOUtils.toString(in, "UTF-8");

            Log.i("Result :" , result);

            JSONObject jsonResponseObject = new JSONObject(result);
            parseJsonResponse(jsonResponseObject);
            jsonResponseObject.getString("fqdn");

            in.close();
            conn.disconnect();

        }catch (MalformedURLException me){

        }catch (ProtocolException pe){

        }catch (IOException ie){

        }catch (JSONException je){

        }catch (Exception e){

        }
        return null;
    }

    private void parseJsonResponse(JSONObject jsonObject){

        try {
            if (jsonObject.getBoolean("verified")) {
                VerifyResponseObject.setVerified(jsonObject.getBoolean("verified"));
                VerifyResponseObject.setFqdn(jsonObject.getString("fqdn"));
            }else{
                VerifyResponseObject.setVerified(jsonObject.getBoolean("verified"));
                VerifyResponseObject.setFqdn("");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
