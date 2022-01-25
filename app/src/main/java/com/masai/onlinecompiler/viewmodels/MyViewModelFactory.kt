package com.masai.onlinecompiler.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.masai.onlinecompiler.repository.MyRespository

class MyViewModelFactory(val repository: MyRespository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(repository) as T
    }
}