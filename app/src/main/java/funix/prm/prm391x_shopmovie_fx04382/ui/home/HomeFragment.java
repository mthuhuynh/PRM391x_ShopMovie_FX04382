package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import funix.prm.prm391x_shopmovie_fx04382.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    RecyclerView dataList;
    List<String> titles;
    List<String> prices;
    //    List<String> images;
    Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        dataList = root.findViewById(R.id.dataList);

        titles = new ArrayList<>();
        prices = new ArrayList<>();



//        images = new ArrayList<>();

//        try {
//            // get JSONObject from JSON file
//            JSONObject obj = new JSONObject(loadJSONFromAsset());
//            // fetch JSONArray named users
//            JSONArray filmArray = obj.getJSONArray("films");
//            // implement for loop for getting users list data
//            for (int i = 0; i < filmArray.length(); i++) {
//                // create a JSONObject for fetching single user data
//                JSONObject filmDetail = filmArray.getJSONObject(i);
//                titles.add(filmDetail.getString("title"));
//                prices.add(filmDetail.getString("price"));
////                images.add(filmDetail.getString("image"));
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//
////
////        titles.add("First Item");
////        titles.add("Second Item");
////        titles.add("Third Item");
////        titles.add("Fourth Item");
////        titles.add("First Item");
////        titles.add("Second Item");
////        titles.add("Third Item");
////        titles.add("Fourth Item");
////
////        prices.add("1");
////        prices.add("1");
////        prices.add("1");
////        prices.add("1");
////        prices.add("1");
////        prices.add("1");
////        prices.add("1");
////        prices.add("1");
////
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
////        images.add(R.drawable.ic_launcher_background);
//
//
//        adapter = new Adapter(getContext(),titles, prices);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
//        dataList.setLayoutManager(gridLayoutManager);
//        dataList.setAdapter(adapter);
//
        return root;
    }
//
//    public String loadJSONFromAsset() {
//        String json = null;
//        try {
//            InputStream is = getContext().getAssets().open("film.json");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            json = new String(buffer, "UTF-8");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            return null;
//        }
//        return json;
//    }

}