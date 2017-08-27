package fidp.decipherx.citrix.com.fingerprintidp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import fidp.decipherx.citrix.com.fingerprintidp.model.NotificationDisplay;
import fidp.decipherx.citrix.com.fingerprintidp.service.FCMMessagingService;
import fidp.decipherx.citrix.com.fingerprintidp.service.GrantService;
import fidp.decipherx.citrix.com.fingerprintidp.service.RestService;

import static android.os.SystemClock.sleep;

public class NotificationActivity extends AppCompatActivity {

    Button approveButton, denyButton;
    TextView userActionMessage;

    private String fqdn, timestamp, browser, clientIp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        approveButton = (Button)findViewById(R.id.buttonApprove);
        denyButton = (Button)findViewById(R.id.buttonDeny);

        userActionMessage = (TextView)findViewById(R.id.textViewUserActionData);

        if (getIntent().getExtras() !=null){
            for ( String key : getIntent().getExtras().keySet()){
                if (key.equals("fqdn")){
                    fqdn = getIntent().getExtras().getString(key);
                }if (key.equals("timestamp")){
                    timestamp = getIntent().getExtras().getString(key);
                }if (key.equals("clientIp")){
                    clientIp = getIntent().getExtras().getString(key);
                }if (key.equals("browser")){
                    browser = getIntent().getExtras().getString(key);
                }
            }
        }

        String formattedText = String.format("At <b><font color='#800000'>%s</font></b>\n, <b><font color='#800000'>%s</font></b> is requesting you " +
                "to approve the login request using\nBrowser: <b><font color='#800000'>%s</font></b>\n" +
                "from \nIP Address <b><font color='#800000'>%s</font></b>", timestamp, fqdn, browser, clientIp);
        userActionMessage.setText(Html.fromHtml(formattedText));

        approveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new GrantService(MainActivity.USERNAME, fqdn, true).execute();
                Toast.makeText(getApplicationContext(), "Request Approved ", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NotificationActivity.this , MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
