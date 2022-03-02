package de.hshl.isd.mensa.ui.main;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<ItemViewModel>> items = new MutableLiveData<>();

    public MainViewModel() {
        this.items.setValue(Arrays.asList(new ItemViewModel("Test")));
    }

    public LiveData<List<ItemViewModel>> getItems() {
        return items;
    }

    public void setItems(List<ItemViewModel> items) {
        this.items.setValue(items);
    }
}