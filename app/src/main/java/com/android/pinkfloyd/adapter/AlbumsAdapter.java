package com.android.pinkfloyd.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.pinkfloyd.ListActivity;
import com.android.pinkfloyd.R;
import com.android.pinkfloyd.model.Album;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder> {

    private Bitmap bitmap;
    private Context context;
    private List<Album> albumList;

    public AlbumsAdapter(Context context, List<Album> albumList) {
        this.context = context;
        this.albumList = albumList;
    }

    @Override
    public AlbumsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new AlbumsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlbumsViewHolder holder, final int position) {

        Album album = albumList.get(position);
        final int id = album.getId();
        String album_name = album.getAlbum_name();
        int release_date = album.getRelease_date();
        String poster_path = album.getPoster_path();

        holder.textView.setText(album_name + " (" + release_date + ")");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, ListActivity.class);

                intent.putExtra("data", id);
                context.startActivity(intent);
            }
        });
        setImageAlbum(poster_path, holder);
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }


    private void setImageAlbum(String poster_path, AlbumsViewHolder holder) {
        switch (poster_path) {
            case "the_dark_side_of_the_moon":
                holder.imageView.setImageResource(R.drawable.the_dark_side_of_the_moon);
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.the_dark_side_of_the_moon);
                setColorPalette(holder);
                break;
            case "wish_you_were_here":
                holder.imageView.setImageResource(R.drawable.wish_you_were_here);
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.wish_you_were_here);
                setColorPalette(holder);
                break;
            case "animals":
                holder.imageView.setImageResource(R.drawable.animals);
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.animals);
                setColorPalette(holder);
                break;
            case "the_wall":
                holder.imageView.setImageResource(R.drawable.the_wall);
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.the_wall);
                setColorPalette(holder);
                break;
        }
    }

    private void setColorPalette(AlbumsViewHolder holder) {
        Palette generate = Palette.from(bitmap).generate();
        Palette.Swatch swatch = checkVibrantSwatch(generate);
        holder.textView.setBackgroundColor(swatch.getRgb());
        holder.textView.setTextColor(swatch.getBodyTextColor());
    }

    private Palette.Swatch checkVibrantSwatch(Palette p) {
        Palette.Swatch swatch = null;

        if ((p.getVibrantSwatch()) != null) {
            swatch = p.getVibrantSwatch();

        } else if ((p.getMutedSwatch()) != null) {
            swatch = p.getMutedSwatch();

        }
        return swatch;
    }

    public class AlbumsViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public AlbumsViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.album_cover);
            textView = itemView.findViewById(R.id.album_name);
        }
    }
}
