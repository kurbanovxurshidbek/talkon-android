package com.talkon.uz.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusSharedViewModel(): ViewModel(){
    var level = MutableLiveData<String>()

}