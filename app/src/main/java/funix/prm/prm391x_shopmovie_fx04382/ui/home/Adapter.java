package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import funix.prm.prm391x_shopmovie_fx04382.MainActivity;
import funix.prm.prm391x_shopmovie_fx04382.Movie;
import funix.prm.prm391x_shopmovie_fx04382.R;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Movie> movies;
    LayoutInflater inflater;

    private MovieAdapterListener mListener;


    public Adapter(Context ctx, List<Movie> movies, MovieAdapterListener listener) {
        this.movies = movies;
        this.inflater = LayoutInflater.from(ctx);
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.title.setText(movie.getTitle());
        holder.price.setText(movie.getPrice());

        if(movie.getImage() != null) {
            Picasso.get()
                    .load(movie.getImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_title);
            price = itemView.findViewById(R.id.textView_price);
            image = itemView.findViewById(R.id.imageView_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("movie onclick", "adapter");
                    mListener.onMovieSelected(movies.get(getAdapterPosition()), itemView);
                }
            });
        }
    }

    /**
     * The interface that receives onClick listener.
     */
    public interface MovieAdapterListener {
        void onMovieSelected(Movie movie, View view);
    }
}