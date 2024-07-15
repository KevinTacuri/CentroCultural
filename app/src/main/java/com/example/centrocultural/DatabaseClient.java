/*package com.example.centrocultural;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {
    private Context context;
    private static DatabaseClient instance;
    private AppDatabase database;

    private DatabaseClient(Context context) {
        this.context = context;
        database = Room.databaseBuilder(context, AppDatabase.class, "CentroCulturalDB").build();
    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}*/
