package com.example.jorgi.figurasaleatorias;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.emo_im_angel)
                        .setContentTitle("Figuras Aleatorias")
                        .setContentText("ir al creador de figuras >");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, MisDibujos.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(MainActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());


    }
    public void launchDrawShapes1(View clickedButton) {
        Intent activityIntent = new Intent(this, DrawShapes1.class);
        startActivity(activityIntent);
    }

    public void launchDrawShapes2(View clickedButton) {
        Intent activityIntent = new Intent(this, DrawShapes2.class);
        startActivity(activityIntent);
    }

    public void launchMisDibujos(View clickedButton) {
        Intent activityIntent = new Intent(this, MisDibujos.class);
        startActivity(activityIntent);
    }
}
