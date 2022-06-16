package com.talkon.talkon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatusSharedViewModel(): ViewModel(){
    var level = MutableLiveData<String>()

}