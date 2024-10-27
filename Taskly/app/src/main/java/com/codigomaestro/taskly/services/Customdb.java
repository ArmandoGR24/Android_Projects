package com.codigomaestro.taskly.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class Customdb {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser();

    public void Task_Completed(String task_id) {
        db.collection("taskCompleted").add(new HashMap<String, Object>() {{
            put("completed", true);
            put("task_id", task_id);
            put("user_id", user.getUid());
        }});
        db.collection("tareas").document(task_id).delete();
    }

    public void deleteTask(String task_id, Runnable onSuccess, Runnable onFailure) {
        db.collection("tareas").document(task_id).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                Map<String, Object> taskData = documentSnapshot.getData();
                taskData.put("deleted_at", System.currentTimeMillis());
                db.collection("deleted_tasks").document(task_id).set(taskData)
                        .addOnSuccessListener(aVoid -> {
                            db.collection("tareas").document(task_id).delete()
                                    .addOnSuccessListener(aVoid2 -> onSuccess.run())
                                    .addOnFailureListener(e -> onFailure.run());
                        })
                        .addOnFailureListener(e -> onFailure.run());
            } else {
                onFailure.run();
            }
        }).addOnFailureListener(e -> onFailure.run());
    }
}