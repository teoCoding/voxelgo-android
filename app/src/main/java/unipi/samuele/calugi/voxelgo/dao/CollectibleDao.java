package unipi.samuele.calugi.voxelgo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CollectibleDao {

    @Insert
    void insert(Collectible collectible);

    @Update
    void update(Collectible collectible);

    @Delete
    void delete(Collectible collectible);

    @Query("DELETE FROM collectibles")
    void deleteAllCollectibles();

    @Query("SELECT * FROM collectibles")
    LiveData<List<Collectible>> getAllCollectibles();

}