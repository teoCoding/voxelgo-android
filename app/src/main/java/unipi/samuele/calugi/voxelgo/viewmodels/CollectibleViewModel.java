package unipi.samuele.calugi.voxelgo.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import unipi.samuele.calugi.voxelgo.dao.Collectible;
import unipi.samuele.calugi.voxelgo.dao.CollectibleRepository;

public class CollectibleViewModel extends AndroidViewModel {

    private CollectibleRepository repository;

    private LiveData<List<Collectible>> allCollectibles;

    public CollectibleViewModel(@NonNull Application application) {
        super(application);

        repository = new CollectibleRepository(application);
        allCollectibles = repository.getAllCollectibles();
    }

    public void insert(Collectible collectible) {
        repository.insert(collectible);
    }

    public void update(Collectible collectible) {
        repository.update(collectible);
    }

    public void delete(Collectible collectible) {
        repository.delete(collectible);
    }

    public void deleteAllCollectibles() {
        repository.deleteAllCollectibles();
    }

    public LiveData<List<Collectible>> getAllCollectibles() {
        return repository.getAllCollectibles();
    }
}
