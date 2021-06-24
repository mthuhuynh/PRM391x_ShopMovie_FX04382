package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.squareup.picasso.Picasso;

import java.util.List;

import funix.prm.prm391x_shopmovie_fx04382.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<String> prices;
    List<String> images;
    LayoutInflater inflater;
    private MovieAdapterListener mListener;

    public Adapter(Context ctx, List<String> titles, List<String> prices, List<String> images) {
        this.titles = titles;
        this.prices = prices;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);
    }

    /**
     * The interface that receives onClick listener.
     */
    public interface MovieAdapterListener {
        void onMovieSelected();
//        void onMovieSelected(Movie movie, View view);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(titles.get(position));
        holder.price.setText(prices.get(position));

        if (images.get(position) != null) {
            Picasso.get()
                    .load(images.get(position))
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return titles.size();
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

            mListener.onMovieSelected();
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (itemView.getId() == R.id.imageView_poster) {
//                        Log.d("on click ", "poster");
//                        Bitmap image = BitmapFactory.decodeResource(itemView.getContext().getResources(),
//                                R.drawable.ic_launcher_background);
//                        SharePhoto photo = new SharePhoto.Builder()
//                                .setBitmap(image)
//                                .build();
//                        SharePhotoContent content = new SharePhotoContent.Builder()
//                                .addPhoto(photo)
//                                .build();
//                    }
//                }
//            });
        }
    }
}

//    public void onClick(View v) {
//        if(v.getId() == R.id.imageView_poster) {
//            Log.d("on click ", "poster");
//            Bitmap image = BitmapFactory.decodeResource(v.getContext().getResources(),
//                    R.drawable.ic_launcher_background);
//            SharePhoto photo = new SharePhoto.Builder()
//                    .setBitmap(image)
//                    .build();
//            SharePhotoContent content = new SharePhotoContent.Builder()
//                    .addPhoto(photo)
//                    .build();
//        }
//    }

//    /**
//     * Share Photo
//     * @param b Hình ảnh dạng bitmap
//     * @param caption thêm caption
//     */
//    public static void sharePhoto(Bitmap b, String caption) {
//        SharePhoto photo = new SharePhoto.Builder()
//                .setBitmap(b)
//                .setCaption(caption)
//                .build();
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo).build();
////        ShareDialog.show(LoginActivity, content);
//    }
//}

