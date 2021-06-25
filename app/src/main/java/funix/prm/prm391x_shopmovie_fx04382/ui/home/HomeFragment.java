package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import funix.prm.prm391x_shopmovie_fx04382.MainActivity;
import funix.prm.prm391x_shopmovie_fx04382.Movie;
import funix.prm.prm391x_shopmovie_fx04382.R;

public class HomeFragment extends Fragment {

    RecyclerView dataListRV;
    List<Movie> movies;
    Adapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

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
            Bitmap b = getBitmapFromURL(movie.getImage());
            Log.d("movie selected", "homefragment");
            if (b != null) {
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(b)
                        .setCaption("Shop Movie - FX04382")
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo).build();
                ShareDialog.show(HomeFragment.this, content);
            }
        }
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
