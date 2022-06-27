package unipi.samuele.calugi.voxelgo.activities;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaCodecInfo;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import unipi.samuele.calugi.voxelgo.R;
import unipi.samuele.calugi.voxelgo.dao.Collectible;
import unipi.samuele.calugi.voxelgo.dao.CollectibleRepository;
import unipi.samuele.calugi.voxelgo.fragments.FragmentHome;
import unipi.samuele.calugi.voxelgo.fragments.FragmentMap;
import unipi.samuele.calugi.voxelgo.fragments.FragmentProfile;
import unipi.samuele.calugi.voxelgo.threads.DownloadThread;
import unipi.samuele.calugi.voxelgo.utils.ApiResponse;
import unipi.samuele.calugi.voxelgo.utils.VoxelUtils;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Thread thread = new Thread(() -> {
//            AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
//            CollectibleDao collectibleDao = appDatabase.collectibleDao();
//            List<Collectible> collectibles = collectibleDao.getAll();
//        });
//        thread.start();

//        Intent intent = new Intent(this, DownloadService.class);
//        startForegroundService(intent);

//        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getApplicationContext());
//        SQLiteDatabase sqLiteDatabase = databaseHandler.getWritableDatabase();
//        Thread downloadThread = new Thread(new DownloadThread(sqLiteDatabase));
//        downloadThread.start();

        Thread thread = new Thread(this::addCollectibles);
        thread.start();


        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            replaceFragment(FragmentHome.class, "fragment_home");
        } else {
            String fragmentTag = savedInstanceState.getString("fragment_tag");
            Class<? extends Fragment> fragmentClass = fragmentManager.findFragmentByTag(fragmentTag).getClass();
            replaceFragment(fragmentClass, fragmentTag);
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);

        try {
            File httpCacheDir = new File(getCacheDir(), "http");
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        HttpResponseCache cache = HttpResponseCache.getInstalled();
        if (cache != null) {
            cache.flush();
        }
    }

    @Override
    @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.buttonHome:
//                replaceFragment(FragmentHome.class, "fragment_home");
//                break;
            case R.id.buttonExplore:
                replaceFragment(FragmentMap.class, "fragment_map");
                break;
            case R.id.buttonProfile:
                replaceFragment(FragmentProfile.class, "fragment_profile");
                break;
            default:
                return false;
        }

        return true;
    }

    private void replaceFragment(Class<? extends Fragment> fragment, String fragmentTag) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, null, fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        outState.putString("fragment_tag", fragment.getTag());
    }


    private void addCollectibles() {

//        try {
//            Thread.sleep(5000L);
//        } catch (Exception e){
//            Log.e("VoxelGO", e.toString());
//        }
//
//        try {
//            int responseDatabaseVersion = Integer.parseInt(IOUtils.toString(URI.create(VoxelUtils.DATABASE_VERSION_URL), StandardCharsets.UTF_8).trim());
//
//            if (responseDatabaseVersion != 2) {
//                String responseCollectibles = IOUtils.toString(URI.create(VoxelUtils.DATABASE_COLLECTIONS_URL), StandardCharsets.UTF_8);
//                ApiResponse apiResponse = new Gson().fromJson(responseCollectibles, ApiResponse.class);
//
//                if (apiResponse.getCode().equals("SUCCESS")) {
//
//                    ApiResponse.Model[] models = apiResponse.getModels();
//
//                    CollectibleRepository repository = new CollectibleRepository(getApplication());
//
//                    for (ApiResponse.Model model : models) {
//                        repository.insert(new Collectible(
//                            model.getModel_name(),
//                            "https://voxel.frangioniwebdev.com/?model=" + model.getModel_url_name(),
//                            "https://cdn.discordapp.com/attachments/606422186923917322/982390254847795270/unknown.png",
//                            model.getModel_rarity())
//                        );
//                    }
//                }
//            }
//        } catch (IOException exception) {
//            Log.e("VoxelGO", exception.toString());
//        }
//        Log.e("VoxelGO", "DONE!");
    }
}


//        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

//        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getApplicationContext());
//        SQLiteDatabase database = databaseHandler.getWritableDatabase();
//
//        DatabaseUpdater filesTask = new DatabaseUpdater(database, progressBar);
//        filesTask.execute(
//            "https://backend.voxel.frangioniwebdev.com/api.php?endpoint=models/list",
//            "https://backend.voxel.frangioniwebdev.com/db-version"
//        );


//        fragmentTransaction.setCustomAnimations(
//                        R.anim.slide_in,  // enter
//                        R.anim.fade_out,  // exit
//                        R.anim.fade_in,   // popEnter
//                        R.anim.slide_out  // popExit
//                );
