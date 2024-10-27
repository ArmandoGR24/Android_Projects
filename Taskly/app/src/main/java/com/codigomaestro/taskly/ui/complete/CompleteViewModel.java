package com.codigomaestro.taskly.ui.complete;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CompleteViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public CompleteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la pantalla de tareas completadas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}