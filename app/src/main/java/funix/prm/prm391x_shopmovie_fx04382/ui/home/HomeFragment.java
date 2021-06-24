package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import funix.prm.prm391x_shopmovie_fx04382.R;

public class HomeFragment extends Fragment {

    RecyclerView dataListRV;
    List<String> titles;
    List<String> prices;
    List<String> images;
    Adapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        dataListRV = root.findViewById(R.id.dataList);

        titles = new ArrayList<>();
        prices = new ArrayList<>();
        images = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArray = obj.getJSONArray("films");

            for (int i = 0; i < m_jArray.length(); i++) {
                JSONObject jo_inside = m_jArray.getJSONObject(i);
                String title_value = jo_inside.getString("title");
                String price_value = jo_inside.getString("price");
                String image_value = jo_inside.getString("image");

                //Add your values in your `ArrayList` as below:
                titles.add(title_value);
                prices.add(price_value);
                images.add(image_value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new Adapter(getContext(), titles, prices, images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
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
        public void onMovieSelected() {
            Log.d("on click ", "poster");
            Bitmap image = BitmapFactory.decodeResource(requireContext().getResources(),
                    R.drawable.ic_launcher_background);
            SharePhoto photo = new SharePhoto.Builder()
                    .setBitmap(image)
                    .build();
            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(photo)
                    .build();
        }
    }
}
//            /*
//             * Using Navigation object we find navigation controller with view then we will call
//             * navigate with it's action name and pass argument to open correct item. You can change
//             * this action name
//             */
//            Navigation.findNavController(view).navigate(VehiclesFragmentDirections.actionVehiclesFragmentToVehicleDetailFragment(vehicle));

