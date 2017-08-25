package fidp.decipherx.citrix.com.fingerprintidp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import fidp.decipherx.citrix.com.fingerprintidp.model.NotificationDisplay;

public class NotificationActivity extends AppCompatActivity {

    Button approveButton, denyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final Activity notificationActivity = this;

        approveButton = (Button)findViewById(R.id.buttonApprove);
        denyButton = (Button)findViewById(R.id.buttonDeny);

        TextView userActionData = (TextView)findViewById(R.id.textViewUserActionData);
        userActionData.setText(NotificationDisplay.getNotificationMessage());

    }
}
