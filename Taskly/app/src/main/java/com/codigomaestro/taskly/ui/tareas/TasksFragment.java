package com.codigomaestro.taskly.ui.tareas;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codigomaestro.taskly.databinding.FragmentTasksBinding;

import com.codigomaestro.taskly.R;
import com.codigomaestro.taskly.databinding.FragmentTasksBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TasksFragment extends Fragment {

    private FragmentTasksBinding binding;

    private int selectedDay, selectedMonth, selectedYear;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String userID = mAuth.getCurrentUser().getUid();
    private List<Map<String, Object>> tasks = new ArrayList<>();
    private String documentID;
    private TasksAdapter adapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TasksViewModel tasksViewModel =
                new ViewModelProvider(this).get(TasksViewModel.class);

        binding = FragmentTasksBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TasksAdapter(tasks);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
        fab.setImageResource(R.drawable.plus);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTasksDialog();
            }
        });

        // Load tasks from Firestore
        loadTasksFromFirestore();

        return root;
    }

    private void loadTasksFromFirestore() {
        db.collection("tareas")
                .whereEqualTo("usuario", userID)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        tasks.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map<String, Object> taskData = document.getData();
                            taskData.put("id", document.getId()); // Almacena el ID del documento
                            tasks.add(taskData);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Snackbar.make(binding.getRoot(), "Error al cargar tareas", Snackbar.LENGTH_LONG)
                                .setAction("Action", null)
                                .setAnchorView(R.id.fab).show();
                    }
                });
    }

    private void showAddTasksDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Agregar Tarea");

        // Crear un LinearLayout para contener los campos de entrada
        LinearLayout layout = new LinearLayout(requireContext());
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 40, 50, 10);

        // Campo de entrada para Nombre de Tarea
        final EditText taskNameInput = new EditText(requireContext());
        taskNameInput.setHint("Nombre de Tarea");
        layout.addView(taskNameInput);

        // Campo de entrada para Materia
        final EditText subjectInput = new EditText(requireContext());
        subjectInput.setHint("Materia");
        layout.addView(subjectInput);

        // Botón para mostrar el DatePicker
        final Button dateButton = new Button(requireContext());
        dateButton.setText("Seleccionar Fecha Límite");
        layout.addView(dateButton);

        // Campo de entrada para Descripción
        final EditText descriptionInput = new EditText(requireContext());
        descriptionInput.setHint("Descripción");
        layout.addView(descriptionInput);

        builder.setView(layout);

        builder.setPositiveButton("Agregar", (dialog, which) -> {
            String taskName = taskNameInput.getText().toString();
            String subject = subjectInput.getText().toString();
            String description = descriptionInput.getText().toString();
            String deadline = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;

            // Guardar las tareas en el HashMap y subirlas a Firestore
            Map<String, Object> task = new HashMap<>();
            task.put("usuario", userID);
            task.put("nombre", taskName);
            task.put("materia", subject);
            task.put("fecha_limite", deadline);
            task.put("descripcion", description);
            db.collection("tareas").add(task).addOnSuccessListener(documentReference -> {
                documentID = documentReference.getId();

                // Mostrar Snackbar después de obtener el documentID
                Snackbar.make(binding.getRoot(), "Tarea agregada: " + taskName + ", " + subject + ", " + deadline + ", " + description + " " + documentID, Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            });
            tasks.add(task);
            adapter.notifyItemInserted(tasks.size() - 1);
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();

        // Configurar el botón para mostrar el DatePicker en un nuevo AlertDialog
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(dateButton);
            }
        });
    }

    private void showDatePickerDialog(Button dateButton) {
        AlertDialog.Builder dateBuilder = new AlertDialog.Builder(requireContext());
        dateBuilder.setTitle("Seleccionar Fecha Límite");

        final DatePicker datePicker = new DatePicker(requireContext());
        dateBuilder.setView(datePicker);

        dateBuilder.setPositiveButton("Aceptar", (dialog, which) -> {
            selectedDay = datePicker.getDayOfMonth();
            selectedMonth = datePicker.getMonth();
            selectedYear = datePicker.getYear();
            dateButton.setText("Fecha Límite: " + selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        });

        dateBuilder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        dateBuilder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}