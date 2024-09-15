package com.codigomaestro.navigation_app.ui.calificaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalificacionesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CalificacionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}