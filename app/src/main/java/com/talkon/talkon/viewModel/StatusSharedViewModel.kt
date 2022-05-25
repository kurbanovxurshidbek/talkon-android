package com.talkon.talkon.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusSharedViewModel(): ViewModel(){
    var level = MutableLiveData<String>()

}