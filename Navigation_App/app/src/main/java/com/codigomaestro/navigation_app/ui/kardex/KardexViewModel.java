package com.codigomaestro.navigation_app.ui.kardex;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KardexViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public KardexViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}