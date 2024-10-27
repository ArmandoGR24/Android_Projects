package com.codigomaestro.taskly.ui.complete;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codigomaestro.taskly.databinding.FragmentTasksBinding;
import com.codigomaestro.taskly.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompleteFragment extends Fragment {

    private FragmentTasksBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private String userID;
    private List<Map<String, Object>> tasks = new ArrayList<>();
    private CompleteAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CompleteViewModel completeViewModel = new ViewModelProvider(this).get(CompleteViewModel.class);

        binding = FragmentTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser() != null ? mAuth.getCurrentUser().getUid() : null;

        if (userID == null) {
            // Handle the case where the user is not authenticated
            Snackbar.make(root, "User not authenticated", Snackbar.LENGTH_LONG).show();
            return root;
        }

        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CompleteAdapter(tasks);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        if (fab != null) {
            fab.setImageResource(R.drawable.plus);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAddTasksDialog();
                }
            });
        }

        loadTasksFromFirestore();

        return root;
    }

    private void loadTasksFromFirestore() {
        if (userID == null) return;

        db.collection("deleted_tasks")
                .whereEqualTo("userID", userID)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        tasks.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            tasks.add(document.getData());
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Snackbar.make(binding.getRoot(), "Error getting tasks", Snackbar.LENGTH_LONG).show();
                    }
                });
    }

    private void showAddTasksDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add Task");

        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText descriptionInput = new EditText(requireContext());
        descriptionInput.setHint("Description");
        layout.addView(descriptionInput);

        final Button dateButton = new Button(requireContext());
        dateButton.setText("Select Date");
        layout.addView(dateButton);

        builder.setView(layout);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String description = descriptionInput.getText().toString();
            // Add task to Firestore
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();

        dateButton.setOnClickListener(v -> showDatePickerDialog(dateButton));
    }

    private void showDatePickerDialog(Button dateButton) {
        AlertDialog.Builder dateBuilder = new AlertDialog.Builder(requireContext());
        dateBuilder.setTitle("Select Due Date");

        final DatePicker datePicker = new DatePicker(requireContext());
        dateBuilder.setView(datePicker);

        dateBuilder.setPositiveButton("OK", (dialog, which) -> {
            // Set selected date to dateButton
        });

        dateBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        dateBuilder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}