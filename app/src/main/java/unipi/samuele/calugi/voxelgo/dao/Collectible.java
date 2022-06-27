package unipi.samuele.calugi.voxelgo.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "collectibles")
public class Collectible {

    @PrimaryKey(autoGenerate = true)
    private int collectibleID;

    @ColumnInfo(name = "collectible_name")
    private final String collectibleName;

    @ColumnInfo(name = "collectible_model")
    private final String collectibleModel;

    @ColumnInfo(name = "collectible_image")
    private final String collectibleImage;

    @ColumnInfo(name = "collectible_rarity")
    private final int collectibleRarity;

    public Collectible(String collectibleName, String collectibleModel, String collectibleImage, int collectibleRarity) {
        this.collectibleName = collectibleName;
        this.collectibleModel = collectibleModel;
        this.collectibleImage = collectibleImage;
        this.collectibleRarity = collectibleRarity;
    }

    public void setCollectibleID(int collectibleID) {
        this.collectibleID = collectibleID;
    }

    public int getCollectibleID() {
        return collectibleID;
    }

    public String getCollectibleName() {
        return collectibleName;
    }

    public String getCollectibleModel() {
        return collectibleModel;
    }

    public String getCollectibleImage() {
        return collectibleImage;
    }

    public int getCollectibleRarity() {
        return collectibleRarity;
    }
}