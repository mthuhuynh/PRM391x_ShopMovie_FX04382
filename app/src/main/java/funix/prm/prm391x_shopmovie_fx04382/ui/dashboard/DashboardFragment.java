package funix.prm.prm391x_shopmovie_fx04382.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import java.time.DayOfWeek;

import funix.prm.prm391x_shopmovie_fx04382.R;
import funix.prm.prm391x_shopmovie_fx04382.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(getActivity()).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textName = binding.textName;
        final TextView textEmail = binding.textEmail;
        String urlProfilePhoto;

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

        dashboardViewModel.getId().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d("fm", s);
                urlProfilePhoto = "";
            }
        });


        return root;
}

//        DetailFragmentViewModel viewModel = ViewModelProviders.of(this, factory).get(DetailFragmentViewModel.class);
//
//        viewModel.getSelectedVehicle().observe(this, new Observer<Vehicle>() {
//            @Override
//            public void onChanged(Vehicle vehicle) {
//                vehicleImageView.setImageResource(vehicle.getVehicleImage());
//                vehicleNameTextView.setText(vehicle.getVehicleName());
//            }
//        });


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}