package unipi.samuele.calugi.voxelgo.threads;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import unipi.samuele.calugi.voxelgo.utils.ApiResponse;
import unipi.samuele.calugi.voxelgo.utils.VoxelUtils;

public class DownloadThread implements Runnable {

    private final SQLiteDatabase sqLiteDatabase;
    private final String databaseCollectiblesURL;
    private final String databaseVersionURL;

    public DownloadThread(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
        this.databaseCollectiblesURL = VoxelUtils.DATABASE_COLLECTIONS_URL;
        this.databaseVersionURL = VoxelUtils.DATABASE_VERSION_URL;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000L);
        } catch (Exception e){
            Log.e("VoxelGO", e.toString());
        }

        Log.e("AAAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAAAAAAAAAAAA");

        try {
            int responseDatabaseVersion = Integer.parseInt(IOUtils.toString(URI.create(databaseVersionURL), StandardCharsets.UTF_8).trim());

            if (responseDatabaseVersion != 2) {
                String responseCollectibles = IOUtils.toString(URI.create(databaseCollectiblesURL), StandardCharsets.UTF_8);
                ApiResponse apiResponse = new Gson().fromJson(responseCollectibles, ApiResponse.class);

                if (apiResponse.getCode().equals("SUCCESS")) {

                    ApiResponse.Model[] models = apiResponse.getModels();
                    ContentValues values = new ContentValues();

                    for (ApiResponse.Model model : models) {
                        values.put("collectible_name", model.getModel_name());
                        values.put("collectible_model", "https://voxel.frangioniwebdev.com/?model=" + model.getModel_url_name());
                        values.put("collectible_image", "https://cdn.discordapp.com/attachments/606422186923917322/982390254847795270/unknown.png");
                        values.put("collectible_rarity", model.getModel_rarity());
                    }
                    sqLiteDatabase.insert("collectibles", null, values);
                }
            }
        } catch (IOException exception) {
            Log.e("VoxelGO", exception.toString());
        }
        Log.e("VoxelGO", "DONE!");
    }
}
