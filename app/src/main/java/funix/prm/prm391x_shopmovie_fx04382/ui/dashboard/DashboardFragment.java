package funix.prm.prm391x_shopmovie_fx04382.ui.dashboard;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;

import funix.prm.prm391x_shopmovie_fx04382.R;
import funix.prm.prm391x_shopmovie_fx04382.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    private String urlProfilePhoto;
    private URL url = null;
    private Bitmap profilePic= null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(getActivity()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textName = binding.textName;
        final TextView textEmail = binding.textEmail;
        final ImageView ivPicURL = binding.ivPhoto;



        dashboardViewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d("fm", s);
                textName.setText(s);
            }
        });

        dashboardViewModel.getEmail().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d("fm", s);
                textEmail.setText(s);
            }
        });

        dashboardViewModel.getPicURL().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d("fm", s);

                try {
                    url = new URL(s);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                Picasso.get()
                        .load(s)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .fit()
                        .into(ivPicURL);
//                    profilePic = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                ivPicURL.setImageBitmap(profilePic);
            }
        });


        return root;
}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}