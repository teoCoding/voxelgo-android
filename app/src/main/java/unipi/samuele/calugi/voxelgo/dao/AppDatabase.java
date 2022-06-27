package unipi.samuele.calugi.voxelgo.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import unipi.samuele.calugi.voxelgo.utils.VoxelUtils;

@Database(entities = {Collectible.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase appDatabase;

    public abstract CollectibleDao collectibleDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    VoxelUtils.DATABASE_NAME
            ).fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return appDatabase;
    }

    private static final RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(appDatabase).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private final CollectibleDao collectibleDao;

        private PopulateDbAsyncTask(AppDatabase appDatabase) {
            collectibleDao = appDatabase.collectibleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            collectibleDao.insert(new Collectible("Nome1", "Modello1", "Immagine1", 0));
            collectibleDao.insert(new Collectible("Nome2", "Modello2", "Immagine2", 0));
            collectibleDao.insert(new Collectible("Nome3", "Modello3", "Immagine3", 0));

            return null;
        }
    }
}