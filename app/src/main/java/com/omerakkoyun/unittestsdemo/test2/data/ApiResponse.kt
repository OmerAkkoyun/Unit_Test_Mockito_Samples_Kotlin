package com.omerakkoyun.unittestsdemo.test2.data

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
data class ApiResponse<T>(
    val success: T? = null,
    val error:Throwable? = null)
