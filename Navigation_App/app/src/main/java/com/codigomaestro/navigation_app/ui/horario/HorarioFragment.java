package com.codigomaestro.navigation_app.ui.horario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.codigomaestro.navigation_app.databinding.FragmentHorarioBinding;

public class HorarioFragment extends Fragment {

    private FragmentHorarioBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HorarioViewModel horarioViewModel =
                new ViewModelProvider(this).get(HorarioViewModel.class);

        binding = FragmentHorarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}