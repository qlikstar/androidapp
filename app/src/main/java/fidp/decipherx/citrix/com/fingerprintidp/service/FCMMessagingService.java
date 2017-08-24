package fidp.decipherx.citrix.com.fingerprintidp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import fidp.decipherx.citrix.com.fingerprintidp.MainActivity;
import fidp.decipherx.citrix.com.fingerprintidp.R;
import fidp.decipherx.citrix.com.fingerprintidp.ViewListContents;

/**
 * Created by sanketmishra on 8/23/17.
 */

public class FCMMessagingService extends FirebaseMessagingService {


    private static final int NOTIFY_ID = 100;
    //private NotificationManager notificationManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

//        Intent yesIntent = getNotificationIntent();
//        yesIntent.setAction(ALLOW_ACTION);
//
//        Intent noIntent = getNotificationIntent();
//        noIntent.setAction(DENY_ACTION);

//        Notification notification = new NotificationCompat.Builder(this)
//                .setContentIntent(PendingIntent.getActivity(this, 0, getNotificationIntent(), PendingIntent.FLAG_UPDATE_CURRENT))
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setContentTitle(title)
//                .setContentText(message)
//                //.setWhen(System.currentTimeMillis())
//                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
//                //.setPriority(Notification.PRIORITY_HIGH)
//                .setAutoCancel(true)
//                .addAction(new android.support.v4.app.NotificationCompat.Action(
//                        R.mipmap.ic_thumb_up_white_36dp,
//                        "Allow",
//                        PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT)))
//                .addAction(new android.support.v4.app.NotificationCompat.Action(
//                        R.mipmap.ic_thumb_down_white_36dp,
//                        "Deny",
//                        PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT)))
//                .build();
//
//        notificationManager.notify(NOTIFY_ID, notification);
//        processIntentAction(intent);



        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(message);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        notificationBuilder.setPriority(Notification.PRIORITY_HIGH);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());

    }

//    private Intent getNotificationIntent() {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        return intent;
//    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        processIntentAction(intent);
//        super.onNewIntent(intent);
//    }

//    private void processIntentAction(Intent intent) {
//        Log.i("Intent : ", intent.getAction());
//        if (intent.getAction() != null) {
//            switch (intent.getAction()) {
//                case ALLOW_ACTION:
//                    Toast.makeText(this, "Yes :)", Toast.LENGTH_SHORT).show();
//                    break;
//                case DENY_ACTION:
//                    Toast.makeText(this, "No :(", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    }

}
