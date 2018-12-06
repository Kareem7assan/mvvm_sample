package ir.orangehat.movieinfo.bussines.persistance.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.v4.net.ConnectivityManagerCompat;

import ir.orangehat.movieinfo.bussines.model.Movie;
import ir.orangehat.movieinfo.bussines.persistance.database.dao.MovieDao;

/**
 * AppDatabase
 */

@Database(entities = Movie.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_DB = "database.db";
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, AppDatabase.class, DATABASE_DB).allowMainThreadQueries().build();
        }
        /*ConnectivityManagerCompat.RESTRICT_BACKGROUND_STATUS_DISABLED;
        ConnectivityManagerCompat.getNetworkInfoFromBroadcast().isConnected()*/
        return appDatabase;
    }

    public abstract MovieDao getMovieDao();
}
