package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.CallbackManager;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import funix.prm.prm391x_shopmovie_fx04382.Movie;
import funix.prm.prm391x_shopmovie_fx04382.R;

public class HomeFragment extends Fragment {

    RecyclerView dataListRV;
    List<Movie> movies;
    Adapter adapter;
    ImageHelper imageHelper;

    Bitmap bitmap;
    ImageView imageView;

    CallbackManager callbackManager;
    ShareDialog shareDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        // this part is optional
//        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() { ... });

        dataListRV = root.findViewById(R.id.dataList);

        movies = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray jArr = obj.getJSONArray("films");

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject json = jArr.getJSONObject(i);

                //Add values in Movie `ArrayList` as below:
                movies.add(new Movie(json.getString("title"), json.getString("image"), json.getString("price")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new Adapter(getContext(), movies, new MovieListener());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataListRV.setLayoutManager(gridLayoutManager);
        dataListRV.setAdapter(adapter);

        // refreshing recycler view
        adapter.notifyDataSetChanged();

        return root;
    }

    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = getResources().openRawResource(R.raw.film);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class MovieListener implements Adapter.MovieAdapterListener {

        @Override
        public void onMovieSelected(Movie movie, View view) {
            Log.d("movie selected", "homefragment");

            ImageLoadAsyncTask imageLoadAsyncTask = new ImageLoadAsyncTask();
            imageLoadAsyncTask.execute(new String[]{movie.getImage()});
            Log.d("movie getImg", "homefragment");
        }
    }

    public class ImageLoadAsyncTask extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                // Sets the flag indicating whether this URLConnection allows input.
                connection.setDoInput(true);
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap == null)
                Log.d("imageAsync", "Can't get image from url");

            if (bitmap != null) {
                Log.d("movie getImg != null", "homefragment");

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .setCaption("Shop Movie - FX04382")
                        .build();
                Log.d("movie sharephoto", String.valueOf(photo != null));

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo).build();
                Log.d("movie sharephotocontent", String.valueOf(content != null));

                if (ShareDialog.canShow(SharePhotoContent.class)) {
                    Log.d("sharedialog", "can show");
                    shareDialog.show(content);

                }

            }
        }
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
