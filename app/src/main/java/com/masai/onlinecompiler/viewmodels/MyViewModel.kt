package com.masai.onlinecompiler.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.masai.onlinecompiler.data.remote.Resource
import com.masai.onlinecompiler.models.ExecuteCodeRequestModel
import com.masai.onlinecompiler.models.ExecuteCodeResponseModel
import com.masai.onlinecompiler.repository.MyRespository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MyViewModel(private val repository: MyRespository) : ViewModel() {

    fun compileMyCode(code: ExecuteCodeRequestModel): LiveData<Resource<ExecuteCodeResponseModel>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(repository.compileCode(code))
        }
    }
}
