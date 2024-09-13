package com.codigomaestro.taskly.ui.tareas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TasksViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TasksViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la pantalla de Tareas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}