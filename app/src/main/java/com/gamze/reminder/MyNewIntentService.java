package com.gamze.reminder;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyNewIntentService extends IntentService {
    private static final int NOTIFICATION_ID = 3;

    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("SU İÇ!");
        builder.setContentText("Bugün yeterince su içtin mi?");
        builder.setSmallIcon(R.drawable.su);
        builder.setAutoCancel(true);
        builder.setTicker("Bir yeni bildirim!");

        Intent notifyIntent = new Intent(this,SecondActivity.class);
        TaskStackBuilder TSB = TaskStackBuilder.create(this);
        TSB.addParentStack(SecondActivity.class);
        TSB.addNextIntent(notifyIntent);
        PendingIntent pendingIntent = TSB.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap bitmap_image = BitmapFactory.decodeResource(this.getResources(),R.drawable.su);
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle().bigPicture(bitmap_image);
        bigPictureStyle.setSummaryText("Yeni bildirim!");
        builder.setStyle(bigPictureStyle);

        builder.setContentIntent(pendingIntent);
        Notification notificationCompat = builder.build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notificationCompat);
    }
}
