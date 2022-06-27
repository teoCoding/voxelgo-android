package unipi.samuele.calugi.voxelgo.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import unipi.samuele.calugi.voxelgo.R;
import unipi.samuele.calugi.voxelgo.dao.Collectible;

public class CollectibleAdapter extends RecyclerView.Adapter<CollectibleAdapter.ViewHolder> {

    private List<Collectible> collectibles;
    private final Context context;

    public CollectibleAdapter(Context context) {
        this.context = context;

        this.collectibles = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_collectible, parent, false);
        return new ViewHolder(view);
    }

    @Override
    @SuppressLint("Range")
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Collectible collectible = collectibles.get(position);

        holder.titleView.setText(collectible.getCollectibleName());

        Glide.with(context)
                .load(collectible.getCollectibleImage())
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(holder.imageView);

//        holder.cardView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return collectibles.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCollectibles(List<Collectible> collectibles) {
        this.collectibles = collectibles;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

//        private final CardView cardView;
        private final TextView titleView;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            cardView = itemView.findViewById(R.id.cardViewCollectible);
            titleView = itemView.findViewById(R.id.textCollectible);
            imageView = itemView.findViewById(R.id.imageCollectible);
        }
    }
}
