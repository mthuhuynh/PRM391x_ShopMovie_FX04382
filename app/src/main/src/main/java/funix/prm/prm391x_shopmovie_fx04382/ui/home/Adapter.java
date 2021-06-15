package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

import funix.prm.prm391x_shopmovie_fx04382.R;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> titles;
    List<String> prices;
//    List<String> images;
    LayoutInflater inflater;

    public Adapter(Context ctx, List<String> titles, List<String> prices) {
        this.titles = titles;
        this.prices = prices;
//        this.images = images;
        this.inflater = LayoutInflater.from(ctx);
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
//        holder.gridIcon.setTag(images.get(position));

    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView price;
//        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            price = itemView.findViewById(R.id.textView_price);

//            new LoadImage(gridIcon).execute();

//            gridIcon = itemView.findViewById(R.id.imageView2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    class LoadImage extends AsyncTask<Object, Void, Bitmap> {
//        ImageView imageView;
//        private String path;
//
//        public LoadImage(ImageView imageView) {
//            this.imageView = imageView;
//            Toast.makeText(imageView.getContext(), "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
//        }
//
//        protected Bitmap doInBackground(Object... params) {
//            String imageURL = images.get(0);
//            Bitmap bimage = null;
//
//            path = imageView.getTag().toString();
//            try {
//                InputStream in = new java.net.URL(imageURL).openStream();
//                bimage = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.e("Error Message", e.getMessage());
//                e.printStackTrace();
//            }
//            return bimage;
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            if (result != null && imageView != null) {
//                imageView.setImageBitmap(result);
//            }else{
//                imageView.setImageResource(R.drawable.ic_dashboard_black_24dp);
//            }
//
//        }
//    }
}















//
//        private ImageView imv;
//        private String path;
//
//
//        @Override
//        protected Bitmap doInBackground(Object... params) {
//            String urldisplay = urls[0];
//            Bitmap bmp = null;
//
//            path = imv.getTag().toString();
//
//            Bitmap bitmap = null;
//            File file = new File(
//                    Environment.getExternalStorageDirectory().getAbsolutePath() + path);
//
//            if(file.exists()){
//                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            }
//
//            //String urldisplay = urls[0];
//            //      Bitmap mIcon11 = null;
//            //      try {
//            //        InputStream in = new java.net.URL(urldisplay).openStream();
//            //        mIcon11 = BitmapFactory.decodeStream(in);
//            //      } catch (Exception e) {
//            //          Log.e("Error", e.getMessage());
//            //          e.printStackTrace();
//            //      }
//            //Bitmap bmp = null;
//            //try {
//            //URL url = new URL("https://parallelcodes.com/wp-content/uploads/2018/06/im-500821018.jpg");
//            //bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//            //} catch (Exception e) {
//            //Log.e("Error", e.getMessage());
//            //e.printStackTrace();
//            //bmp = null;
//            //}
//
//            return bitmap;
//        }
//        @Override
//        protected void onPostExecute(Bitmap result) {
//            if(result != null && imv != null){
//                imv.setVisibility(View.VISIBLE);
//                imv.setImageBitmap(result);
//            }else{
//                imv.setVisibility(View.GONE);
//            }
//        }
//    }
//}
