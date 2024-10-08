package com.omerakkoyun.unittestsdemo.test2

import com.omerakkoyun.unittestsdemo.test2.data.ApiResponse
import com.omerakkoyun.unittestsdemo.test2.data.Login

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
class LoginRepositoryImpl : ILoginRepository {

    override fun login(username: String, password: String): ApiResponse<Login> {
        return if (username == "user" && password == "password"){
            ApiResponse(success = Login("123token123"))
        } else {
            val throwable = Throwable()
            ApiResponse(error = throwable)

        }
    }
}