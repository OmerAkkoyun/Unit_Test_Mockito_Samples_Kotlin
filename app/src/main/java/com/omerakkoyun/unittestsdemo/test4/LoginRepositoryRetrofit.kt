package com.omerakkoyun.unittestsdemo.test4

import com.omerakkoyun.unittestsdemo.test2.data.Login
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit.*
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Omer AKKOYUN on 8.10.2024.
 */

class LoginRepositoryRetrofitImpl : ILoginRepositoryRetrofit {

    private val service: ILoginService

    init {
        val retrofit = Builder()
            .baseUrl("https://example.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ILoginService::class.java)
    }

    override fun login(user: String, pass: String, cb: ILoginCallback) {
        service.login(user, pass)
            .enqueue(object : Callback<Login> {
                override fun onFailure(call: Call<Login>, t: Throwable) {
                    cb.fail(t)
                }

                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    if (response.body() != null) {
                        cb.success(response.body()!!)
                    } else {
                        cb.fail(Exception(""))
                    }

                }
            })
    }
}

interface ILoginRepositoryRetrofit {
    fun login(user: String, pass: String, cb: ILoginCallback)
}

interface ILoginCallback {
    fun success(login: Login)
    fun fail(error: Throwable)
}