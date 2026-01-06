package com.example.avnifinalyb;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyWinAudioService extends Service {
    private static final String CHANNEL_ID = "MyWinAudioServiceChannel";
    private MediaPlayer mediaPlayer;
    public static final String ACTION_PLAY = "com.example.avnifinalyb.ACTION_PLAY";
    public static final String ACTION_STOP = "com.example.avnifinalyb.ACTION_STOP";

    public MyWinAudioService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MediaService", "onCreate called");
        mediaPlayer = MediaPlayer.create(this, R.raw.victory_music);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
        } else {
            Log.e("MediaService", "Error creating MediaPlayer");
            stopSelf();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    Log.d("MediaService", "Received Play Intent");
                    createNotificationChannel();

                    Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                            .setContentTitle("Music Playing")
                            .setContentText("Your song is playing")
                            .setSmallIcon(android.R.drawable.ic_media_play)
                            .build();

                    startForeground(1, notification);
                    if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                        mediaPlayer.start();
                    }
                    break;
                case ACTION_STOP:
                    Log.d("MediaService", "Received Stop Intent");
                    stopSelf(); // This will trigger onDestroy
                    break;
            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        Log.d("MediaService", "onDestroy called");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Media Playback Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            serviceChannel.setSound(null,null); // Disable sound for this channel

            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(serviceChannel);
                Log.d("MediaService", "Notification channel ''' + CHANNEL_ID + ''' created with importance DEFAULT.");
            } else {
                Log.e("MediaService", "NotificationManager is null, channel not created.");
            }
        }
    }
}
