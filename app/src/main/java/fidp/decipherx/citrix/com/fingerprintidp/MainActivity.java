package fidp.decipherx.citrix.com.fingerprintidp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import fidp.decipherx.citrix.com.fingerprintidp.util.DatabaseHelper;
import fidp.decipherx.citrix.com.fingerprintidp.model.VerifyResponseObject;
import fidp.decipherx.citrix.com.fingerprintidp.service.CheckTokenService;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {

    Button scanView, listView;
    DatabaseHelper db;
    CheckTokenService checkTokenService;
    static LinkedHashMap<String,String> userProfile = new LinkedHashMap<>();
    private final static String USERNAME = "sanket";
    private final static String IMEI ="6571682182679-80";
    private final static String PHONE_NUMBER = "(415) 640 0312";
    private final static String EMAIL_ADDRESS = "sanket.mishra@citrix.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Activity activity = this;
        scanView = (Button)findViewById(R.id.scanView);
        listView = (Button)findViewById(R.id.listView);

        populateUserProfile();

        List<LinkedHashMap<String, String>> listItems = new ArrayList<>();
        ListView userProfileListView = (ListView) findViewById(R.id.userDetailView);
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.listitem_profile,
                new String[]{"key", "value"},
                new int[]{R.id.text1, R.id.text2});

        Iterator it = userProfile.entrySet().iterator();
        while(it.hasNext()){
            LinkedHashMap<String, String> resultsMap =   new LinkedHashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultsMap.put("key", pair.getKey().toString());
            resultsMap.put("value", pair.getValue().toString());
            listItems.add(resultsMap);
        }
        userProfileListView.setAdapter(adapter);


        scanView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(true);
                intentIntegrator.setBarcodeImageEnabled(true);
                intentIntegrator.initiateScan();
            }
        });

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListContents.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.FCM_PREF), Context.MODE_PRIVATE);
        String fcmToken = FirebaseInstanceId.getInstance().getToken();

        Log.i("FCM Token : ", fcmToken);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!= null) {
            if (isNetworkAvailable(this)) {

                //RestClientService restClientService = new RestClientService(this);
                checkTokenService = new CheckTokenService(this);
                checkTokenService.setUsername(USERNAME);
                try {
                    checkTokenService.checkIfValidTokenScanned(fcmToken, result.getContents());
                    db = new DatabaseHelper(this);
                    String clientId = checkTokenService.getClientId();
                    Log.i("Client Id : ", clientId);

                    while(null==VerifyResponseObject.getVerified()){
                        sleep(1000);
                    }
                    if (VerifyResponseObject.getVerified()) {
                        String fqdn = VerifyResponseObject.getFqdn();

                        if (!db.addClient(fqdn, clientId)) {
                            Toast.makeText(getApplicationContext(), "DB ERROR: FQDN " + fqdn + " could not be added!", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "FQDN " + fqdn + " with Client Id <" + clientId + "> has been added", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "ERROR: Invalid token scanned! ", Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(getApplicationContext(), "ERROR: Please check your internet connection and retry! ", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "ERROR: Could not parse content! ", Toast.LENGTH_LONG).show();
        }

        VerifyResponseObject.nullify();
        super.onActivityResult(requestCode,resultCode, data);
    }

    //check internet connection
    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private void populateUserProfile(){
        userProfile.put("Username :", USERNAME);
        userProfile.put("Email Address :", EMAIL_ADDRESS);
        userProfile.put("Phone Number :" , PHONE_NUMBER);

        userProfile.put("Device Name :", "One Plus One A3000");
        userProfile.put("IMEI :", IMEI);
        userProfile.put("Device Id :", "android-20016sgy7268gdjgy3t");
    }



}
