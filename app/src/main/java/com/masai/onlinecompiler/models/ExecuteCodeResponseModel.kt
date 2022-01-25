package com.masai.onlinecompiler.models

data class ExecuteCodeResponseModel(
    val cpuTime: String,
    val memory: String,
    val output: String,
    val statusCode: Int
)