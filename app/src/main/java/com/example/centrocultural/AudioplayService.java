package com.example.centrocultural;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

    private String currentFilename;

    @Override
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

        if (filename != null && !filename.isEmpty()) {
            currentFilename = filename;
        }

        if (command != null) {
            switch (command) {
                case PLAY:
                    audioPlay(currentFilename);
                    updateNotification(currentFilename, true, false);
                    break;
                case PAUSE:
                    audioPause();
                    updateNotification(currentFilename, false, true);
                    break;
                case RESUME:
                    audioResume();
                    updateNotification(currentFilename, true, false);
                    break;
                case STOP:
                    audioStop();
                    updateNotification(currentFilename, false, false);
                    break;
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
        PendingIntent playPendingIntent = createPendingIntent(PLAY, 0);
        PendingIntent pausePendingIntent = createPendingIntent(PAUSE, 1);
        PendingIntent stopPendingIntent = createPendingIntent(STOP, 2);
        PendingIntent contentPendingIntent = createContentPendingIntent();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Reproduciendo audio")
                .setContentText("Reproduciendo: " + filename)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .addAction(R.drawable.ic_launcher_foreground, "Play", playPendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "Pause", pausePendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "Stop", stopPendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Reproduciendo: " + filename))
                .setContentIntent(contentPendingIntent)
                .build();

        startForeground(NOTIFICATION_ID, notification);
        audioPlay(filename);
    }

    private void updateNotification(String filename, boolean isPlaying, boolean isPaused) {
        PendingIntent playPendingIntent = createPendingIntent(PLAY, 0);
        PendingIntent pausePendingIntent = createPendingIntent(PAUSE, 1);
        PendingIntent resumePendingIntent = createPendingIntent(RESUME, 2);
        PendingIntent stopPendingIntent = createPendingIntent(STOP, 3);
        PendingIntent contentPendingIntent = createContentPendingIntent();

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Reproduciendo audio")
                .setContentText("Reproduciendo: " + filename)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .addAction(R.drawable.ic_launcher_foreground, "Play", playPendingIntent);

        if (isPlaying) {
            notificationBuilder.addAction(R.drawable.ic_launcher_foreground, "Pause", pausePendingIntent);
        } else if (isPaused) {
            notificationBuilder.addAction(R.drawable.ic_launcher_foreground, "Resume", resumePendingIntent);
        }

        notificationBuilder.addAction(R.drawable.ic_launcher_foreground, "Stop", stopPendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Reproduciendo: " + filename))
                .setContentIntent(contentPendingIntent);


        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notificationBuilder.build());
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

    private PendingIntent createPendingIntent(String command, int requestCode) {
        Intent intent = new Intent(this, AudioplayService.class);
        intent.putExtra(COMMAND, command);
        intent.putExtra(FILENAME, currentFilename);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        return PendingIntent.getService(this, requestCode, intent, flags);
    }
    private PendingIntent createContentPendingIntent() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("fragment", "PaintingDetailFragment");
        intent.putExtra(FILENAME, currentFilename);
        int flags = PendingIntent.FLAG_UPDATE_CURRENT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            flags |= PendingIntent.FLAG_IMMUTABLE;
        }
        return PendingIntent.getActivity(this, 4, intent, flags);
    }
}
