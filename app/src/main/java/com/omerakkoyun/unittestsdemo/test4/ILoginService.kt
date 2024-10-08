package com.omerakkoyun.unittestsdemo.test4

import com.omerakkoyun.unittestsdemo.test2.data.Login
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */
interface ILoginService {

        @GET("login")
        fun login(
            @Query("user") username: String,
            @Query("password") password: String): Call<Login>
}