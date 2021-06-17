package funix.prm.prm391x_shopmovie_fx04382.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import funix.prm.prm391x_shopmovie_fx04382.R;
import funix.prm.prm391x_shopmovie_fx04382.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(getActivity()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textName = binding.textName;
        final TextView textEmail = binding.textEmail;
        final ImageView ivPicURL = binding.ivPhoto;

        dashboardViewModel.getName().observe(getViewLifecycleOwner(), textName::setText);

        dashboardViewModel.getEmail().observe(getViewLifecycleOwner(), textEmail::setText);

        dashboardViewModel.getPicURL().observe(getViewLifecycleOwner(), s -> {
            if(s != null) {
                Picasso.get()
                        .load(s)
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .fit()
                        .into(ivPicURL);
            } else {
                ivPicURL.setImageResource(R.drawable.ic_launcher_background);
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