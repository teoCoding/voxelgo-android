package unipi.samuele.calugi.voxelgo.dao;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CollectibleRepository {

    private final CollectibleDao collectibleDao;

    private final LiveData<List<Collectible>> allCollectibles;

    public CollectibleRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        collectibleDao = database.collectibleDao();
        allCollectibles = collectibleDao.getAllCollectibles();
    }

    public void insert(Collectible collectible) {
        new InsertCollectibleAsyncTask(collectibleDao).execute(collectible);
    }

    public void update(Collectible collectible) {
        new UpdateCollectibleAsyncTask(collectibleDao).execute(collectible);
    }

    public void delete(Collectible collectible) {
        new DeleteCollectibleAsyncTask(collectibleDao).execute(collectible);
    }

    public void deleteAllCollectibles() {
        new DeleteAllCollectiblesAsyncTask(collectibleDao).execute();
    }

    public LiveData<List<Collectible>> getAllCollectibles() {
        return allCollectibles;
    }

    private static class InsertCollectibleAsyncTask extends AsyncTask<Collectible, Void, Void> {

        private final CollectibleDao collectibleDao;

        private InsertCollectibleAsyncTask(CollectibleDao collectibleDao) {
            this.collectibleDao = collectibleDao;
        }

        @Override
        protected Void doInBackground(Collectible... collectibles) {
            collectibleDao.insert(collectibles[0]);
            return null;
        }
    }

    private static class UpdateCollectibleAsyncTask extends AsyncTask<Collectible, Void, Void> {

        private final CollectibleDao collectibleDao;

        private UpdateCollectibleAsyncTask(CollectibleDao collectibleDao) {
            this.collectibleDao = collectibleDao;
        }

        @Override
        protected Void doInBackground(Collectible... collectibles) {
            collectibleDao.update(collectibles[0]);
            return null;
        }
    }

    private static class DeleteCollectibleAsyncTask extends AsyncTask<Collectible, Void, Void> {

        private final CollectibleDao collectibleDao;

        private DeleteCollectibleAsyncTask(CollectibleDao collectibleDao) {
            this.collectibleDao = collectibleDao;
        }

        @Override
        protected Void doInBackground(Collectible... collectibles) {
            collectibleDao.delete(collectibles[0]);
            return null;
        }
    }

    private static class DeleteAllCollectiblesAsyncTask extends AsyncTask<Void, Void, Void> {

        private final CollectibleDao collectibleDao;

        private DeleteAllCollectiblesAsyncTask(CollectibleDao collectibleDao) {
            this.collectibleDao = collectibleDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            collectibleDao.deleteAllCollectibles();
            return null;
        }
    }
}
