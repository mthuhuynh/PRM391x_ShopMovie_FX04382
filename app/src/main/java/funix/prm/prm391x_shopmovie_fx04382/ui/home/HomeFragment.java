package funix.prm.prm391x_shopmovie_fx04382.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

import funix.prm.prm391x_shopmovie_fx04382.Movie;
import funix.prm.prm391x_shopmovie_fx04382.R;

public class HomeFragment extends Fragment {

//    private HomeViewModel homeViewModel;

    RecyclerView dataListRV;
    List<String> titles;
    List<String> prices;
    List<String> images;
    Adapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//
//            }
//        });

        dataListRV = root.findViewById(R.id.dataList);

        titles = new ArrayList<>();
        prices = new ArrayList<>();
        images = new ArrayList<>();

//        Gson gson = new Gson();
//
//        Type listType = new TypeToken<List<Movie>>() {}.getType();
//
//        List<Movie> movieList = gson.fromJson(loadFileFromClasspath(
//
//        for (Movie movie : movieList) {
//            titles.add(movie.getTitle());
//            prices.add(movie.getPrice());
//        }
//
//        // refreshing recycler view
//        adapter.notifyDataSetChanged();

        //test main
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("films");
//            ArrayList<HashMap<String, String>> formList = new ArrayList<HashMap<String, String>>();
//            HashMap<String, String> m_li;

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("Details-->", jo_inside.getString("title"));
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

//        mVehicleAdapter = new VehicleAdapter(getContext(), vehicleList, new VehicleListener());

        adapter = new Adapter(getContext(),titles, prices, images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        dataListRV.setLayoutManager(gridLayoutManager);
        dataListRV.setAdapter(adapter);

        // refreshing recycler view
        adapter.notifyDataSetChanged();

        return root;
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getResources().openRawResource(R.raw.film);
//            InputStream is = new URL("https://api.androidhive.info/json/movies_2017.json").openStream();
//            InputStream is = getActivity().getAssets().open("yourfilename.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}






//        MovieResponseList movieResponseList = new Gson.fromJson(json, MovieResponseList.class);
//
//        List<Movie> items = gson().fromJson(response.toString(), new TypeToken<List<Movie>>() {
//        }.getType());




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
//        fetchStoreItems();
//
//        return root;
//    }
//
//    /**
//     * fetching shopping item by making http call
//     */
//    private void fetchStoreItems() {
////        JsonArrayRequest request = new JsonArrayRequest(URL,
////                new Response.Listener<JSONArray>() {
////                    @Override
////                    public void onResponse(JSONArray response) {
////                        if (response == null) {
////                            Toast.makeText(getActivity(), "Couldn't fetch the store items! Pleas try again.", Toast.LENGTH_LONG).show();
////                            return;
////                        }
////
//////                        Dog[] arrayOfDogs = gson.fromJson(jsonArrayString, Dog[].class);
////
////
////                        MovieResponseList movieResponseList = new Gson.fromJson(json, MovieResponseList.class);
////
////                        List<Movie> items = new Gson().fromJson(response.toString(), new TypeToken<List<Movie>>() {
////                        }.getType());
////
////                        itemsList.clear();
////                        itemsList.addAll(items);
////
////                        // refreshing recycler view
////                        mAdapter.notifyDataSetChanged();
////                    }
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                // error in getting json
////                Log.e(TAG, "Error: " + error.getMessage());
////                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
////
////        MyApplication.getInstance().addToRequestQueue(request);
//    }
//
////
////    public String loadJSONFromAsset() {
////        String json = null;
////        try {
////            InputStream is = getContext().getAssets().open("film.json");
////            int size = is.available();
////            byte[] buffer = new byte[size];
////            is.read(buffer);
////            is.close();
////            json = new String(buffer, "UTF-8");
////        } catch (IOException ex) {
////            ex.printStackTrace();
////            return null;
////        }
////        return json;
////    }
//
//}