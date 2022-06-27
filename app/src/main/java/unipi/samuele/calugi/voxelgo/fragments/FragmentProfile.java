package unipi.samuele.calugi.voxelgo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import unipi.samuele.calugi.voxelgo.R;
import unipi.samuele.calugi.voxelgo.adapters.CollectibleAdapter;
import unipi.samuele.calugi.voxelgo.dao.Collectible;
import unipi.samuele.calugi.voxelgo.viewmodels.CollectibleViewModel;

public class FragmentProfile extends Fragment {

//    private SQLiteDatabase database;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DatabaseHandler databaseHandler = DatabaseHandler.getInstance(getContext());
//        database = databaseHandler.getReadableDatabase();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RecyclerView collectibles = requireView().findViewById(R.id.recyclerViewCollectibles);
//        collectibles.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        collectibles.setAdapter(new CursorAdapter(getContext(), getAllItems()));


        RecyclerView collectibles = requireView().findViewById(R.id.recyclerViewCollectibles);
        collectibles.setLayoutManager(new GridLayoutManager(getContext(), 3));

        CollectibleAdapter adapter = new CollectibleAdapter(getContext());
        collectibles.setAdapter(adapter);

        CollectibleViewModel viewModel = new ViewModelProvider(this).get(CollectibleViewModel.class);
        viewModel.getAllCollectibles().observe(getViewLifecycleOwner(), new Observer<List<Collectible>>() {
            @Override
            public void onChanged(List<Collectible> collectibles) {
                adapter.setCollectibles(collectibles);
            }
        });

//
//        MainViewModel viewModel = new ViewModelProvider(this, new MainViewModelFactory(database)).get(MainViewModel.class);
//        viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Cursor>() {
//            @Override
//            public void onChanged(Cursor newCursor) {
//                adapter.updateCursor(newCursor);
//            }
//        });
    }

//    private Cursor getAllItems() {
//        return database.rawQuery("SELECT * FROM collectibles", null);
//    }
}
