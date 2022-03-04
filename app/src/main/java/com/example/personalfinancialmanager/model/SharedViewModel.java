package com.example.personalfinancialmanager.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<Spend> selectedItem = new MutableLiveData<>();
    private boolean isEdit;

    public void selectItem(Spend spend){ selectedItem.setValue(spend);}
    public LiveData<Spend> getSelectedItem(){return selectedItem;}

    public void setIsEdit(boolean isEdit){this.isEdit = isEdit;}
    public boolean getIsEdit(){return isEdit;}

}
