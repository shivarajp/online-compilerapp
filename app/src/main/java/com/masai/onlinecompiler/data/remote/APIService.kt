package com.masai.onlinecompiler.data.remote

import com.masai.onlinecompiler.models.ExecuteCodeRequestModel
import com.masai.onlinecompiler.models.ExecuteCodeResponseModel
import retrofit2.http.*


interface APIService {

    @POST("execute")
    suspend fun compileCode(
        @Header("Content-Type") contentType: String,
        @Body code: ExecuteCodeRequestModel,
    ): ExecuteCodeResponseModel

}