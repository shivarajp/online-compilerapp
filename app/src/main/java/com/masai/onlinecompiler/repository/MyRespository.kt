package com.masai.onlinecompiler.repository

import android.util.Log
import com.masai.onlinecompiler.data.remote.APIService
import com.masai.onlinecompiler.data.remote.Resource
import com.masai.onlinecompiler.data.remote.ResponseHandler
import com.masai.onlinecompiler.data.remote.RetrofitGenerator
import com.masai.onlinecompiler.models.ExecuteCodeRequestModel
import com.masai.onlinecompiler.models.ExecuteCodeResponseModel


class MyRespository() {

    private val CONTENT_TYPE = "application/json"

    val api = RetrofitGenerator.createService(APIService::class.java)

    val responseHandler = ResponseHandler()

    suspend fun compileCode(code: ExecuteCodeRequestModel): Resource<ExecuteCodeResponseModel> {

        return try {
            val response = api.compileCode(CONTENT_TYPE, code)

            responseHandler.handleSuccess(response)

        } catch (e: Exception) {
            //just extra logging
            responseHandler.handleException(e)
        }

    }
}
