package com.example.centrocultural;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class AudioplayService extends Service {
    private MediaPlayer mediaPlayer;

    public static final String FILENAME = "FILENAME";
    public static final String COMMAND = "COMMAND";
    public static final String PLAY = "PLAY";
    public static final String PAUSE = "PAUSE";
    public static final String RESUME = "RESUME";
    public static final String STOP = "STOP";
    public static final String CHANNEL_ID = "AudioPlayServiceChannel";
    public static final int NOTIFICATION_ID = 1;

    public void onCreate() {
        super.onCreate();
        createNotification();
    }
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String filename = intent.getStringExtra(FILENAME);
        String command = intent.getStringExtra(COMMAND);

        Log.d("AudioplayService", "Command: " + command + ", Filename: " + filename);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Reproduciendo audio")
                .setContentText("Reproduciendo: " + filename)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(NOTIFICATION_ID, notification);

        if (command != null) {
            if (command.equals(PLAY)) {
                startForegroundService(filename);
                Log.d("AudioplayService", "Play command received");
                audioPlay(filename);
            } else if (command.equals(PAUSE)) {
                Log.d("AudioplayService", "Pause command received");
                audioPause();
            } else if (command.equals(RESUME)) {
                Log.d("AudioplayService", "Resume command received");
                audioResume();
            } else if (command.equals(STOP)) {
                Log.d("AudioplayService", "Stop command received");
                audioStop();
                stopForegroundService();
            }
        }
        return START_STICKY;
    }

    private void audioPlay(String filename) {
        if (filename != null) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            try {
                AssetFileDescriptor assetFileDescriptor = getAssets().openFd(filename);
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(
                        assetFileDescriptor.getFileDescriptor(),
                        assetFileDescriptor.getStartOffset(),
                        assetFileDescriptor.getLength()
                );
                assetFileDescriptor.close();
                mediaPlayer.prepare();
                mediaPlayer.setVolume(5f, 5f);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void audioPause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void audioResume() {
        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    private void audioStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void startForegroundService(String filename) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Audio Playback")
                .setContentText("Playing audio in background" + filename)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();
        startForeground(NOTIFICATION_ID, notification);
        audioPlay(filename);
    }

    private void stopForegroundService() {
        stopForeground(true);
        stopSelf();
    }

    private void createNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Audio Play Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null){
                manager.createNotificationChannel(serviceChannel);
            }
        }
    }
}

