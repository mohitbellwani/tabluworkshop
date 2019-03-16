package com.example.noti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //1. Notification Channel
    private static final String ch1="sim_coding1";
    private static final String chNM="sim_coding";
    private static final String chdesc="sim coding Notification";
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel= new NotificationChannel(ch1,chNM, NotificationManager.IMPORTANCE_LOW);
            channel.setDescription(chdesc);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Button btn=findViewById(R.id.btnnotify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displaynotification();
            }
        });
    }
    private void displaynotification()
    {
        NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(this,ch1)
                .setSmallIcon(R.drawable.ic_action_attach)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("New Episode of Fairy tail is here")
                .setContentText("Fairy tail final season episode 24")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_DEFAULT)
                ;
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        mbuilder.setContentIntent(pendingIntent);

        //You can use Notification Manager OR NotificationManagerCompat

        NotificationManager mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(1,mbuilder.build());

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mbuilder.build());
    }
}
