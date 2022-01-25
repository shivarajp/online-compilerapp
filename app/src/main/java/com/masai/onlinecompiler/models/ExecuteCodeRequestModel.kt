package com.masai.onlinecompiler.models

data class ExecuteCodeRequestModel(
    val args: Any?,
    val clientId: String,
    val clientSecret: String,
    val hasInputFiles: Boolean,
    val language: String,
    val libs: List<Any>?,
    val projectKey: Int,
    val script: String,
    val stdin: Any?,
    val versionIndex: Int
)